package sep.framework.curtness.dao;

import static java.sql.Connection.TRANSACTION_NONE;
import static java.sql.Connection.TRANSACTION_READ_COMMITTED;
import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
import static java.sql.Connection.TRANSACTION_REPEATABLE_READ;
import static java.sql.Connection.TRANSACTION_SERIALIZABLE;

public enum DataIsolationLevel {
	None(TRANSACTION_NONE),
	ReadCommitted(TRANSACTION_READ_COMMITTED),
	ReadUncommitted(TRANSACTION_READ_UNCOMMITTED),
	RepeatableRead(TRANSACTION_REPEATABLE_READ),
	Serializable(TRANSACTION_SERIALIZABLE);
	
	public static DataIsolationLevel get(final int level) {
		switch (level) {
		case TRANSACTION_NONE:				return None;
		case TRANSACTION_READ_COMMITTED:	return ReadCommitted;
		case TRANSACTION_READ_UNCOMMITTED:	return ReadUncommitted;
		case TRANSACTION_REPEATABLE_READ:	return RepeatableRead;
		case TRANSACTION_SERIALIZABLE:		return Serializable;
		default:							return null;
		}
	}
	
	private final int level;
	
	private DataIsolationLevel(final int level) {
		this.level = level;
	}
	
	int get() {
		return level;
	}
	
	@Override
	public String toString() {
		return name();
	}
}
