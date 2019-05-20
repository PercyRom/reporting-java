package pe.com.vendemas.weblogistica.dao.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pe.com.vendemas.weblogistica.util.ErrorUtil;

public class DatabaseUtil {
	
	private static final Logger logger = LogManager.getLogger(DatabaseUtil.class);

	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error(ErrorUtil.formatError(e));
		}
		conn = null;
	}
	
	public static void close(CallableStatement csmt) {
		try {
			if (csmt != null) {
				csmt.close();
			}
		} catch (SQLException e) {
			logger.error(ErrorUtil.formatError(e));
		}
		csmt = null;
	}
	
	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.error(ErrorUtil.formatError(e));
		}
		rs = null;
	}

}
