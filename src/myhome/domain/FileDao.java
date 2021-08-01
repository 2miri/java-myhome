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
 * 파일 DAO
 *
 */
public class FileDao implements Dao<FileDto> {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private FileDao() {}
	private static FileDao instance;
	public static FileDao getInstance() {
		if(instance == null)
			instance = new FileDao();
		return instance;
	}
	
	@Override
	public FileDto select(int no) {
		String sql = "SELECT f.*, member.nickname "
				+ "FROM ( "
				+ "	SELECT no, filename, filepath, regdate, count, uploader_no "
				+ "	FROM file "
				+ "	WHERE no = ? "
				+ ") AS f "
				+ "INNER JOIN member "
				+ "ON f.uploader_no = member.no ";
		FileDto dto = null;
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new FileDto();
				dto.setNo(rs.getInt("no"));
				dto.setFilename(rs.getString("filename"));
				dto.setFilepath(rs.getString("filepath"));
				dto.setCount(rs.getInt("count"));
				dto.setUploaderNo(rs.getInt("uploader_no"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setUploaderNickname(rs.getString("nickname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		
		
		return dto;
	}
	@Override
	public List<FileDto> selectAll() {
		String sql = "SELECT file.no, file.filename, file.uploader_no, file.regdate, file.count, member.nickname "
				+ "FROM file "
				+ "INNER JOIN member "
				+ "ON file.uploader_no = member.no "
				+ "ORDER BY file.regdate DESC";
		List<FileDto> list = new ArrayList<FileDto>();
		
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				FileDto dto = new FileDto();
				dto = new FileDto();
				dto.setNo(rs.getInt("no"));
				dto.setFilename(rs.getString("filename"));
				dto.setUploaderNo(rs.getInt("uploader_no"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setCount(rs.getInt("count"));
				dto.setUploaderNickname(rs.getString("nickname"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return list;
	}
	@Override
	public boolean update(FileDto t) {
		return false;
	}
	@Override
	public boolean insert(FileDto t) {
		String sql = "INSERT INTO file (filename, filepath, uploader_no ) "
				+ "VALUES(?, ?, ?)"; 
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, t.getFilename());
			ps.setString(2, t.getFilepath());
			ps.setInt(3, t.getUploaderNo());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps);
		}
		return false;
	}
	@Override
	public boolean delete(int no) {
		return false;
	}
	
}
