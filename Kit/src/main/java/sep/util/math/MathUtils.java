package sep.util.math;

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
	
	public static <T> void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	private MathUtils() {
	}
}
