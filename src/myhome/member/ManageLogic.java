package myhome.member;

import java.io.IOException;
import java.util.List;

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
 * 관리자 페이지 로직
 *
 */
@WebServlet("/view/member/manage")
public class ManageLogic extends HttpServlet { 
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클라이언트가 관리자 유형인지?
		HttpSession session = request.getSession();
		MemberDto currentDto = (MemberDto)session.getAttribute("currentDto");
		
		//로그인을 안했거나 (세션이 만료되었거나), 회원 유형이 '관리자'가 아닌경우 로그인페이지로 리다이렉트
		if(currentDto == null || currentDto.getType() != 0 ) {
			// 이거 순서바뀌면 널포인트 뜰수도있으니까 널을 먼저보고 그다음에 관리자 타입을 봐야함
			
			response.sendRedirect("/myhome/view/member/login.jsp");
			return;
		}
		
		MemberDao dao = MemberDao.getInstance();
		List<MemberDto> list = dao.selectAll();
		
		request.setAttribute("memberList", list);
		request.getRequestDispatcher("manage.jsp").forward(request, response);
		
	}

}
