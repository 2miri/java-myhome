package myhome.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import myhome.board.DeleteLogic;
import myhome.board.ListLogic;
import myhome.board.ModifyActionLogic;
import myhome.board.ModifyLogic;
import myhome.board.ReadLogic;
import myhome.board.WriteLogic;
import myhome.domain.BoardDao;
import myhome.domain.BoardDto;
import myhome.domain.MemberDto;

/**
 * 
 * @author miri
 * borad 메뉴 컨트롤러
 *
 */
@WebServlet("/board/*")
public class MyController extends HttpServlet {
	
	private BoardDao dao = BoardDao.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("tab", "board");
		
		String uri = request.getRequestURI();
		String method = request.getMethod();
		
		String requestUri = uri.replace(request.getContextPath() + "/board", "");
		
		HttpSession session = request.getSession();
		MemberDto currentMemberDto = (MemberDto)session.getAttribute("currentDto");
		
		boolean result = false;
		
		switch(requestUri) {
		case "/write" : // 글 쓰기
			
			// GET POST상관없이 로그인 여부 확인
			if(currentMemberDto == null) { //로그인을 안했을때
				response.sendRedirect(request.getContextPath()+"/board/list");
				break;
			}
			
			switch(method) {
			case "GET" : // 글쓰기 페이지로
				request.getRequestDispatcher("/view/board/write.jsp").forward(request, response);
				break;
			case "POST" : // 글 저장
				result = new WriteLogic().doLogic(request, response);
				request.setAttribute("result", result);
				request.setAttribute("status", "write");
				request.getRequestDispatcher("/view/board/result.jsp")
						.forward(request, response);
				break;
			}
			
			break;
		
		case "/list" : // 글 목록
			
			new ListLogic().doLogic(request, response);
			request.getRequestDispatcher("/view/board/list.jsp")
					.forward(request, response);
			break;
		
		case "/delete" : // 글 삭제
			
			//세션 검사 - 글 주인이 맞는지
			result = new DeleteLogic().doLogic(request, response);
			
			request.setAttribute("result", result);
			request.setAttribute("status", "delete");
			request.getRequestDispatcher("/view/board/result.jsp")
				.forward(request, response);
			break;
		
		case "/modify" : // 글 수정
			
			switch (method) {
			case "GET": // 글 수정 페이지
				new ModifyLogic().doLogic(request, response);
				request.getRequestDispatcher("/view/board/modify.jsp")
						.forward(request, response);
				break;
			case "POST": // 글 수정 실행
				result = 
					new ModifyActionLogic().doLogic(request, response);
				
				JsonObject json = new JsonObject();
				json.addProperty("result", result);
				response.getWriter().write(json.toString());
				
				break;
			}
			break;
		
		case "/read" : // 글 조회
			
			new ReadLogic().doLogic(request, response);
			request.getRequestDispatcher("/view/board/read.jsp")
					.forward(request, response);
			break;
			
		default :
			request.getRequestDispatcher("/board/list")
			.forward(request, response);
		}
		
		
	}
}
