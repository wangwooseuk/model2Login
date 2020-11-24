package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.JoinService;
import vo.ActionForward;
import vo.Member;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Member member = new Member();
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String addr = request.getParameter("addr");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String name = request.getParameter("name");
		String nation = request.getParameter("nation");
		member.setId(id);
		member.setPasswd(passwd);
		member.setAddr(addr);
		member.setAge(age);
		member.setEmail(email);
		member.setGender(gender);
		member.setName(name);
		member.setNation(nation);
		JoinService joinService = new JoinService();
		int joinMember = joinService.insertMember(member);
		
		if(joinMember > 0) {
			HttpSession session	= request.getSession();
			session.setAttribute("loginMember", member);
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index.do");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}
}
