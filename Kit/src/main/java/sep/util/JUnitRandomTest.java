package sep.util;

import sep.util.collection.CollectionUtil;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public abstract class JUnitRandomTest {
	public final static Test suite(final Iterable<Class<?>> classes) {
		return suite(CollectionUtil.toArray(classes));
	}
	
	@SuppressWarnings("unchecked")
	public final static Test suite(final Class<?>... classes) {
		new Random().shuffle(classes);
		TestSuite suite = new TestSuite();
		for (Class<?> clazz : classes) {
			if (clazz.isAssignableFrom(TestCase.class)) {
				suite.addTestSuite((Class<? extends TestCase>) clazz);
			} else {
				suite.addTest(new JUnit4TestAdapter(clazz));
			}
		}
		return suite;
	}
}
