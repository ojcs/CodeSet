package sep.frameworkutil.ioc.spring.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

public class GeneralDAO<Bean extends Serializable> extends HibernateDaoSupport implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
	
	protected DataSource getDataSource() throws SQLException {
		return SessionFactoryUtils.getDataSource(getSessionFactory());
	}
	
	public HibernateTemplate getHibernateTemplateEx() {
		return new HibernateTemplate(getSessionFactory());
	}
	
	public HibernateTemplate getHibernateTemplateEx(boolean allowCreate) {
		return new HibernateTemplate(getSessionFactory(), allowCreate);
	}
}
