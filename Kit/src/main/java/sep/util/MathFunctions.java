package sep.util;

import static java.lang.Math.acos;
import static java.lang.Math.asin;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.log;
import static java.lang.Math.sin;

public class MathFunctions {
	/** 反三角[反余弦] */
	public static double arccos(final double value) {
		return acos(value);
	}

	/** 反三角[反余切] */
	public static double arccot(final double value) {
		return arctan(1 / value);
	}

	/** 反三角[反余割] */
	public static double arccsc(final double value) {
		return arcsin(1 / value);
	}

	/** 反三角[反正割] */
	public static double arcsec(final double value) {
		return arccos(1 / value);
	}

	/** 反三角[反正弦] */
	public static double arcsin(final double value) {
		return asin(value);
	}

	/** 反三角[反正切] */
	public static double arctan(final double value) {
		return atan(value);
	}

	/** 三角[余切] */
	public static double cot(final double value) {
		return cos(value) / sin(value);
	}

	/** 余矢[余弦] */
	public static double covercosin(final double value) {
		return 1 + sin(value);
	}

	/** 余矢[正弦] */
	public static double coversin(final double value) {
		return 1 - sin(value);
	}

	/** 三角[余割] */
	public static double csc(final double value) {
		return 1 / sin(value);
	}

	/** 外[余割] */
	public static double excsc(final double value) {
		return csc(value) - 1;
	}

	/** 外[正割] */
	public static double exsec(final double value) {
		return sec(value) - 1;
	}

	/** 半余矢[余弦] */
	public static double hacovercosin(final double value) {
		return (1 + sin(value)) / 2;
	}

	/** 半余矢[正弦] */
	public static double hacoversin(final double value) {
		return (1 - sin(value)) / 2;
	}

	/** 半正矢[余弦] */
	public static double havercosin(final double value) {
		return (1 + cos(value)) / 2;
	}

	/** 半正矢[正弦] */
	public static double haversin(final double value) {
		return (1 - cos(value)) / 2;
	}

	/** 2的自然对数 */
	public static double log2(final double value) {
		return logN(2, value);
	}

	/**
	 * last为底real的对数。
	 * 
	 * @param last 底数。
	 * @param real 真数。
	 * @return 对数值。
	 */
	public static double logN(final double last, final double real) {
		return log(real) / log(last);
	}

	public static double sec(final double value) {
		return 1 / cos(value);
	}

	/** 正矢[余弦] */
	public static double vercosin(final double value) {
		return 1 + cos(value);
	}

	/** 正矢[正弦] */
	public static double versin(final double value) {
		return 1 - cos(value);
	}
	
	private MathFunctions() {
	}
}
