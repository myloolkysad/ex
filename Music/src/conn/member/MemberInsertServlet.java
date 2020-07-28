//회원가입 시 작동하는 서블릿

package conn.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseConn.DBConnection;

@WebServlet("/MemberInsertServlet")
public class MemberInsertServlet extends HttpServlet{
	private static final long serialVersionUID = -4274700572038677000L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String userID = request.getParameter("userid");
		String userPW = request.getParameter("userpw");
		String userName = request.getParameter("userName");
		String userBirthYear = request.getParameter("signUp_userBirth1");
		String userBirthMonth = request.getParameter("signUp_userBirth2");
		String userBirthDay = request.getParameter("signUp_userBirth3");
		String userPhone = request.getParameter("signUp_userPhone");
		String userMail = request.getParameter("signUp_userMail");
		String userAddress1 = request.getParameter("signUp_userAddress1");
		String userAddress2 = request.getParameter("signUp_userAddress2");
		String userZipCode = request.getParameter("signUp_zipCode");
		
		System.out.println("확인하기"+userName);
		
		String userBirth = userBirthYear+"-"+userBirthMonth+"-"+userBirthDay;
		String userAddress = "("+userZipCode+")"+"."+userAddress1+"."+userAddress2;
		
		new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String userSignUpday = df.format(cal.getTime());
		String userFinalLogin = df.format(cal.getTime());
		int userFreeTicket = 0;
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			 conn = DBConnection.getConnection();
			
			String sql = "insert into music.member values (?,?,?,?,?,?,?,?,?,?)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			pstmt.setString(3, userName);
			pstmt.setString(4, userBirth);
			pstmt.setString(5, userPhone);
			pstmt.setString(6, userMail);
			pstmt.setString(7, userAddress);
			pstmt.setString(8, userSignUpday);
			pstmt.setString(9, userFinalLogin);
			pstmt.setInt(10, userFreeTicket);
			
			int n = pstmt.executeUpdate();
						
			if(n == 1) {  // 입력 성공
				System.out.println("회원가입 성공");
				response.sendRedirect("suc.jsp"); 
			}
			else { response.sendRedirect("fail.jsp");  }  // 입력 실패 
			
		}catch (ClassNotFoundException e) {
			System.out.println("연결실패 : ClassNotFoundException오류");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("연결실패: SQLException 오류");
		}	finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
				System.out.println("데이터닫기 완료");
			}catch (Exception e) {
				e.getStackTrace();
				System.out.println("데이터닫기 오류");
			}
		}

	}
}
