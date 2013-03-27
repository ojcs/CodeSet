package sep.framework.text.format;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.text.NumberFormat;

import org.junit.Test;

import sep.framework.text.format.RMBFormat;

public class RMBFormatTest {
	private NumberFormat format = new RMBFormat();

	/** 整数 */
	@Test
	public void testInteger() {
		assertThat("零元整", equalTo(format.format(0)));
		assertThat("壹佰贰拾叁元整", equalTo(format.format(123)));
		assertThat("壹佰万元整", equalTo(format.format(1000000)));
		assertThat("壹亿零壹元整", equalTo(format.format(100000001)));
		assertThat("壹拾亿元整", equalTo(format.format(1000000000)));
		assertThat("壹拾贰亿叁仟肆佰伍拾陆万柒仟捌佰玖拾元整", equalTo(format.format(1234567890)));
		assertThat("壹拾亿零壹佰壹拾万零壹佰零壹元整", equalTo(format.format(1001100101)));
		assertThat("壹亿壹仟零壹拾万壹仟零壹拾元整", equalTo(format.format(110101010)));
	}

	/** 小数 */
	@Test
	public void testDecimal() {
		assertThat("壹角贰分", equalTo(format.format(0.12)));
		assertThat("壹佰贰拾叁元叁角肆分", equalTo(format.format(123.34)));
		assertThat("壹佰万元伍角陆分", equalTo(format.format(1000000.56)));
		assertThat("壹亿零壹元柒角捌分", equalTo(format.format(100000001.78)));
		assertThat("壹拾亿元玖角", equalTo(format.format(1000000000.90)));
		assertThat("壹拾贰亿叁仟肆佰伍拾陆万柒仟捌佰玖拾元叁分", equalTo(format.format(1234567890.03)));
		assertThat("壹拾亿零壹佰壹拾万零壹佰零壹元整", equalTo(format.format(1001100101.00)));
		assertThat("壹亿壹仟零壹拾万壹仟零壹拾元壹角", equalTo(format.format(110101010.10)));
	}

	/** 负数 */
	@Test
	public void testNegativeNumber() {
		assertThat("负壹角贰分", equalTo(format.format(-0.12)));
		assertThat("负壹佰贰拾叁元叁角肆分", equalTo(format.format(-123.34)));
		assertThat("负壹佰万元伍角陆分", equalTo(format.format(-1000000.56)));
		assertThat("负壹亿零壹元柒角捌分", equalTo(format.format(-100000001.78)));
		assertThat("负壹拾亿元玖角", equalTo(format.format(-1000000000.90)));
		assertThat("负壹拾贰亿叁仟肆佰伍拾陆万柒仟捌佰玖拾元叁分", equalTo(format.format(-1234567890.03)));
		assertThat("负壹拾亿零壹佰壹拾万零壹佰零壹元整", equalTo(format.format(-1001100101.00)));
		assertThat("负壹亿壹仟零壹拾万壹仟零壹拾元壹角", equalTo(format.format(-110101010.10)));
	}
}
