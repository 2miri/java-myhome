package myhome.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myhome.domain.MemberDao;
import myhome.domain.MemberDto;

/**
 * 
 * @author miri
 * 사용자 가입 로직
 *
 */
@WebServlet("/view/member/join_logic")
public class JoinLogic extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		int type = Integer.parseInt(request.getParameter("type"));
		
		MemberDto dto = new MemberDto();
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setNickname(nickname);
		dto.setType(type);
		
		MemberDao dao = MemberDao.getInstance();
		boolean result = dao.insert(dto);
		
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("/view/member/join_result.jsp");
		rd.forward(request, response);
	}
	
}
