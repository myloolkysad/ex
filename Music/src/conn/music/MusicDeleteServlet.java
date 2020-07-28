package conn.music;

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

public class MusicDeleteServlet {
		
	
	public void deleteSong(String musicName, String Artist) {
		int n = -1;
		int m = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			
			String sql = "select musicNum from music.musicList where musicName=? and musicArtist=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, musicName);
			pstmt.setString(2, Artist);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				
				String musicNum = rs.getString("musicNum"); 
			
			sql = "delete from music.playList where musicNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(musicNum));
			m = pstmt.executeUpdate();
			
			if(m>0) {
				System.out.println("재생목록에서 삭제완료");
			}
			else { 
				System.out.println("재생목록에서 삭제실패");  
			}
			
//			sql = "SET foreign_key_checks = 0;";  
//			pstmt = conn.prepareStatement(sql);
//			pstmt.executeQuery();
			
			sql = "delete from music.musicList where musicName=? and musicArtist=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, musicName);
			pstmt.setString(2, Artist);
			n = pstmt.executeUpdate();
			
			if(n>0) {
				System.out.println("음원삭제완료");
			}
			else { 
				System.out.println("음원삭제실패");  
			}
			
//			sql = "SET foreign_key_checks = 0;";  
//			pstmt = conn.prepareStatement(sql);
//			pstmt.executeQuery();

			}
		} catch (ClassNotFoundException e) {
			System.out.println("뮤직딜리트서블릿오류");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("뮤직딜리트서블릿오류");
		}	finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch (Exception e) {
				e.getStackTrace();
				System.out.println("뮤직딜리트서블릿오류");
			}
		}
	}

}
