package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.LoginDAO;
import vo.Member;

public class LoginService {

	public Member getLoginMember(String id, String passwd) throws Exception {
		LoginDAO loginDAO = LoginDAO.getInstance();
		Connection con = getConnection();
		loginDAO.setConnection(con);
		Member loginMember = loginDAO.selectLoginMember(id, passwd);
		
		return loginMember;
	}
	
}
