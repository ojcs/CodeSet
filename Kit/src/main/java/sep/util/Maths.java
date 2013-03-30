package sep.util;

import static java.lang.Math.acos;
import static java.lang.Math.asin;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sin;

import java.util.Random;

public final class Maths {
	public static double arccos(final double value) {
		return acos(value);
	}

	public static double arccot(final double value) {
		return arctan(1 / value);
	}

	public static double arccsc(final double value) {
		return arcsin(1 / value);
	}

	public static double arcsec(final double value) {
		return arccos(1 / value);
	}

	public static double arcsin(final double value) {
		return asin(value);
	}

	public static double arctan(final double value) {
		return atan(value);
	}

	public static double cot(final double value) {
		return cos(value) / sin(value);
	}

	/** 余矢函数 */
	public static double covercosin(final double value) {
		return 1 + sin(value);
	}

	/** 余矢函数 */
	public static double coversin(final double value) {
		return 1 - sin(value);
	}

	public static double csc(final double value) {
		return 1 / sin(value);
	}

	/** 外余割函数 */
	static double excsc(final double value) {
		return csc(value) - 1;
	}

	/** 外正割函数 */
	static double exsec(final double value) {
		return sec(value) - 1;
	}

	/** 半余矢函数 */
	static double hacovercosin(final double value) {
		return (1 + sin(value)) / 2;
	}

	/** 半余矢函数 */
	static double hacoversin(final double value) {
		return (1 - sin(value)) / 2;
	}

	/** 半正矢函数 */
	static double havercosin(final double value) {
		return (1 + cos(value)) / 2;
	}

	/** 半正矢函数 */
	static double haversin(final double value) {
		return (1 - cos(value)) / 2;
	}

	static double log2(final double value) {
		return logN(2, value);
	}

	/**
	 * last为底real的对数。
	 * @param last 底数。
	 * @param real 真数。
	 * @return 对数值。
	 */
	static double logN(final double last, final double real) {
		return log(real) / log(last);
	}

	public static double min(final double one, final double two, final double three) {
		return (two < one) ? two : ((three < two) ? three : two);
	}

	public static long min(final long one, final long two, final long three) {
		return (two < one) ? two : ((three < two) ? three : two);
	}

	public static int random(int min, int max) {
		return new Random().nextInt(max - min) + min;
	}

	public static int random(Random random, int min, int max) {
		return random.nextInt(max - min) + min;
	}
	
	/**
	 * 对number进行四舍五入操作。
	 * @param num 要进行舍入操作的数。
	 * @param bit 要保留小数的精确位数。
	 * @return 舍入后的结果。
	 */
	static double round(final double number, final int bit) {
		double temp = pow(10, bit);
		return java.lang.Math.round(number * temp) / temp;
	}
	
	public static double sec(final double value) {
		return 1 / cos(value);
	}
	
	public static void swap(boolean[] array, int i, int j) {
		boolean temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void swap(double[] array, int i, int j) {
		double temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static <E> void swap(E[] array, int i, int j) {
		final E temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void swap(float[] array, int i, int j) {
		float temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void swap(short[] array, int i, int j) {
		short temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	/** 正矢函数 */
	public static double vercosin(final double value) {
		return 1 + cos(value);
	}
	
	/** 正矢函数 */
	public static double versin(final double value) {
		return 1 - cos(value);
	}
	
	public static long fibonacci(final int number) {
		return number <= 1 ? 1 : fibonacci(number - 1) + fibonacci(number - 2);
	}
	
	/**
	 * 用Math.sqrt(n)求出循环上限
	 * isPrime()方法用来检测当前数是否为质数
	 */
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
	
	private Maths() {
	}
}
