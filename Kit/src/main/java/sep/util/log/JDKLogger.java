package sep.util.log;

import java.util.logging.Level;

public final class JDKLogger extends Logger {
	private final java.util.logging.Logger log;

	public JDKLogger(final Class<?> clazz) {
		this(clazz.getName());
	}

	public JDKLogger(final String clazzName) {
		super(clazzName);
		log = java.util.logging.Logger.getLogger(clazzName);
	}

	public void debug(final String message) {
		log.logp(Level.FINE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message);
	}

	public void debug(final String message,  Throwable t) {
		log.logp(Level.FINE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, t);
	}

	public void error(final String message) {
		log.logp(Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message);
	}

	public void error(final String message, final Throwable throwable) {
		log.logp(Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, throwable);
	}

	/**
	 * JdkLogger fatal is the same as the error.
	 */
	public void fatal(final String message) {
		log.logp(Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message);
	}

	/**
	 * JdkLogger fatal is the same as the error.
	 */
	public void fatal(final String message, final Throwable t) {
		log.logp(Level.SEVERE, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, t);
	}

	public void info(final String message) {
		log.logp(Level.INFO, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message);
	}

	public void info(final String message, final Throwable throwable) {
		log.logp(Level.INFO, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, throwable);
	}

	public boolean isDebugEnabled() {
		return log.isLoggable(Level.FINE);
	}

	public boolean isErrorEnabled() {
		return log.isLoggable(Level.SEVERE);
	}

	public boolean isFatalEnabled() {
		return log.isLoggable(Level.SEVERE);
	}

	public boolean isInfoEnabled() {
		return log.isLoggable(Level.INFO);
	}

	public boolean isWarnEnabled() {
		return log.isLoggable(Level.WARNING);
	}

	public void warn(final String message) {
		log.logp(Level.WARNING, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message);
	}

	public void warn(final String message, final Throwable throwable) {
		log.logp(Level.WARNING, clazzName, Thread.currentThread().getStackTrace()[1].getMethodName(), message, throwable);
	}
}
