package conn.play;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import databaseConn.DBConnection;

//@WebServlet("/PlayInsertServlet")
public class PlayInsertServlet {
	
	
//		response.setContentType("text/html; charset=UTF-8");
//		request.setCharacterEncoding("UTF-8");
						
//		String userID = request.getParameter("userid");
//		String userPW = request.getParameter("userpw");
	
	public void innerSong(String userID, String musicNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("플레이 인설트 서블릿 userID값 "+userID);
		System.out.println("플레이 인설트 서블릿 musicName값 "+musicNum);
		
		if(userID != null) {
		try {
		 conn = DBConnection.getConnection();
		
		 String sql =
			"insert into music.playlist (userid, musicNum, playNum) select ?,?, (SELECT COUNT(userid) FROM playlist where userid=?)+1";  
		 pstmt = conn.prepareStatement(sql);
		 pstmt.setString(1, userID);
		 pstmt.setString(2, musicNum); ///////////////////////////////////////// 
		 pstmt.setString(3, userID);
		 pstmt.executeUpdate(); 
			
		} catch (Exception e) {
			System.out.println("플레이인설트서블렛 오류");
			e.getStackTrace();		
					
		}
		finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch (Exception e) {
				e.getStackTrace();
				System.out.println("�����ʹݱ� ����");
			}
		}
		}else {
			System.out.println("음...");
			
		}
	}
}
