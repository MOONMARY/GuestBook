package guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestBookDaoImpl extends BaseDao 
	implements GuestBookDao {

	//	Constructor
	public GuestBookDaoImpl(String dbUser, String dbPass) {
		super(dbUser, dbPass);
	}
	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "SELECT no, name, password, content, reg_date "
					+ "FROM guestbook "
					+ "ORDER BY reg_date DESC;";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Integer no = rs.getInt(1);
				String lastName = rs.getString(2);
				String firstName = rs.getString(3);
				String email = rs.getString(4);
				Date createdAt = rs.getDate(5);
				
				GuestBookVo info = new GuestBookVo(no, 
						name,
						password,
						content,
						regDate);
				list.add(info);
			}
		} catch (Exception e) {
			System.err.println("ERROR:" + e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				System.err.println("ERROR:" + e.getMessage());
			}
		}
		return list;
	}
	public boolean insert(GuestBookVo vo) {
		int insertedCount = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "INSERT INTO guestbook "
					+ "(name, password, content) "
					+ "VALUES(?, ?, ?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(3, vo.getContent());

			insertedCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("ERROR:" + e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				System.err.println("ERROR:" + e.getMessage());
			}
		}
		
		return 1 == insertedCount;
	}
	public boolean delete(Integer no, String password) {
		int deletedCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM guestbook WHERE no=? AND password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, password);
			deletedCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				System.err.println("ERROR:" + e.getMessage());
			}
		}
		return 1 == deletedCount;
	}

}
