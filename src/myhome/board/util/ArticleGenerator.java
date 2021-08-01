package myhome.board.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myhome.domain.BoardDao;
import myhome.domain.BoardDto;

/**
 * 
 * @author miri
 * 페이징 확인을 위해 게시글을 많이 작성하기 위한 임시 로직
 *
 */
@WebServlet("/create")
public class ArticleGenerator extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BoardDao dao = BoardDao.getInstance();
		int[] arr = {9,12,13,14};
		
		for(int i = 0; i < 200; ++i) {
			
			BoardDto dto = new BoardDto();
			dto.setTitle((i+1)+"번 글 제목임!");
			dto.setContent((i+1)+"번 글 내용임!");
			dto.setWriterNo(arr[(int)Math.random() * arr.length]);
			dao.insert(dto);
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
