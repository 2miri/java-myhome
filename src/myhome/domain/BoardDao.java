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
 * 게시글 DAO
 *
 */
public class BoardDao implements Dao<BoardDto> {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private BoardDao() {}
	private static BoardDao instance;
	public static BoardDao getInstance() {
		if(instance == null)
			instance = new BoardDao();
		return instance;
	}

	// 전체 글 개수
	public int selectTotalCount() {
		String sql = "SELECT COUNT(*) FROM board";
		try {
			conn = MyDataSource.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1); 
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return 0;
	}

	// no번 글의 조회수 증가
	public void updateHit(int no) {
		String sql = "UPDATE board SET hit = hit+1 WHERE no = ?";
		try {
			conn = MyDataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
	}

	@Override
	public BoardDto select(int no) {
		String sql = "SELECT board.no, board.content, board.title, board.hit, board.writer_no, board.regdate, member.nickname "
				+ "FROM board "
				+ "INNER JOIN member "
				+ "ON board.writer_no = member.no "
				+ "WHERE board.no = ?";
		try {
			conn = MyDataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setHit(rs.getInt("hit"));
				dto.setWriterNo(rs.getInt("writer_no"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setWriterNickname(rs.getString("nickname"));
				return dto;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return null;
	}

	@Override
	public List<BoardDto> selectAll() {
		return selectAll(0);
	}
	
	public List<BoardDto> selectAll(int beginRownum) {
		String sql = "SELECT brd.*, mem.nickname"
				+ " FROM ("
				+ " SELECT b.no, b.title, b.writer_no, b.regdate, b.hit"
				+ " FROM board AS b"
				+ " JOIN ("
				+ " SELECT no FROM board"
				+ " ORDER BY no DESC"
				+ " LIMIT ?, 10"
				+ " ) AS tmp"
				+ " ON tmp.no = b.no"
				+ " ) AS brd JOIN ( SELECT no, nickname FROM member"
				+ " ) AS mem"
				+ " ON brd.writer_no = mem.no"
				+ " ORDER BY brd.no DESC";
		
		List<BoardDto> list = new ArrayList<BoardDto>();
		
		try {
			conn = MyDataSource.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, beginRownum);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDto dto = new BoardDto();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setHit(rs.getInt("hit"));
				dto.setWriterNo(rs.getInt("writer_no"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setWriterNickname(rs.getString("nickname"));
				list.add(dto);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public boolean update(BoardDto t) {
		String sql = "UPDATE board SET title = ?, content = ? "
				+ "WHERE no = ?";
		
		try {
			conn = MyDataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, t.getTitle());
			ps.setString(2, t.getContent());
			ps.setInt(3, t.getNo());
			
			return ps.executeUpdate() > 0;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		return false;
	}

	@Override
	public boolean insert(BoardDto t) {
		String sql = "INSERT INTO board(title, content, writer_no) "
					+ "VALUES(?, ?, ?)";
		try {
			conn = MyDataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, t.getTitle());
			ps.setString(2, t.getContent());
			ps.setInt(3, t.getWriterNo());
			return ps.executeUpdate() > 0;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		return false;
	}

	@Override
	public boolean delete(int no) {
		String sql = "DELETE FROM board WHERE no = ?"; 
		try {
			conn = MyDataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			return ps.executeUpdate() > 0;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		return false;
	}

}
