package myhome.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myhome.domain.MemberDao;
import myhome.domain.MemberDto;

/**
 * 
 * @author miri
 * 회원정보 수정화면 로직
 *
 */
@WebServlet("/view/member/modify")
public class ModifyLogic extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDao dao = MemberDao.getInstance();
		
		HttpSession session = req.getSession();
		MemberDto dto = (MemberDto)session.getAttribute("currentDto");
		
		dto = dao.select(dto.getNo());
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/view/member/modify.jsp")
			.forward(req, resp);
	}

}






