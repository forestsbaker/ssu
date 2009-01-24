package org.kikaineko.ssu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectTest {
	public static void main(String[] args) throws Exception {
		try {
			String jdbcClass = args[0];
			String url = args[1];
			String user = null;
			String password = null;
			if (args.length == 4) {
				user = args[2];
				password = args[3];
			}
			System.out.println("db connect start");
			connectTest(jdbcClass, url, user, password);
			System.out.println("db connect end");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void connectTest(String jdbcClass, String url, String user,
			String password) throws Exception {
		Connection conn = null;
		Statement stmt = connect(jdbcClass, conn, url, user, password);
		close(conn, stmt, null);
	}

	private static Statement connect(String jdbcClass, Connection conn,
			String url, String user, String password)
			throws ClassNotFoundException, SQLException {
		Class.forName(jdbcClass);
		if (user != null && user.length() != 0) {
			conn = DriverManager.getConnection(url, user, password);
		} else {
			conn = DriverManager.getConnection(url);
		}
		return conn.createStatement();
	}

	private static void close(Connection conn, Statement stmt, ResultSet rset) {
		if (rset != null) {
			try {
				rset.close();
			} catch (SQLException e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
}
