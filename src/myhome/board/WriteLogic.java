package myhome.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myhome.domain.BoardDao;
import myhome.domain.BoardDto;
import myhome.domain.MemberDto;

/**
 * 
 * @author miri
 * 게시글 작성 저장 로직 / 게시글 제목,내용에 공백 없애주고 엔터 변환 
 *
 */
public class WriteLogic implements Logic{
	
	@Override
	public boolean doLogic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title").trim();
		String content = request.getParameter("content")
				.trim()
				.replaceAll("\\n|\\r\\n", "<br/>");
		
		HttpSession session = request.getSession();
		int writerNo = ((MemberDto)session.getAttribute("currentDto")).getNo();
		
		BoardDto dto = new BoardDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriterNo(writerNo);
		
		BoardDao dao = BoardDao.getInstance();
		return dao.insert(dto);
	}
}
