package myhome.domain;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 
 * @author miri
 * DB와 연결을 위한 클래스
 *
 */
public class MyDataSource {
	private static DataSource ds;
	static {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private MyDataSource() {}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
