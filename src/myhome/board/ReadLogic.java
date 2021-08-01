package myhome.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myhome.domain.BoardDao;
import myhome.domain.BoardDto;
import myhome.domain.MemberDto;

/**
 * 
 * @author miri
 * 게시글 조회 로직 - 읽었으면 조회수 증가되게 처리함
 *
 */
public class ReadLogic implements Logic{ 
	@Override
	public boolean doLogic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardDao dao = BoardDao.getInstance();
		
		//조회수 증가 조건 걸기
		
		HttpSession session = request.getSession();
		MemberDto currentMember = (MemberDto)session.getAttribute("currentDto");
		
		String cookieName = (currentMember != null? currentMember.getNo() : "anonymous")
							+"_"+no;
		
		Cookie[] cookies = request.getCookies();
		boolean cookieExists = false;
		
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(cookieName)) {
				cookieExists = true;
				break;
			}
		}
		
		if(!cookieExists) {
			
			dao.updateHit(no);
			Cookie cookie = new Cookie(cookieName, String.valueOf(System.currentTimeMillis()));
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24*90); // 90일
			response.addCookie(cookie);
		}
		
		BoardDto dto = dao.select(no);
		request.setAttribute("dto", dto);
		return true;
	}

}



