package sep.util.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public final class ImageUtil {
	public static Dimension getSize(final Image image) {
		return new Dimension(image.getHeight(null), image.getHeight(null));
	}
	
	private static Map<String, Object> preparatoryScale(final Image image, final Dimension targetDimension) {
		// 获得尺寸
		final float oldSize = (float) image.getWidth(null) / (float) image.getHeight(null);
		final float newSize = (float) targetDimension.getWidth() / (float) targetDimension.getHeight();
		
		// 比例相同
		Dimension newDimension = (Dimension) targetDimension.clone();
		Point newPoint = new Point(0, 0);
		// 缩放
		if (oldSize > newSize) { // 原图太宽，计算当原图与画布同高时，原图的等比宽度
			newDimension.setSize(targetDimension.width, targetDimension.width / oldSize);
			newPoint.setLocation(0, targetDimension.height - newDimension.height / 2);
		} else if (oldSize < newSize) { // 原图太长
			newDimension.setSize(targetDimension.height * oldSize, targetDimension.height);
			newPoint.setLocation((targetDimension.height - newDimension.width) / 2, 0);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dimension", newDimension);
		map.put("point", newPoint);
		return map;
	}
	
	public static BufferedImage rotate(final BufferedImage image, final int degree) {
		// 原始图象的宽度&高度
		final Dimension originSize = new Dimension(image.getWidth(null), image.getHeight(null));
		
		int newDegree = degree % 360;
		if (newDegree < 0) { newDegree += 360; }// 将角度转换到0-360度之间
		final double ang = newDegree * 0.0174532925;// 将角度转为弧度
		
		// 确定旋转后的图象的高度和宽度
		final Dimension rotateDimension;
		if (0 == newDegree || 90 == newDegree || 180 == newDegree || 270 == newDegree || 360 == newDegree) {
			rotateDimension = (Dimension) originSize.clone();
		} else {
			final int size = originSize.width + originSize.height;
			rotateDimension = new Dimension(
				(int) (size * Math.abs(Math.cos(ang))),
				(int) (size * Math.abs(Math.sin(ang)))
			);
		}
		
		final BufferedImage rotatedImage = new BufferedImage(rotateDimension.width, rotateDimension.height, image.getType());
		rotatedImage.getGraphics().fillRect(0, 0, rotateDimension.width, rotateDimension.height);// 以给定颜色绘制旋转后图片的背景
		
		//[ Transform
		final AffineTransform transform = new AffineTransform();
		transform.rotate(// 旋转图象
			ang,
			rotateDimension.width / 2,
			rotateDimension.height / 2
		);
		transform.translate(
			(rotateDimension.width / 2) - (originSize.width / 2),
			(rotateDimension.height / 2) - (originSize.height / 2)
		);
		
		new AffineTransformOp(
			transform,
			AffineTransformOp.TYPE_NEAREST_NEIGHBOR
		).filter(image, rotatedImage);
		//]
		return rotatedImage;
	}

	public static Dimension suiteSize(final Dimension originSize, final Dimension targetSize) {
		// 不需要缩略
		if (originSize.equals(targetSize)) {
			return (Dimension) originSize.clone();
		}
		
		// 制取同比例宽高
		final float copOptimal = (float) targetSize.width / (float) targetSize.height; // 最优宽高比例
		final float copOrigin  = (float) originSize.width / (float) originSize.height; // 当前宽高比例
		
		final Dimension newDimension = new Dimension();
		// 按照比例缩略
		if (copOrigin > copOptimal) { // 当前图片较宽
			newDimension.setSize(
				targetSize.width,
				originSize.width * (float) targetSize.width / (float) originSize.width
			);
		} else {
			newDimension.setSize(
				originSize.width * (float) targetSize.height / (float) originSize.height,
				targetSize.height
			);
		}
		return newDimension;
	}
	
	public static BufferedImage toBufferedImage(final Image originImage) {
		if (originImage instanceof BufferedImage) {
			return (BufferedImage) originImage;
		}
		
		// This code ensures that all the pixels in the image are loaded
		final Image image = new ImageIcon(originImage).getImage();
		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent
		// Pixels
		final boolean hasAlpha = false;// hasAlpha(image);
		// Create a buffered image with a format that's compatible with the
		// screen
		final int width = image.getWidth(null);
		final int height = image.getHeight(null);
		
		BufferedImage bufferedImage;
		try {
			// Determine the type of transparency of the new buffered image
			final int transparency = hasAlpha ? Transparency.OPAQUE : Transparency.BITMASK;
			// Create the buffered image
			bufferedImage =
				GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration()
				.createCompatibleImage(width, height, transparency);
		} catch (HeadlessException e) {
			// Create a buffered image using the default color model
			final int type = hasAlpha ?  BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
			bufferedImage = new BufferedImage(width, height, type);
		}
		
		// Copy image to buffered image
		Graphics g = bufferedImage.createGraphics();
		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();
		
		return bufferedImage;
	}

	public static BufferedImage trim(final Image image, final Dimension targetDimension) {
		final Map<String, Object> scale = preparatoryScale(image, targetDimension);
		final Dimension scaleDimension = (Dimension) scale.get("dimension");
		final Point scalePoint = (Point) scale.get("point");
		
		// 创建图像
		final BufferedImage newImage = new BufferedImage((int)targetDimension.getWidth(), (int)targetDimension.getHeight(), ColorSpace.TYPE_RGB);
		newImage.getGraphics().drawImage(
			image,
			scalePoint.x, scalePoint.y,
			scaleDimension.width, scaleDimension.height,
			Color.black,
			null
		);
		// 返回
		return newImage;
	}

	public static BufferedImage zoom(final Image image, final Dimension targetDimension, final Color bgColor) {
		// 检查背景颜色
		final Color color = (null == bgColor) ? Color.black : bgColor;
		
		final Map<String, Object> scale = preparatoryScale(image, targetDimension);
		final Dimension scaleDimension = (Dimension) scale.get("dimension");
		final Point scalePoint = (Point) scale.get("point");
		
		// 创建图像
		final BufferedImage newImage = new BufferedImage((int)targetDimension.getWidth(), (int)targetDimension.getHeight(), ColorSpace.TYPE_RGB);
		// 得到一个绘制接口
		final Graphics g = newImage.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, (int) targetDimension.getWidth(), (int) targetDimension.getHeight());
		g.drawImage(
			image,
			scalePoint.x, scalePoint.y,
			scaleDimension.width, scaleDimension.height,
			color,
			null
		);
		// 返回
		return newImage;
	}

	private ImageUtil() {
	}
}
