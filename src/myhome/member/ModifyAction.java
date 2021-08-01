package myhome.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import myhome.domain.MemberDao;
import myhome.domain.MemberDto;

/**
 * 
 * @author miri
 * 회원정보 수정 실행 로직
 *
 */
@WebServlet("/member/modifyAction")
public class ModifyAction extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		MemberDto currentMember = (MemberDto)session.getAttribute("currentDto");
		
		JsonObject json = new JsonObject();
		resp.setCharacterEncoding("UTF-8");
				
		if(currentMember == null) {
			// {result : false} 를 응답
			json.addProperty("result", false);
			resp.getWriter().write(json.toString());
			return;
		}
		
		MemberDto newDto = new MemberDto();
		newDto.setNo(currentMember.getNo());
		newDto.setNickname(req.getParameter("nickname"));
		newDto.setPassword(req.getParameter("password"));
		newDto.setType(Integer.parseInt(req.getParameter("type")));
		
		MemberDao dao = MemberDao.getInstance();
		boolean result = dao.update(newDto);
		
		if(result) {
			// 수정이 성공하면 바로 상단닉네임뜨는거 바뀌게 하기
			session.setAttribute("currentDto", dao.select(newDto.getNo()));
			json.addProperty("new_nickname", newDto.getNickname());
		}
		
		// {result:ture/false} 를 응답
		json.addProperty("result", result);
		resp.getWriter().write(json.toString());
		
	}
}
