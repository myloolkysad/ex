package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	
	public static Connection getConnection(){
		Connection con=null;
		//커넥션풀을 사용하는 경우  
		//아래와 같이 커넥션풀에서 커넥션을 받아오는 경우 pool.jocl파일 여부 확인할것! 
		
		try {
			con = DriverManager.getConnection("jdbc:apache:commons:dbcp:/pool");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
