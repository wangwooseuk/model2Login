package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.LoginDAO;
import vo.Member;

public class JoinService {

	public int insertMember(Member member) {
		LoginDAO loginDAO = LoginDAO.getInstance();
		Connection con = getConnection();
		loginDAO.setConnection(con);
		int joinMember = loginDAO.insertMember(member);
		
		return joinMember;
	}

}
