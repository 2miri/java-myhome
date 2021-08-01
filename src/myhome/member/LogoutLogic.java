package myhome.member;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author miri
 * 사용자 로그아웃 로직
 *
 */
@WebServlet ("/logout")
public class LogoutLogic extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		
		
		HttpSession session = req.getSession();
		System.out.println("세션 생성 시간 : " + sdf.format(session.getCreationTime()));
		System.out.println("마지막 요청 시간 : " + sdf.format(session.getLastAccessedTime()));
		System.out.println("JSESSIONID : " + session.getId());
		System.out.println("최대 대기 시간 : " + session.getMaxInactiveInterval() + "초");
		
		// session 세션 갱신
		session.invalidate();
		
		// 대문페이지로 리다이렉트
		resp.sendRedirect("/myhome");
		
	}
}
