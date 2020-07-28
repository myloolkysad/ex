package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	
	public static Connection getConnection(){
		Connection con=null;
		//Ŀ�ؼ�Ǯ�� ����ϴ� ���  
		//�Ʒ��� ���� Ŀ�ؼ�Ǯ���� Ŀ�ؼ��� �޾ƿ��� ��� pool.jocl���� ���� Ȯ���Ұ�! 
		
		try {
			con = DriverManager.getConnection("jdbc:apache:commons:dbcp:/pool");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
