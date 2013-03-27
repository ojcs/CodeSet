package sep.util.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

public final class WatermarkUtil {
	private WatermarkUtil() {
	}
	
	public static Image setImage(final Image targetImage, final Point markLocation, final Image markImage) {
		final Dimension targetSize = ImageUtil.getSize(targetImage);
		final Dimension watermarkSize = ImageUtil.getSize(markImage);
		final Point watermarkLocation = new Point(
			targetSize.width - watermarkSize.width - markLocation.x,
			targetSize.height - watermarkSize.height - markLocation.y
		);
		
		final BufferedImage image = new BufferedImage(targetSize.width, targetSize.height, BufferedImage.TYPE_INT_RGB);
		
		final Graphics g  = image.createGraphics();
		g.drawImage(targetImage, 0, 0, targetSize.width, targetSize.height, null);
		g.drawImage(
			markImage,
			watermarkLocation.x, watermarkLocation.y,
			watermarkSize.width, watermarkSize.height,
			null
		);
		g.dispose();
		
		return image;
	}
	
	public static Image setText(final Image targetImage, final Point markLocation, final String markText, final Font markFont, final Color markFontColor) {
		final Dimension targetSize = ImageUtil.getSize(targetImage);
		
		final BufferedImage image = new BufferedImage(
			targetSize.width,
			targetSize.height,
			BufferedImage.TYPE_INT_RGB
		);
		
		final Graphics g = image.createGraphics();
		g.drawImage(targetImage, 0, 0, targetSize.width, targetSize.height, null);
		
		g.setColor(markFontColor);
        g.setFont(markFont);
		g.drawString(
			markText,
			targetSize.width - markFont.getSize() - markLocation.x,
			targetSize.height - markFont.getSize() / 2 - markLocation.y
		);
		g.dispose();
		
		return image;
	}
}
