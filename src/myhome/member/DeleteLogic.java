package myhome.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myhome.domain.MemberDao;

/**
 * 
 * @author miri
 * 사용자 탈퇴 로직
 *
 */
@WebServlet("/view/member/delete")
public class DeleteLogic extends HttpServlet{
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int no = Integer.parseInt(request.getParameter("no"));

      MemberDao dao = MemberDao.getInstance();
      boolean result = dao.delete(no);      
            
      request.setAttribute("result", result);
      request.getRequestDispatcher("delete.jsp")
            .forward(request, response);
      
   }
}



