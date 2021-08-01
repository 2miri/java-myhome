package myhome.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author miri
 * 사용자 DAO
 *
 */
public class MemberDao implements Dao<MemberDto> {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	private static MemberDao instance;

	private MemberDao() {
		// Dao.loadClassDriver();
	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}

	@Override
	public MemberDto select(int no) {
		// no, username, nickname, password, type, regdate
		String sql = "SELECT no, username, nickname, password, type, regdate FROM member WHERE no = ?";
		MemberDto dto = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();

			while (rs.next()) {
				dto = new MemberDto();
				dto.setNo(rs.getInt("no"));
				dto.setUsername(rs.getString("username"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPassword(rs.getString("password"));
				dto.setType(rs.getInt("type"));
				dto.setRegdate(rs.getString("regdate"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return dto;
	}

	@Override
	public List<MemberDto> selectAll() {
		// no, username, nickname, password, type, regdate
		String sql = "SELECT no, username, nickname, password, type, regdate"
					+ " FROM member";

		List<MemberDto> list = new ArrayList();
		MemberDto dto = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				dto = new MemberDto();
				dto.setNo(rs.getInt("no"));
				dto.setUsername(rs.getString("username"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPassword(rs.getString("password"));
				dto.setType(rs.getInt("type"));
				dto.setRegdate(rs.getString("regdate"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}

		return list;
	}

	@Override
	public boolean insert(MemberDto t) {
		// no, username, nickname, password, type, regdate
		String sql = "INSERT INTO member " + "(no, username, nickname, password, type, regdate) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		boolean result = false;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getNo());
			ps.setString(2, t.getUsername());
			ps.setString(3, t.getNickname());
			ps.setString(4, t.getPassword());
			ps.setInt(5, t.getType());
			ps.setString(6, t.getRegdate());

			result = ps.executeUpdate() > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}

		return result;
	}

	@Override
	public boolean update(MemberDto t) {
		// no, username, nickname, password, type, regdate
		String sql = "UPDATE member SET nickname = ?, password = ?, type = ?"
				+ " WHERE no = ?";
		boolean result = false;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, t.getNickname());
			ps.setString(2, t.getPassword());
			ps.setInt(3, t.getType());
			ps.setInt(4, t.getNo());

			result = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}

		return result;
	}

	@Override
	public boolean delete(int no) {
		String sql = "DELETE FROM member WHERE no = ?";
		boolean result = false;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);

			result = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}

		return result;
	}
	
	public MemberDto findByUserNameAndPassword(String username, String password) {
		String sql = "SELECT no, username, nickname, password, type, regdate"
					+" FROM member"
					+" WHERE username = ? AND password = ?";
		// AND = &&
		MemberDto dto = null;

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				dto = new MemberDto();
				dto.setNo(rs.getInt("no"));
				dto.setUsername(rs.getString("username"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPassword(rs.getString("password"));
				dto.setType(rs.getInt("type"));
				dto.setRegdate(rs.getString("regdate"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return dto;
	}
	
	
	public static void main(String[] args) {
		
		MemberDao dao = MemberDao.getInstance();
		List<MemberDto> list = dao.selectAll();
		for(MemberDto i : list) {
			System.out.println(i.getUsername());
			
		}
	}
}