package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import vo.Member;
import static db.JdbcUtil.*;

public class LoginDAO {
	private static LoginDAO loginDAO;
	private Connection con;
	
	private LoginDAO() {
	}

	public static LoginDAO getInstance() {
		if(loginDAO == null) {
			loginDAO = new LoginDAO();
		}
		return loginDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public Member selectLoginMember(String id, String passwd) {
		Member loginMember = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select * from users where id = ? and passwd = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loginMember = new Member();
				loginMember.setAddr(rs.getString("addr"));
				loginMember.setAge(rs.getInt("age"));
				loginMember.setEmail(rs.getString("email"));
				loginMember.setGender(rs.getString("gender"));
				loginMember.setId(rs.getString("id"));
				loginMember.setName(rs.getString("name"));
				loginMember.setNation(rs.getString("nation"));
				loginMember.setPasswd(rs.getString("passwd"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(rs);
				close(pstmt);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return loginMember;
	}

	public int selectJoinMember(Member article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int insertCount = 0;
		
		try {
			sql = "insert into users values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getId());
			pstmt.setString(2, article.getPasswd());
			pstmt.setString(3, article.getAddr());
			pstmt.setInt(4, article.getAge());
			pstmt.setString(5, article.getEmail());
			pstmt.setString(6, article.getGender());
			pstmt.setString(7, article.getName());
			pstmt.setString(8, article.getNation());
			
			insertCount = pstmt.executeUpdate();
		} catch(Exception ex) {
			System.out.println("joinInsert 에러: " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
	}
	
}
