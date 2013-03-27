package sep.framework.curtness.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import sep.framework.curtness.dao.DAO;

public class DAOJNDI extends DAO {
	private final DataSource source;
	
	protected DAOJNDI(final String lookup) {
		try {
			source = (DataSource) new InitialContext().lookup("java:comp/env/" + lookup);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Connection openConnection() throws SQLException {
		return source.getConnection();
	}
}
