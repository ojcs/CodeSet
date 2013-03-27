package sep.util.log;

public abstract class Logger {
	protected final String clazzName;
	
	public Logger(final Class<?> clazz) {
		this(clazz.getName());
	}
	
	public Logger(final String clazzName) {
		this.clazzName = clazzName;
	}
	
	public abstract void debug(String message);

	public abstract void debug(String message, Throwable t);

	public abstract void info(String message);

	public abstract void info(String message, Throwable t);

	public abstract void warn(String message);

	public abstract void warn(String message, Throwable t);

	public abstract void error(String message);

	public abstract void error(String message, Throwable t);

	public abstract void fatal(String message);

	public abstract void fatal(String message, Throwable t);

	public abstract boolean isDebugEnabled();

	public abstract boolean isInfoEnabled();

	public abstract boolean isWarnEnabled();

	public abstract boolean isErrorEnabled();

	public abstract boolean isFatalEnabled();
}
