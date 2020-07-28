package conn.play;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseConn.DBConnection;
import dto.PlayDto;


public class PlaySearchServlet {

	
	public ArrayList<PlayDto> getplayList(String userid) {
		ArrayList<PlayDto> list = new ArrayList<PlayDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			
			String sql = 
			"select music.musiclist.musicName, music.playlist.playNum, music.musiclist.musicNum from music.musiclist , music.playlist where music.musiclist.musicNum = music.playlist.musicNum and userid =? order by playNum";  
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PlayDto playdto = new PlayDto();
				playdto.setPlayMusicName(rs.getString("musicName"));
				playdto.setPlayNum(rs.getInt("playNum"));
				playdto.setPlayMusicNum(rs.getInt("musicNum"));
				list.add(playdto);
			}
			
		}catch(Exception e) { System.out.println("플레이서치 오류"); }
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
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
}
