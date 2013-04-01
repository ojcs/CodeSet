package sep.frameworkutil.spring.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

import sep.frameworkutil.hibernate.order.OrderType;

public class HibernateTemplate extends org.springframework.orm.hibernate3.HibernateTemplate {
	public HibernateTemplate() {
		super();
	}

	public HibernateTemplate(SessionFactory factory) {
		super(factory);
	}

	public HibernateTemplate(SessionFactory factory, boolean allowCreate) {
		super(factory, allowCreate);
	}

	/**
	 * 执行HQL查询语句
	 * @param hql HQL语句
	 * @param values 值列表
	 */
	public <T> T executeQuery(final String hql, final Object... values) {
		return execute(new HibernateCallback<T>() {
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				setParams(query, values);
				return (T) query.list();
			}
		});
	}

	/**
	 * 查询单个值
	 * @param hql HQL语句
	 * @param values 参数列表
	 * @return 返回单个值
	 */
	public <T> T executeSacale(final String hql, final Object... values) {
		return execute(new HibernateCallback<T>() {
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				setParams(query, values);
				query.setMaxResults(1);
				return (T) query.uniqueResult();
			}
		});
	}

	/**
	 * 执行HQL删除、修改语句
	 * @param hql HQL语句
	 * @param values 值列表
	 */
	public int executNonQuery(final String hql, final Object... values) {
		return execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				setParams(query, values);
				return query.executeUpdate();
			}
		});
	}

	/** 查询全部 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<?> clazz) {
		return find("from " + clazz.getName());
	}

	@SuppressWarnings("unchecked")
	public long getSumRecord(final Class<?> clazz) {
		List<Long> count = find("select COUNT(*) from " + clazz.getName());
		return count.size() > 0 ? (count.get(0)) : 0L;
	}

	/** 判断值是否为空或 */
	private boolean isEmptyOrNull(Object... values) {
		if (values == null || values.length == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 分页
	 * @param entity 类名
	 * @param index 当前页数
	 * @param size 每页显示的大小
	 * @param order 排序类型
	 * @param propertyName 要排序的属性名
	 */
	public <T> List<T> paging(final Class<?> entity, final int index, final int size, final OrderType order, final String... propertyName) {
		return executeFindEx(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(entity);
				if (propertyName != null && propertyName.length > 0) {
					switch (order) {
					case ASC : criteria.addOrder(Order.asc(propertyName[0]));  break;
					case DESC: criteria.addOrder(Order.desc(propertyName[0])); break;
					}
				}
				criteria.setFirstResult((index - 1) * size);
				criteria.setMaxResults(size);
				return criteria.list();
			}
		});
	}

	/**
	 * 根据字段查询
	 * @param params 字段列表
	 * @param values 值列表
	 */
	public <T> List<T> queryByProperty(final Class<?> clazz, final Map<String, Object> params) {
		return executeFindEx(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(clazz);
				for (String field : params.keySet()) {
					criteria.add(Property.forName(field).eq(params.get(field)));
				}
				return criteria.list();
			}
		});
	}
	
	/**
	 * 根据字段查询可以分的页数
	 * @param clazz 要查询的实体类
	 * @param params 属性列表
	 * @param size 每页显示的大小
	 */
	public long queryByPropertyGetSumPage(final Class<?> clazz, final Map<String, Object> params, final int size) {
		List<Integer> ints = executeFindEx(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(clazz);
				for (String field : params.keySet()) {
					criteria.add(Property.forName(field).eq(params.get(field)));
				}
				criteria.setProjection(Projections.rowCount());
				return criteria.list();
			}
		});
		int count = (ints == null) ? 0 : ints.get(0);
		return count > 0 ? (count + size - 1) / size : 0L;
	}

	/** 获取总记录数根据属性 */
	public long queryByPropertyGetSumRecord(final Class<?> clazz, final Map<String, Object> params) {
		List<Integer> ints = executeFindEx(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(clazz);
				for (String field : params.keySet()) {
					criteria.add(Property.forName(field).eq(params.get(field)));
				}
				criteria.setProjection(Projections.rowCount());
				return criteria.list();
			}
		});
		return ints == null ? 0 : ints.get(0);
	}

	/**
	 * 根据字段查询并分页显示
	 * @param clazz 要分页的实体类
	 * @param params 字段列表
	 * @param index 当前页
	 * @param size 每页显示的大小
	 * @param order 排序
	 */
	public <T> List<T> queryByPropertyPaging(final Class<?> clazz, final Map<String, Object> params, final int index, final int size, final OrderType order, final String... field) {
		return executeFindEx(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(clazz);
				for (String f : params.keySet()) {
					criteria.add(Property.forName(f).eq(params.get(f)));
				}
				if (field != null && field.length != 0) {
					switch (order) {
					case ASC:  criteria.addOrder(Order.asc(field[0]));  break;
					case DESC: criteria.addOrder(Order.desc(field[0])); break;
					}
				}
				criteria.setFirstResult((index - 1) * size);
				criteria.setMaxResults(size);
				return criteria.list();
			}
		});
	}

	/**
	 * 模糊查询
	 * @param clazz 类名
	 * @param field 字段名
	 * @param value 值
	 * @param matchMode 匹配模式:ANYWHERE->任意位置、END->结束、START->开始、EXACT->精确匹配
	 */
	public <T> List<T> queryLike(final Class<?> clazz, final String field, final String value, final MatchMode matchMode) {
		return executeFindEx(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createCriteria(clazz).add(Property.forName(field).like(value, matchMode)).list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> executeFindEx(HibernateCallback<Object> action) throws DataAccessException {
		return super.executeFind(action);
	}

	private void setParams(Query query, Object... values) {
		if (!isEmptyOrNull(values)) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
	}

	/**
	 * 获取总页数
	 * @param className 类名
	 * @param size 每页显示的大小
	 */
	@SuppressWarnings("unchecked")
	public long sumPage(final Class<?> className, int size) {
		List<Long> count = find("select COUNT(*) from " + className.getName());
		return count.size() > 0 ? (count.get(0) - 1 + size) / size : 0L;
	}

	/**
	 * 删除表数据
	 * @param tableName 表名
	 */
	public int truncate(final String tableName) {
		return execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery("truncate table " + tableName).executeUpdate();
			}
		});
	}
}