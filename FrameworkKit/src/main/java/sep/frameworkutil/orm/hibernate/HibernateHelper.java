package sep.frameworkutil.orm.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import sep.frameworkutil.ioc.spring.hibernate.HibernateTemplate;

public class HibernateHelper implements AutoCloseable {
	protected static final HibernateHelper instance =
		new HibernateHelper(
			new Configuration().configure(),
			new ServiceRegistryBuilder().buildServiceRegistry()
		);

	public static final HibernateHelper getInstance() {
		return instance;
	}
	
	protected final Configuration configure;

	protected final SessionFactory factory;

	protected HibernateHelper(Configuration configure, ServiceRegistry registry) {
		this.factory = (this.configure = configure).buildSessionFactory(registry);
	}

	@Override
	public void close() throws HibernateException {
		factory.close();
	}
	
	public Configuration getConfigure() {
		return configure;
	}
	
	public Session getCurrentSession() throws HibernateException {
		return factory.getCurrentSession();
	}

	public SessionFactory getFactory() {
		return factory;
	}
	
	public HibernateTemplate newTemplate() {
		return new HibernateTemplate(factory);
	}
	
	public HibernateTemplate newTemplate(boolean allowCreate) {
		return new HibernateTemplate(factory, allowCreate);
	}
}