package myhome.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myhome.domain.MemberDao;
import myhome.domain.MemberDto;


/**
 * 
 * @author miri
 * 사용자 로그인 로직
 *
 */
@WebServlet("/view/member/login_logic.do")
public class LoginLogic extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = dao.findByUserNameAndPassword(username, password);
		
		String message = dto != null ? dto.getNickname() + "님, 안녕하세요."
									: "로그인 실패!";
		
		if(dto!=null) {
			
			HttpSession session = request.getSession();
			
			session.setMaxInactiveInterval(600);
			// 600초(10분)동안 요청 없으면 로그아웃 (세션접속 끊기)
			
			session.setAttribute("currentDto", dto);
		}
		
		request.setAttribute("msg", message);
		request.setAttribute("result", dto != null);
		
		//쿠키 설정하기
		///////// 아이디 기억하기 ///////////
		
		Cookie cookie = new Cookie("rememberId", "");
		cookie.setPath("/");
		
		if(request.getParameter("checkbox") != null) {
			// 체크박스를 체크했으면
			cookie.setValue(username); // 쿠키값에 아이디 넣어주기
			cookie.setMaxAge(60*60*24*365); // 1년동안
		} 
		
		else {
			// 이전에 체크를 한번이라도 했다면 쿠키가 있을거라서 원래있던거 지워야함
			cookie.setMaxAge(0);
		}
		
		response.addCookie(cookie);
		
		
		// 결과페이지로 이동
		request.getRequestDispatcher("login_result.jsp").forward(request, response);
		
	}
	
}
