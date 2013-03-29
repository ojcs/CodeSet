package sep.framework.curtness.dao.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import sep.framework.curtness.dao.DAO;

public class DAOH2Test {
	private static DAO dao = new DAOJDBC("jdbc:h2:test", "sa", "");

	@BeforeClass
	public static void setUpBeforeClass() throws SQLException, IOException {
		Files.deleteIfExists(Paths.get("test.h2.db"));
		Files.deleteIfExists(Paths.get("test.trace.db"));
		dao.update("CREATE TABLE TEST_MEM(ID INT PRIMARY KEY,NAME VARCHAR(255))");
		dao.update("INSERT INTO TEST_MEM VALUES(1, 'Hello_Mem')");
	}

	@Test
	public void testQuery() throws SQLException {
		try (ResultSet set = dao.query("SELECT ID,NAME FROM TEST_MEM")) {
			while (set.next()) {
				System.out.println(set.getInt("ID") + "," + set.getString("NAME"));
			}
		}
	}

	@Test
	public void testUpdate() throws SQLException {
		List<Object[]> params = new ArrayList<>();
		params.add(new Object[] { 2, "TEST" });
		params.add(new Object[] { 3, "TEST" });
		params.add(new Object[] { 4, "TEST" });
		params.add(new Object[] { 5, "TEST" });
		dao.update("INSERT INTO TEST_MEM VALUES(?,?)", params);
	}
}
