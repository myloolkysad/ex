package conn.rank;

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

//@WebServlet("/LoginServlet")
public class RankUpdateServlet extends HttpServlet{
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

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			 conn = DBConnection.getConnection();
			
			//아이디 존재 유무 확인
			String sql = "select * from music.member where userid=?";  
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); //rs는 1이 나와야 함.
			System.out.println(rs);
			
			if(rs.next()) {
				if(rs.getString("userpw").equals(userPW)) {  // 아이디와 비밀번호 일치 시
//					MemberDto member = new MemberDto();
//					member.setMemberid(rs.getString("userid"));
//					member.setMemberpw(rs.getString("userpw"));
//					member.setMemberName(rs.getString("userName"));
//					member.setMemberBirth(rs.getString("userBirth"));
//					member.setMemberPhone(rs.getString("userPhone"));
//					member.setMemberMail(rs.getString("userMail"));
//					member.setMemberAddress(rs.getString("userAddress"));
//					member.setMemberSignUpDay(rs.getString("userSignUpDay"));
//					member.setMemberFinalLogin(rs.getString("userFinalLogin"));
//					member.setMemberFreeTicket(rs.getInt("userFreeTicket"));
							
					HttpSession session = request.getSession();
//					session.setAttribute("member", member);

					RequestDispatcher rd = request.getRequestDispatcher("suc.jsp");
					response.sendRedirect("suc.jsp"); // 처리 후 보낼 곳 페이지 명
				}
				
				else { response.sendRedirect("fail.jsp");  }  //비밀번호 불일치 보낼 페이지 명 
			} else {response.sendRedirect("fail.jsp");  }
		} catch (ClassNotFoundException e) {
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
			}catch (Exception e) {
				e.getStackTrace();
				System.out.println("데이터닫기 오류");
			}
		}

	}
}
