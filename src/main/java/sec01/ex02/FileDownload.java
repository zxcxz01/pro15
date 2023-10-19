package sec01.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downLoad.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		
		System.out.println("이 구간은 다운로드 서블릿");
		
		String file_repo = "C:/file_repo";
		
		String fileName = (String) request.getParameter("fileName");
		
		System.out.println(fileName);
		
		OutputStream out = response.getOutputStream();
		
		String downFile = file_repo + "/" + fileName;
		
		System.out.println(downFile);
		
		File f = new File(downFile);
		
		System.out.println("파일 객체 : " + f);
		//한글로 된 파일명을 위해 파일 경로 인코딩
		
		String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
		
		response.setHeader("Cache-Control", "no-cache");
		
		
		
		response.addHeader("Content-disposition", "attachment; fileName=" + encodedFileName);
		
		
		//파일에 들어갈 파일 인풋스트림객체 생성
		FileInputStream in = new FileInputStream(f);
		
		byte[] buffer = new byte[1024 * 8];
		
		
		while(true) {
			int count = in.read(buffer);
			if(count == -1) {
				break;
			}
			out.write(buffer,0,count);
		}
		in.close();
		out.close();		
		
		
		
			
	}
}
