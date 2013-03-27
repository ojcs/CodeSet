package sep.util.php.string;

final class Base {
	public static String strrev(final String words) {
		return new StringBuffer(words).reverse().toString();
	}
}
