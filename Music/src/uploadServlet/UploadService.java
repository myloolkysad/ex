package uploadServlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UploadService")
public class UploadService extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("file");
		System.out.println(fileName);

		ServletContext context = getServletContext(); // 어플리케이션에 대한 정보를 ServletContext 객체가 갖게 됨. (서버의 절대경로를 구하는 데 필요)
		String saveDir = context.getRealPath("C://Upload"); // 절대경로를 가져옴
		System.out.println("절대경로 >> " + saveDir);

		int maxSize = 3 * 1024 * 1024; // 3MB
		String encoding = "UTF-8";

		// saveDir: 경로
		// maxSize: 크기제한 설정
		// encoding: 인코딩타입 설정
		// new DefaultFileRenamePolicy(): 동일한 이름일 경우 자동으로 (1),(2)..붙게 해줌

		boolean isMulti = ServletFileUpload.isMultipartContent(request);// boolean타입. ??????
		if (isMulti) {
			MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, encoding,
					new DefaultFileRenamePolicy());

			String author = multi.getParameter("author");
			String title = multi.getParameter("title");
			String file = multi.getFilesystemName("file");

		} else {
			System.out.println("일반 전송 form 입니다.");
		}

	}

}
