package sep.framework.curtness.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public final class Transaction implements AutoCloseable {
	public static void checkOpen(final Connection connection) throws IllegalArgumentException {
		try {
			if (hasOpen(connection)) {
				throw new IllegalArgumentException("Connection has been opened Affairs");
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@SuppressWarnings("resource")
	public static void execute(final Connection connection, final Atom atom) throws SQLException {
		new Transaction(connection).execute(atom);
	}
	
	public static boolean hasOpen(final Connection connection) throws SQLException {
		return !connection.getAutoCommit();
	}
	
	private transient final Connection connection;
	
	Transaction(final Connection connection) throws SQLException {
		super();
		if (connection == null || connection.isClosed()) {
			throw new SQLException("Connection isClosed");
		}
		this.connection = connection;
		setTransaction(true);
		setSavepoint();
	}
	
	Transaction(final Connection connection, final DataIsolationLevel isolationLevel) throws SQLException {
		this(connection);
		setDataIsolationLevel(isolationLevel);
	}
	
	@Override
	public void close() throws SQLException {
		connection.close();
	}
	
	public void closeTransaction() throws SQLException {
		setTransaction(false);
	}
	
	public void commit() throws SQLException {
		connection.commit();
		openTransaction();
	}
	
	public void execute(final Atom atom) throws SQLException {
		try {
			atom.transaction(connection, this);
		} catch (SQLException e) {
			rollback();
			throw e;
		} finally {
			commit();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			commit();
		} catch (SQLException commit) {
			try {
				rollback();
			} catch (SQLException rollback) {
				throw rollback;
			}
			throw commit;
		}
		super.finalize();
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public DataIsolationLevel getDataIsolationLevel() throws SQLException {
		return DataIsolationLevel.get(connection.getTransactionIsolation());
	}
	
	public boolean hasOpen() throws SQLException {
		return hasOpen(connection);
	}
	
	public void openTransaction() throws SQLException {
		setTransaction(true);
	}
	
	public void rollback() throws SQLException {
		connection.rollback();
	}
	
	public void rollback(final Savepoint savepoint) throws SQLException {
		connection.rollback(savepoint);
	}

	public void setDataIsolationLevel(final DataIsolationLevel level) throws SQLException {
		connection.setTransactionIsolation(level.get());
	}
	
	public Savepoint setSavepoint() throws SQLException {
		return connection.setSavepoint();
	}
	
	private void setTransaction(final boolean status) throws SQLException {
		final boolean flag = !status;
		connection.setAutoCommit(flag);
		if (status) {
			setSavepoint();
		}
	}
}
