package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.sql.PooledConnection;

import assets.DBConnectionMgr;

public class DBMgrLoader extends HttpServlet{
	static DBConnectionMgr  pool;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		pool = DBConnectionMgr.getInstance();			
	}
	
	public static Connection getConnection(){
		Connection con=null;
		try {
			con = pool.getConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
