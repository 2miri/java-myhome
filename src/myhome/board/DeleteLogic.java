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
 * 게시글 삭제 로직
 *
 */
public class DeleteLogic implements Logic{
	
	@Override
	public boolean doLogic(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
				
		int no = Integer.parseInt(request.getParameter("no"));
		return BoardDao.getInstance().delete(no);
		
	}
}
