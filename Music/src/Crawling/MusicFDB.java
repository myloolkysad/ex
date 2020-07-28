//초기 음악리스트 추가용

package Crawling;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MusicFDB")
public class MusicFDB extends HttpServlet{
	private static final long serialVersionUID = -4274700572038677000L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Crawling craw = new Crawling();
		ArrayList<SongVO> lo = craw.crawlingStart();
		UploadToDB(lo);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Crawling craw = new Crawling();
		ArrayList<SongVO> lo = craw.crawlingStart();
		UploadToDB(lo);
	}
	
	
	
	//디비에 저장하는 메서드
	public void UploadToDB(ArrayList<SongVO> SL) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/music";
		String user ="root";
		String pw= "1234";
		Connection conn;
		String sql;
		
		try {
		
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user ,pw);
			if(conn!=null)
			{
				System.out.println("���� ����");
			}
			for(int i=0;i<SL.size();i++)
			{
				sql="insert into music.musicList(musicNum, musicNAME, musicArtist, musicAlbum, musicImgSrc) values(?,?,?,?,?)";
				PreparedStatement pst =conn.prepareStatement(sql);
				pst.setInt(1, i+1);
				pst.setString(2, SL.get(i).getName());
				pst.setString(3, SL.get(i).getArtist());
				pst.setString(4, SL.get(i).getAlbum());
				pst.setString(5, "c://upload");
				pst.executeUpdate();
			}
			System.out.println("Upload_complete");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//UploadToDB 종료
	
	
	//DB에서 차트정보를 받아오는 메서드
//	static void DownloadToDB(ArrayList<SongVO> SL)
//	{
//		String driver = "org.mariadb.jdbc.Driver";
//		String url="jdbc:mariadb://127.0.0.1:3306/MelonDB";
//		String user ="root";
//		String pw= "1234";
//		Connection con;
//		String sql;
//		
//		int rank=0;
//		String NT, ST, AT;
//		
//		
//		try {
//			Class.forName(driver);
//			con=DriverManager.getConnection(url, user ,pw);
//			if(con!=null)
//			{
//				System.out.println("���� ����");
//			}
//			sql="select * from MelonDB";
//			PreparedStatement pst = con.prepareStatement(sql);
//			ResultSet re=pst.executeQuery();
//			while(re.next()) {
//				rank = re.getInt("rank");
//				NT = re.getString("NAME");
//				ST = re.getString("artist");
//				AT = re.getString("album");
//				SL.add(new SongVO(rank, NT, ST, AT));
//			}
//			
//			System.out.println("Download complete");
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
