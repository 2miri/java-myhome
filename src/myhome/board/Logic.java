package myhome.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author miri
 * 로직에서 사용할 공통 메서드를 인터페이스로 만들어서 선언
 *
 */
public interface Logic {
	public boolean doLogic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
}
