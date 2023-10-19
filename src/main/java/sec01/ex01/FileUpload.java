package sec01.ex01;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String encoding = "utf-8";
		
		
		//DiskFileItemFactory
		//기본 FileItemFactory 구현입니다. 이 구현은 작은 항목의 경우 콘텐츠를 메모리에, 
		//큰 항목의 경우 디스크의 임시 파일에 보관하는 FileItem 인스턴스를 만듭니다. 
		//콘텐츠가 디스크에 저장되는 크기 임계값은 임시 파일이 생성될 디렉터리와 마찬가지로 구성 가능합니다.
		
		
		
		File currentPath = new File("C:/file_repo");
		// 파일을 올리는 공간에다가 파일을 올리고자하는 용량과 경로 설정
		// 파일 공간에 대한 설정을 위한 클래스 DiskFileItemFactory
		
		// 파일 설정과 관련된 여러가지 일을 하는 클래스, 파일과 관련된 내용을 항목(item) 형태로 저장함
		// 파일 아이템이란 예를 들어, 파일이름, 파일 크기
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// DiskFileItemFactory 클래스의 setSizeThreshold() 메서드는 메모리에 보관할 임시 파일의 크기 임계값을 설정하는
		// 데 사용됩니다. 이 메서드에서 사용되는 단위는 바이트(Byte)입니다.
		// setSizeThreshold() 메서드에 전달하는 매개변수는 바이트 단위로 지정되며,
		// 임시 파일의 크기가 이 임계값보다 크면 디스크에 파일이 저장됩니다. 임시 파일의 크기가 임계값보다 작으면 메모리에 보관됩니다.
		// 이렇게 함으로써 작은 파일은 메모리에 보관되어 더 빠른 처리가 가능하고, 큰 파일은 디스크에 저장되어 메모리 사용량을 줄일 수 있습니다.
		factory.setSizeThreshold(1024 * 1024);// 1024 byte * 1024 = 1KB
		
		
		
		// 파일을 올리는 행위를 하는 클래스
		
		ServletFileUpload upload =new ServletFileUpload(factory);
		
		System.out.println("파일 업로드 객체 : " + upload);
		
		
		
		
		// FileItem 은 인코딩 타입이 multipart/form-data 일 때 , POST로 요청시 받을 수 있는 항목 클래스
		
		
		
		try {
			List<FileItem> items=upload.parseRequest(request);
			System.out.println("items란 : " +items);
			System.out.println("items 크기 : " + items.size());			
			
			
			for(int i = 0; i < items.size(); i++) {
				System.out.println("---------------------");
				FileItem fileItem=(FileItem) items.get(i);
				
				// 폼필드 내용만 가져옴
				if (fileItem.isFormField()) {
					System.out.println("여기는 폼필드");
					System.out.print("필드 아이템 필드명=필드값");
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
				}else {
					System.out.println("매개변수 이름 :" + fileItem.getFieldName());
					System.out.println("파일이름 :" + fileItem.getName());
					System.out.println("파일 크기" + fileItem.getSize() + "bytes");
					
					
					if (fileItem.getSize() > 0) {
						System.out.println("파일 아이템 이름 : " + fileItem.getName());
						
						// 이전 버전의 익스플로러에서는 전체 경로를 가져오는 경우에 대비...중간에 혹시 폴더명이 끼여있는지 확인하는 코드
						//중간에 파일명에 //나 \가 포함될 경우 예외가 발생함, 예외가 발생하지 않도록 하는 코드 추가함
						int idx = fileItem.getName().lastIndexOf("\\");
						System.out.println("인덱스" + idx);
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");    //익스플로러의 경우 대비
							System.out.println(idx);
						}
						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("현재 경로 : " + currentPath);
						System.out.println("파일명 : " + fileName);
						File uploadFile = new File(currentPath + "\\" + fileName);
						fileItem.write(uploadFile);
					}
					
				}
				
			}
			
			
		} catch (Exception e) {
			
			System.out.println("파일 업로드 요청시 에러남");
		}
		
		
		
		
		
	}

}
