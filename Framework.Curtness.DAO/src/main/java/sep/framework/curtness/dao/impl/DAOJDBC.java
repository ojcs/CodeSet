package sep.framework.curtness.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sep.framework.curtness.dao.DAO;
import sep.util.other.Properties;

public class DAOJDBC extends DAO {
	private final java.util.Properties info;
	private final String url;
	
	protected DAOJDBC(final String url) {
		this(url, new Properties());
	}
	
	protected DAOJDBC(final String url, final Properties info) {
		this.url = url;
		this.info = info.toJavaUtilProperties();
	}

	protected DAOJDBC(final String url, final String user, final String password) {
		this(url);
		if (user != null) {
            info.put("user", user);
        }
        if (password != null) {
            info.put("password", password);
        }
	}
	
	@Override
	public Connection openConnection() throws SQLException {
		return DriverManager.getConnection(url, info);
	}
}
