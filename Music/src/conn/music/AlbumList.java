package conn.music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import databaseConn.DBConnection;
import dto.MusicDto;

public class AlbumList {
	
	public ArrayList<MusicDto> getAlbums() {
		ArrayList<MusicDto> list = new ArrayList<MusicDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		conn = DBConnection.getConnection();
		
		String sql = "select * from music.musicList";  
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			MusicDto music = new MusicDto();
			music.setMusicNum(rs.getInt("musicNum"));
			music.setMusicName(rs.getString("musicName"));
			music.setMusicArtist(rs.getString("musicArtist"));
			music.setMusicAlbum(rs.getString("musicAlbum"));
			music.setMusicImgSre(rs.getString("musicImgSrc"));
			list.add(music);
		}
		
		}
		catch(Exception e) {
			System.out.println("앨범리스트자바 오류");
		}finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch (Exception e) {
				e.getStackTrace();
				System.out.println("앨범리스트자바 데이터 닫기 오류");
			}
		}
		
		return list;
	}
	
	public ArrayList<MusicDto> searchAlbums(String what){
		ArrayList<MusicDto> list = new ArrayList<MusicDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		conn = DBConnection.getConnection();
		
		String sql = "select * from music.musicList where musicName like concat('%',?,'%') or musicArtist like concat('%',?,'%') or musicAlbum like concat('%',?,'%')";  
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, what);
		pstmt.setString(2, what);
		pstmt.setString(3, what);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			MusicDto music = new MusicDto();
			music.setMusicNum(rs.getInt("musicNum"));
			music.setMusicName(rs.getString("musicName"));
			music.setMusicArtist(rs.getString("musicArtist"));
			music.setMusicAlbum(rs.getString("musicAlbum"));
			music.setMusicImgSre(rs.getString("musicImgSrc"));
			list.add(music);
		}
		
		}
		catch(Exception e) {
			System.out.println("앨범찾기 오류");
			e.getStackTrace();
			e.getMessage();
		}finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch (Exception e) {
				e.getStackTrace();
				System.out.println("앨범찾기 데이터 닫기 오류");
			}
		}
		
		
		return list;
	}
		
}
