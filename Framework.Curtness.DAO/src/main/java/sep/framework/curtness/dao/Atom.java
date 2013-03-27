package sep.framework.curtness.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface Atom {
	void transaction(Connection connection, Transaction transaction) throws SQLException;
}
