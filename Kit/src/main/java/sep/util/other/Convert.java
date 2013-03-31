package sep.util.other;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import sep.util.collection.CollectionUtil;

public final class Convert {
	public interface Callback<Old, New> {
		New as(Old old);
	}
	
	public static <New, Old> List<? extends New> as(final List<? extends Old> list, final Callback<Old, New> callback) {
		final List<Old> olds = CollectionUtil.removeNull(list);
		final List<New> news = new ArrayList<New>(olds.size());
		for (Old old : olds) {
			news.add(callback.as(old));
		}
		return news;
	}
	
	public static <New, Old> New[] as(final Old[] array, final Callback<Old, New> callback) {
		return CollectionUtil.toArray(as(CollectionUtil.<Old>toList(array), callback));
	}
	
	public static String formatDate(final String pattern, final Date date) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	public static String formatNumber(final String pattern, final Number number) {
		return new DecimalFormat(pattern).format(number);
	}
	
	public static Date parseDate(final String pattern, final String date) throws ParseException {
		return new SimpleDateFormat(pattern).parse(date);
	}
	
	public static Number parseNumber(final String pattern, final String number) throws ParseException {
		return new DecimalFormat(pattern).parse(number);
	}
	
	public static String toBinaryString(final int value) {
		return Integer.toBinaryString(value);
	}
	
	public static String toBinaryString(final long value) {
		return Long.toBinaryString(value);
	}
	
	public static String toHexString(final byte... values) {
		return DatatypeConverter.printHexBinary(values);
	}
	
	public static String toHexString(final double value) {
		return Double.toHexString(value);
	}
	
	public static String toHexString(final float value) {
		return Float.toHexString(value);
	}
	
	public static String toHexString(final int value) {
		return Integer.toHexString(value);
	}
	
	public static String toHexString(final long value) {
		return Long.toHexString(value);
	}
	
	public static String toOctalString(final int value) {
		return Integer.toOctalString(value);
	}
	
	public static String toOctalString(final long value) {
		return Long.toOctalString(value);
	}
	
	public static String toString(final BigDecimal value) {
		return value.toString();
	}
	
	public static String toString(final BigInteger value) {
		return value.toString();
	}
	
	public static String toString(final BigInteger value, final int radix) {
		return value.toString(radix);
	}
	
	public static String toString(final double value) {
		return Double.toString(value);
	}
	
	public static String toString(final float value) {
		return Float.toString(value);
	}
	
	public static String toString(final int value) {
		return Integer.toString(value);
	}
	
	public static String toString(final int value, final int radix) {
		return Integer.toString(value, radix);
	}
	
	public static String toString(final long value) {
		return Long.toString(value);
	}
	
	public static String toString(final long value, final int radix) {
		return Long.toString(value, radix);
	}
	
	public static String toString(final short value) {
		return Short.toString(value);
	}

	private Convert() {
	}
}
