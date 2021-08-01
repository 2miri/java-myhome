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
 * 게시글 수정 get방식 로직 (수정할 내용 작성)
 * 엔터 변환
 *
 */
public class ModifyLogic implements Logic{
	@Override
	public boolean doLogic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		BoardDto dto = BoardDao.getInstance().select(no); 
		
		String content = dto.getContent();
		content = content.replaceAll("<br/>", "\n");
		dto.setContent(content);
		
		HttpSession session = request.getSession();
		MemberDto currentMemberDto = (MemberDto)session.getAttribute("currentDto");
		
		// 로그인한 유저가 글쓴이가 맞는지 확인
		if(currentMemberDto.getNo() != dto.getWriterNo()) {
			// 글쓴이가 아니라면 no번 글 조회 페이지로 리다이렉트
			response.sendRedirect("/board/read?no="+no);
			return false;
		}
		
		request.setAttribute("dto", dto);
		return true;
	}

}
