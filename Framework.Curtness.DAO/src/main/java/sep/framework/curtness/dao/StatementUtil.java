package sep.framework.curtness.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Set;

public final class StatementUtil {
	/** Preparation SQL batch */
	public static int[] batchUpdate(final PreparedStatement statement, final Set<Object[]> parametersList) throws SQLException {
		for (final Object[] parameters : parametersList) {
			bindParameters(statement, parameters);
			statement.addBatch();
		}
		return executeBatch(statement);
	}

	/** Static SQL batch */
	public static int[] batchUpdate(final Statement statement, final Collection<String> sqlList) throws SQLException {
		for (String sql : sqlList) {
			statement.addBatch(sql);
		}
		return executeBatch(statement);
	}
	
	public static PreparedStatement bindParameters(final PreparedStatement statement, final Object... parameters) throws SQLException {
		statement.clearParameters();
		int i = 1;
		for (final Object parameter : parameters) {
			statement.setObject(i++, parameter);
		}
		return statement;
	}
	
	public static int[] executeBatch(final Statement statement) throws SQLException {
		final Transaction transaction = Session.beginTransaction(statement.getConnection());
		try {
			return statement.executeBatch();
		} catch (SQLException e) {
			transaction.rollback();
			throw e;
		} finally {
			transaction.commit();
			transaction.close();
		}
	}
	
	public static ResultSet function(final CallableStatement statement, final Object... params) throws SQLException {
		return bindParameters(statement, params).executeQuery();
	}
	
	public static final ResultSet query(final PreparedStatement statement, final Object... params) throws SQLException {
		return bindParameters(statement, params).executeQuery();
	}
	
	public static Object scalar(final PreparedStatement statement, final Object... params) throws SQLException {
		bindParameters(statement, params);
		return scalar(statement);
	}
	
	public static Object scalar(final Statement statement) throws SQLException {
		ResultSet set = null;

		Object obj = null;
		try {
			set = statement.getResultSet();
			obj = set.getObject(1);
		} finally {
			DAO.close(set);
		}
		return obj;
	}

	public static final int update(final PreparedStatement preparedStatement, final Object... params) throws SQLException {
		PreparedStatement statement = bindParameters(preparedStatement, params);
		final int rows = statement.executeUpdate();
		DAO.close(statement);
		return rows;
	}
	
	private StatementUtil() {
	}
}
