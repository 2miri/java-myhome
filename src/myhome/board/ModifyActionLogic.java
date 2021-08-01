package myhome.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myhome.domain.BoardDao;
import myhome.domain.BoardDto;

/**
 * 
 * @author miri
 * 게시글 수정 post방식 (수정 실행)
 * 게시물 제목, 내용에 공란 없애주고 엔터 변환
 *
 */
public class ModifyActionLogic implements Logic{

	@Override
	public boolean doLogic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no")); 
		String title = request.getParameter("title").trim();
	    String content = request.getParameter("content")
	    		  			.trim()
	    		  			.replaceAll("\\n|\\r\\n", "<br/>");

		
		BoardDto dto = new BoardDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setNo(no);
		
		BoardDao dao = BoardDao.getInstance();
		return dao.update(dto);
	}
}





