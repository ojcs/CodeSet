package sep.frameworkutil.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import sep.frameworkutil.spring.hibernate.HibernateTemplate;

public class HibernateUtil implements AutoCloseable {
	private final SessionFactory factory;

	protected final HibernateUtil instance =
			new HibernateUtil(new Configuration().configure(), new ServiceRegistryBuilder().buildServiceRegistry());

	protected HibernateUtil(Configuration configure, ServiceRegistry registry) {
		this.factory = configure.buildSessionFactory(registry);
	}

	public Session getCurrentSession() throws HibernateException {
		return factory.getCurrentSession();
	}

	public final HibernateUtil getInstance() {
		return instance;
	}
	
	public HibernateTemplate newTemplate() {
		return new HibernateTemplate(factory);
	}
	
	public HibernateTemplate newTemplate(boolean allowCreate) {
		return new HibernateTemplate(factory, allowCreate);
	}

	@Override
	public void close() throws HibernateException {
		factory.close();
	}
}