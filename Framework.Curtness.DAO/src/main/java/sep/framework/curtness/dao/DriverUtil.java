package sep.framework.curtness.dao;

import java.sql.Driver;

public final class DriverUtil {
	/** [JavaDB]http://db.apache.org/derby/ {@linkplain org.apache.derby.jdbc.EmbeddedDriver} */
	public static final String Driver_ApacheDerby = "org.apache.derby.jdbc.EmbeddedDriver";
	
	/** [HyperSQLDB]http://sourceforge.net/projects/hsqldb/files/hsqldb/ {@linkplain org.hsqldb.jdbc.JDBCDriver} */
	public static final String Driver_HSQLDB = "org.hsqldb.jdbc.JDBCDriver";
	
	/** [Microsoft SQL Server]http://go.microsoft.com/fwlink/?LinkId=245496 {@linkplain com.microsoft.sqlserver.jdbc.SQLServerDriver} */
	public static final String Driver_MSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	/** [Microsoft SQL Server for jTDS]http://sourceforge.net/projects/jtds/ {@linkplain net.sourceforge.jtds.jdbc.Driver}*/
	public static final String Driver_MSSQL_jTDS = "net.sourceforge.jtds.jdbc.Driver";
	
	/** http://dev.mysql.com/downloads/connector/j/ {@link com.mysql.jdbc.Driver}*/
	public static final String Driver_MySQL = "com.mysql.jdbc.Driver";
	/** [JDBC-ODBC] */
	public static final String Driver_ODBC = "sun.jdbc.odbc.JdbcOdbcDriver";
	
	/** [Oracle Database]http://www.oracle.com/technetwork/database/features/jdbc/index-091264.html {@linkplain oracle.jdbc.driver.OracleDriver} */
	public static final String Driver_Oracle = "oracle.jdbc.driver.OracleDriver";
	/** [PostgreSQL]http://jdbc.postgresql.org/ {@linkplain org.postgresql.Driver}*/
	public static final String Driver_PostgreSQL = "org.postgresql.Driver";
	/** [SQLite-JDBC] https://bitbucket.org/xerial/sqlite-jdbc/ {@linkplain org.sqlite.JDBC} */
	public static final String Driver_SQLite = "org.sqlite.JDBC";
	
	public static boolean loadDriver(final String className) {
		if (className != null) {
			try {
				return Class.forName(className).isAssignableFrom(Driver.class);
			} catch (ClassNotFoundException e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	private DriverUtil() {
	}
}
