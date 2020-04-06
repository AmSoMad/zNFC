package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUill {
	public static Connection getConnection() { // db 연결을 위한 메소드
		Connection conn = null;
		try {
		
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mDB");
			conn = ds.getConnection();
			conn.setAutoCommit(false); // 트랜잭션처리위해서
			///////////////////////////////////////////////////////////////////// 연결된상태
			System.out.println("Connetion success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Connection close");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
				System.out.println("Statement close");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				System.out.println("ResultSet close");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void commit(Connection conn) {
		if (conn != null) {
			try {
				conn.commit();
				System.out.println("commit success");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
				System.out.println("rollback success");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}