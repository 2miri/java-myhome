package myhome.domain;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 
 * @author miri
 * 
 * DAO 인터페이스
 * @param <T> 해당 dto를 제네릭하는 dao로 제한을 둠
 */
public interface Dao<T extends Dto> {
	
	T select(int no);
	List<T> selectAll();
	boolean update(T t);
	boolean insert(T t);
	boolean delete(int no); 
	
	default Connection getConnection() throws SQLException {
		return MyDataSource.getConnection();
	}
	
	default void close(Connection conn, PreparedStatement ps) {
		close(conn, ps, null);
	}
	default void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	static void loadDriverClass() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}






