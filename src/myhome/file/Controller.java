package myhome.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import myhome.domain.FileDao;
import myhome.domain.FileDto;
import myhome.domain.MemberDto;

/**
 * 
 * @author miri
 * 파일 메뉴 컨트롤러
 *
 */
@WebServlet("/file/*")
public class Controller extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("tab", "file");
		
		String uri = request.getRequestURI();
		String method = request.getMethod();
		String requestUri = uri.replace(request.getContextPath() + "/file", "");
		
		FileDao dao = FileDao.getInstance();
		FileDto dto = new FileDto();
		
		HttpSession session = request.getSession();
		MemberDto currentMember = (MemberDto)session.getAttribute("currentDto");
		
		switch(requestUri) {
		case "/list":
			
			// File DB 목록 가져오기
			dao = FileDao.getInstance();
			List<FileDto> list = dao.selectAll();
			
			// request 애트리뷰트 추가
			request.setAttribute("list", list);
			
			// 뷰로 포워드
			request.getRequestDispatcher("/view/file/list.jsp")
				.forward(request, response);
			
			
			break;
			
		case "/upload":
			switch(method) {
			case "GET":
				// upload <form> 뷰로 이동
				
				request.getRequestDispatcher("/view/file/upload.jsp")
					.forward(request, response);
				
				break;
				
			case "POST":
				// 파라미터(파일)를 받아서 이를 DB, 서버 디렉토리에 파일 저장
								
				int maxPostSize = 100 * 1000000; // 100 mb
				String saveDirectory = request.getServletContext().getRealPath("/storage");
				System.out.println("storage의 진짜 경로 : " + saveDirectory);
				
				MultipartRequest mr = new MultipartRequest(
					request, // 파라미터가 들어있는 request 객체
					saveDirectory, // (파라미터로 전달된) 파일을 저장할 서버측의 디렉토리 경로
					maxPostSize, // 허용할 파일의 최대 byte 수 
					"utf-8", // 파일 이름, 일반 파라미터의 인코딩
					new DefaultFileRenamePolicy() // 중복 파일명의 경우 어떤 정책으로 이름 재설정 할 것인지
					);
				
				File file1 = mr.getFile("file1");
				File file2 = mr.getFile("file2");
				
				if(file1 != null) {
					// file1 파라미터 파일의 업로드 당시 이름
					String originName = mr.getOriginalFileName("file1");
					
					// file1 파라미터 파일의 서버에 저장된 실제 이름
					String realName = mr.getFilesystemName("file1");
					
					// 업로드 파일 있으면 DB에 저장
					dto = new FileDto();
					dto.setFilename(realName);
					dto.setFilepath(saveDirectory);
					dto.setUploaderNo(currentMember.getNo());
					dao.insert(dto);
					
					request.setAttribute("originName1", originName);
					request.setAttribute("realName1", realName);
					// 파일크기 보내기 (byte)
					request.setAttribute("fileSize1", file1.length());
				}
				
				if(file2 != null) {
					// file2 파라미터 파일의 업로드 당시 이름
					String originName = mr.getOriginalFileName("file2");
					
					// file2 파라미터 파일의 서버에 저장된 실제 이름
					String realName = mr.getFilesystemName("file2");
					
					// 업로드 파일 있으면 DB에 저장
					dto = new FileDto();
					dto.setFilename(realName);
					dto.setFilepath(saveDirectory);
					dto.setUploaderNo(currentMember.getNo());
					dao.insert(dto);
					
					request.setAttribute("originName2", originName);
					request.setAttribute("realName2", realName);
					// 파일크기 보내기 (byte)
					request.setAttribute("fileSize2", file2.length());
				}
				
				request.getRequestDispatcher("/view/file/upload_result.jsp")
						.forward(request, response);
				break;
			}
			
			break;
			
		case "/download":
			InputStream fIn = null;
			OutputStream fOut = null;
			
			try {
			
			String fileNo = request.getParameter("no");
			
			// GET요청은 반드시 파라미터가 있는지 없는지 확인해줘야함
			if(fileNo == null || fileNo.isEmpty()) {
				throw new IllegalArgumentException();
			}
			
			int no = Integer.parseInt(fileNo);
			dao = FileDao.getInstance();
			
			dto = new FileDto();
			dto = dao.select(no);
			
			if(dto == null) {
				throw new IllegalArgumentException();
			}
			
			 File file = new File(dto.getFilepath(), dto.getFilename());
			fIn = new FileInputStream(file);
			// 이것과 동일 : FileInputStream fIn = new FileInputStream(dto.getFilepath()+dto.getFilename());
			
			 byte[] temp = new byte[(int)file.length()];
			 
			 fIn.read(temp);
			 
			 // response 객체 초기화 (응답헤더, 응답바디를 재설정)
			 response.reset();
			 
			 // 응답 헤더 설정 !! 아주 중요
			 response.setContentType("application/octet-stream");
			 response.setContentLength((int)file.length());
			 response.setHeader(
	                  "content-disposition", 
	                  "attachment; filename=" + URLEncoder.encode(dto.getFilename(),"UTF-8").replace("+", " "));
			 // 응답 바디 작성
			 fOut = response.getOutputStream();
			 fOut.write(temp);
			 fOut.flush();
			 
			
			} catch (IllegalArgumentException e) {
				response.sendRedirect("/myhome/file/list");
			} finally {
				try {
					if(fOut != null) fOut.close();
					if(fIn != null) fIn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			break;
			
		default:
			request.getRequestDispatcher("/file/list")
				.forward(request, response);
		}
	}
}
