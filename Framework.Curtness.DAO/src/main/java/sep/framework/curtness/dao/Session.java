package sep.framework.curtness.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Collection;
import java.util.Set;

public final class Session implements Wrapper, AutoCloseable {
	public static int[] batchUpdate(final Connection connection, final Collection<String> sqlList) throws SQLException {
		return StatementUtil.batchUpdate(connection.createStatement(), sqlList);
	}
	
	public static int[] batchUpdate(final Connection connection, final String sql, final Set<Object[]> parametersList) throws SQLException {
		return StatementUtil.batchUpdate(connection.prepareStatement(sql), parametersList);
	}
	
	public static Transaction beginTransaction(final Connection connection) throws SQLException {
		Transaction.checkOpen(connection);
		return new Transaction(connection);
	}
	
	public static ResultSet function(final Connection connection, final String command, final Object... params) throws SQLException {
		return StatementUtil.function(connection.prepareCall(command), params);
	}
	
	public static ResultSet query(final Connection connection, final String sql, final Object... params) throws SQLException {
		return StatementUtil.query(connection.prepareStatement(sql), params);
	}
	
	public static Object scalar(final Connection connection, final String sql, final Object... params) throws SQLException { 
		return StatementUtil.scalar(connection.prepareStatement(sql));
	}
	
	public static int update(final Connection connection, final String sql, final Object... params) throws SQLException {
		return StatementUtil.update(connection.prepareStatement(sql), params);
	}
	
	private final Connection connection;
	
	Session(final Connection connection) {
		this.connection = connection;
	}
	
	public int[] batchUpdate(final Collection<String> sqlList) throws SQLException {
		return batchUpdate(connection, sqlList);
	}
	
	public int[] batchUpdate(final String sql, final Set<Object[]> parametersList) throws SQLException {
		return batchUpdate(connection, sql, parametersList);
	}
	
	public Transaction beginTransaction() throws SQLException {
		return beginTransaction(connection);
	}
	
	@Override
	public void close() throws SQLException {
		connection.close();
	}
	
	public ResultSet function(final String command, final Object... params) throws SQLException {
		return function(connection, command, params);
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public boolean isClosed() throws SQLException {
		return connection.isClosed();
	}
	
	@Override
	public boolean isWrapperFor(final Class<?> iface) throws SQLException {
		return connection.isWrapperFor(iface);
	}

	public ResultSet query(final String sql, final Object... params) throws SQLException {
		return query(connection, sql, params);
	}

	public Object scalar(final String sql, final Object... params) throws SQLException { 
		return scalar(connection, sql, params);
	}

	@Override
	public <T> T unwrap(final Class<T> iface) throws SQLException {
		return connection.unwrap(iface);
	}
	
	public int update(final String sql, final Object... params) throws SQLException {
		return update(connection, sql, params);
	}
}
