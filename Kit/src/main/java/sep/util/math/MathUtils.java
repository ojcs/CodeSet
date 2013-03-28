package sep.util.math;

import java.util.Random;

public final class MathUtils {
	public static long fibonacci(final int number) {
		return number <= 1 ? 1 : fibonacci(number - 1) + fibonacci(number - 2);
	}
	
	public static double min(final double one, final double two, final double three) {
		return (two < one) ? two : ((three < two) ? three : two);
	}
	
	public static long min(final long one, final long two, final long three) {
		return (two < one) ? two : ((three < two) ? three : two);
	}
	
	public static void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static <E> void swap(E[] array, int i, int j) {
		final E temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static int random(int min, int max) {
		return new Random().nextInt(max - min) + min;
	}
	
	public static int random(Random random, int min, int max) {
		return random.nextInt(max - min) + min;
	}
	
	/**
	 * 用(int) Math.sqrt(n)求出循环上限
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
	
	private MathUtils() {
	}
}
