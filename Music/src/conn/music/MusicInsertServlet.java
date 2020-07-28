package conn.music;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import databaseConn.DBConnection;

@WebServlet("/MusicInsertServlet")
public class MusicInsertServlet extends HttpServlet{
	private static final long serialVersionUID = -4274700572038677000L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String realFolder = "C://upload"; //웹 어플리케이션상의 절대 경로
		String encType = "utf-8"; //인코딩 타입
		int maxSize = 50 * 1024 * 1024; //최대 업로드될 파일의 크기5Mb

		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		
		File file = multi.getFile("file");
		System.out.println(file);
		
		String singer = multi.getParameter("singer");
		String song = multi.getParameter("song");
		String album = multi.getParameter("album");
		
		System.out.println("뮤직인설트 부분   "+singer+"   "+song+"   "+album);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			int n = -1;
			
			conn = DBConnection.getConnection();
			
			String sql = "insert into music.musiclist (musicName, musicArtist, musicAlbum, musicImgSrc) value (?,?,?,?)";  
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, song);
			pstmt.setString(2, singer);
			pstmt.setString(3, album); 
			pstmt.setString(4, realFolder);
			n = pstmt.executeUpdate();
			
			if(n>0) {
				response.sendRedirect("ManagerWindow.jsp");
			} 
			else {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script language='javascript'>");
				out.println("alert('노래 추가 실패. 다시 시도해주세요.');");   //서블릿 작동 후 원래 페이지로 이동
				out.println("history.go(-1);");
				out.print("</script>");
				out.flush();  
			}
			

		} catch (ClassNotFoundException e) {
			System.out.println("뮤직인설트서블릿 오류");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("뮤직인설트서블릿 오류");
		}	finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch (Exception e) {
				e.getStackTrace();
				System.out.println("뮤직인설트서블릿 오류");
			}
			
		}

	}
		
}
