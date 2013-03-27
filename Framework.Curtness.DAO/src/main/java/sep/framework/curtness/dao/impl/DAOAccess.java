package sep.framework.curtness.dao.impl;

import java.nio.file.Path;

import sep.framework.curtness.dao.DriverUtil;
import sep.util.other.EnvInfo;

public class DAOAccess extends DAOJDBC {
	private final static String baseURL;
	
	static {
		DriverUtil.loadDriver(DriverUtil.Driver_ODBC);
		if (EnvInfo.getOSName().equals("Windows")) {
			float osVersion = Float.valueOf(EnvInfo.getOSVersion());
			if (osVersion <= 5) {
				baseURL = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=";
			} else {
				baseURL = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=";
			}
		} else {
			baseURL = null;
		}
	}
	
	public DAOAccess(final Path path) {
		super(baseURL + path.toAbsolutePath().toString());
	}
}
