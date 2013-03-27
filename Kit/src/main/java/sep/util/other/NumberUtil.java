//package sep.util.other;
//
//import sep.framework.text.RegexNumber;
//
//public final class NumberUtil {
//	public static boolean isDecimal(final String value) {
//		return RegexNumber.Decimal_Decimal.matches(value);
//	}
//	
//	public static boolean isInteger(final String value) {
//		return RegexNumber.Decimal_Integer.matches(value);
//	}
//	
//	public static boolean isNumber(final String value) {
//		return RegexNumber.Decimal_ScientificNotation.matches(value);
//	}
//	
//	public static byte reverse(final byte data) {
//		return (byte) ((data & 0xF0) >> 4 | (data & 0x0F) << 4);
//	}
//	
//	private NumberUtil() {
//	}
//}
