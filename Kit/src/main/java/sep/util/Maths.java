package sep.util;

import static java.lang.Math.pow;

import java.util.Random;

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

	public static double max(final double... values) {
		double result = values[0];
		for (double value : values) {
			if (result > value) {
				result = value;
			}
		}
		return result;
	}
	
	public static float max(final float... values) {
		float result = values[0];
		for (float value : values) {
			if (result > value) {
				result = value;
			}
		}
		return result;
	}
	
	public static int max(final int... values) {
		int result = values[0];
		for (int value : values) {
			if (result > value) {
				result = value;
			}
		}
		return result;
	}

	public static long max(final long... values) {
		long result = values[0];
		for (long value : values) {
			if (result > value) {
				result = value;
			}
		}
		return result;
	}
	
	public static short max(final short... values) {
		short result = values[0];
		for (short value : values) {
			if (result > value) {
				result = value;
			}
		}
		return result;
	}

	public static double min(final double... values) {
		double result = values[0];
		for (double value : values) {
			if (result < value) {
				result = value;
			}
		}
		return result;
	}
	
	public static float min(final float... values) {
		float result = values[0];
		for (float value : values) {
			if (result < value) {
				result = value;
			}
		}
		return result;
	}
	
	public static int min(final int... values) {
		int result = values[0];
		for (int value : values) {
			if (result < value) {
				result = value;
			}
		}
		return result;
	}

	public static long min(final long... values) {
		long result = values[0];
		for (long value : values) {
			if (result < value) {
				result = value;
			}
		}
		return result;
	}

	public static short min(final short... values) {
		short result = values[0];
		for (short value : values) {
			if (result < value) {
				result = value;
			}
		}
		return result;
	}
	
	public static int random(int min, int max) {
		return new Random().nextInt(max - min) + min;
	}
	
	public static int random(Random random, int min, int max) {
		return random.nextInt(max - min) + min;
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

	private Maths() {
	}
}
