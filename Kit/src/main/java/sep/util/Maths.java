package sep.util;

import static java.lang.Math.pow;

import java.math.BigInteger;

public final class Maths {
	public static long fibonacci(final long size) {
		long count = 0, first = 1, second = 1;
		while (count++ < size) {
			second = (first = second) + second;
		}
		return first;
	}

	/** 质数 */
	public static boolean isPrime(final int value) {
		if (value <= 2) {
			return true;
		}
		for (int i = 2, limit = (int) Math.sqrt(value); i <= limit; i++) {
			if (value % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 四舍五入
	 * @param number 要进行舍入操作的数。
	 * @param bit 要保留小数的精确位数。
	 * @return 舍入后的结果。
	 */
	public static double round(final double number, final int bit) {
		double temp = pow(10, bit);
		return java.lang.Math.round(number * temp) / temp;
	}
	
	/** 任意进制转换 */
	public static String convertRadix(long value, final int radix) {
		return BigInteger.valueOf(value).toString(radix);
	}

	private Maths() {
	}
}
