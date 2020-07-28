package conn.play;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseConn.DBConnection;
import dto.MemberDto;

public class PlayDeleteServlet {
		
	
	public String deletePlaySong(String musicNum, String userid, String playNum) {
		System.out.println("플레이딜리드서블릿 뮤직넘 ::"+musicNum);
		System.out.println("플레이딜리드서블릿 유저아이디 ::"+userid);
		System.out.println("플레이딜리드서블릿 플레이넘 ::"+playNum);
		int m = -1;
		String result="노래삭제 실패";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			
			String sql = "delete from music.playList where musicNum=? and userid=? and playNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(musicNum));
			pstmt.setString(2, userid);
			pstmt.setInt(3, Integer.parseInt(playNum));
			m = pstmt.executeUpdate();
			
			if(m>0) {
				System.out.println("재생목록에서 삭제완료");
				System.out.println("m 값 : "+ m);
				result="노래삭제 성공";
			}
			else { 
				System.out.println("재생목록에서 삭제실패");
				System.out.println("m 값 : "+ m);
				result="노래삭제 실패";
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("플레이딜리트서블릿오류");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("플레이딜리트서블릿오류");
		}	finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch (Exception e) {
				e.getStackTrace();
				System.out.println("플레이딜리트서블릿오류");
			}
		}
		
		return result;
	}

}
