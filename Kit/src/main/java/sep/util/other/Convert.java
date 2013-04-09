package sep.util.other;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	private Convert() {
	}
}
