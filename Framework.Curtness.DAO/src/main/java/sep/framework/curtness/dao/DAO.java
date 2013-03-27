package sep.framework.curtness.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Wrapper;
import java.util.Set;

public abstract class DAO {
	public static void close(final Connection connection) throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
	
	public static void close(final Session session) throws SQLException {
		if (session != null) {
			close(session.getConnection());
		}
	}

	public static void close(final ResultSet set) throws SQLException {
		if (set != null && !set.isClosed()) {
			close(set.getStatement());
			set.close();
		}
	}
	
	public static void close(final Statement statement) throws SQLException {
		if (statement != null  && !statement.isClosed()) {
			close(statement.getConnection());
			statement.close();
		}
	}
	
	public static void close(final Wrapper wrapper) throws SQLException {
		if (wrapper instanceof ResultSet) {
			close((ResultSet) wrapper);
		} else if (wrapper instanceof Statement) {
			close((Statement) wrapper);
		} else if (wrapper instanceof Session) {
			close((Session) wrapper);
		} else if (wrapper instanceof Connection) {
			close((Connection) wrapper);
		}
	}
	
	public int[] batchUpdate(final Set<String> sqlList) throws SQLException {
		return openSession().batchUpdate(sqlList);
	}
	
	public int[] batchUpdate(final String sql, final Set<Object[]> parametersList) throws SQLException {
		return openSession().batchUpdate(sql, parametersList);
	}
	
	public ResultSet function(final String command, final Object... params) throws SQLException {
		return openSession().function(command, params);
	}
	
	public abstract Connection openConnection() throws SQLException;
	
	public final Session openSession() throws SQLException {
		return new Session(openConnection());
	}
	
	public ResultSet query(final String sql, final Object... params) throws SQLException {
		return openSession().query(sql, params);
	}
	
	public Object scalar(final String sql, final Object... params) throws SQLException {
		return openSession().scalar(sql, params);
	}

	public int update(final String sql, final Object... params) throws SQLException {
		return openSession().update(sql, params);
	}
}
