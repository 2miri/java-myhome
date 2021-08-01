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
 * 마이페이지 조회 로직
 *
 */
@WebServlet("/view/member/mypage")
public class MyPageLogic extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDao dao = MemberDao.getInstance();
		
		HttpSession session = req.getSession();
		MemberDto dto = (MemberDto)session.getAttribute("currentDto");
		
		if (dto != null) {
			
		dto = dao.select(dto.getNo());
		req.setAttribute("dto", dto);
		
		}
		
		req.getRequestDispatcher("/view/member/mypage.jsp")
			.forward(req, resp);
	}

}







