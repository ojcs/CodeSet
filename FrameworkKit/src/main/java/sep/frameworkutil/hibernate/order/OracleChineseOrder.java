package sep.frameworkutil.hibernate.order;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;

/**
 * Oracle 中文排序
 * @Ref http://www.oschina.net/code/snippet_176115_17508
 */
public class OracleChineseOrder extends Order {
	private static final long serialVersionUID = 1L;

	public static OracleChineseOrder asc(String propertyName) {
		return new OracleChineseOrder(propertyName, OrderType.ASC);
	}

	public static OracleChineseOrder desc(String propertyName) {
		return new OracleChineseOrder(propertyName, OrderType.DESC);
	}

	protected final OrderType orderType;
	
	protected final String propertyName;
	
	public OracleChineseOrder(String propertyName, OrderType orderType) {
		super(propertyName, orderType.equals(OrderType.ASC));
		this.propertyName = propertyName;
		this.orderType = orderType;
	}

	/** 只考虑按一个字段排序的情况 */
	public String toSqlString(Criteria criteria, CriteriaQuery query) throws HibernateException {
		final String column = query.getColumnsUsingProjection(criteria, propertyName)[0];
		return String.format(" nlssort(%s,'NLS_SORT=SCHINESE_PINYIN_M') %s", column, orderType.name());
	}
}
