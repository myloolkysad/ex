package conn.member;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet{
	private static final long serialVersionUID = -4274700572038677000L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		
		String userPW = request.getParameter("updateMember_userpw");
		String userPhone = request.getParameter("updateMember_userPhone");
		String userMail = request.getParameter("updateMember_userMail");
		String userZip = request.getParameter("updateMember_userZipcode");
		String userAddress1 = request.getParameter("updateMember_userAddress1");
		String userAddress2 = request.getParameter("updateMember_userAddress2");
		
		String userAddress = "("+userZip+")"+"."+userAddress1+"."+userAddress2;
		

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			 conn = DBConnection.getConnection();
			
			String sql = "update music.member set userpw=?,userPhone=?,userMail=?,userAddress=? where userid=?";  
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userPW);
			pstmt.setString(2, userPhone);
			pstmt.setString(3, userMail);
			pstmt.setString(4, userAddress);
			pstmt.setString(5, member.getMemberid());
			int n = pstmt.executeUpdate();
			
			if(n>0) {
				System.out.println("멤버업데이터서블릿: 회원정보 수정 완료");
				
				member.setMemberpw(userPW);
				member.setMemberPhone(userPhone);
				member.setMemberMail(userMail);
				member.setMemberAddress(userAddress);
//					RequestDispatcher rd = request.getRequestDispatcher("suc.jsp");
				session.setAttribute("member", member);
				
				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script language='javascript'>");
				out.println("alert('회원정보가 수정되었습니다.');");   //알림창을 띄운 뒤 페이지 뒤로 이동
				out.println("window.close();");
				out.print("</script>");
				out.flush();
				
				
			}
			else { response.sendRedirect("fail.jsp");  } 
		} catch (ClassNotFoundException e) {
			System.out.println("멤버데이터서블릿: 회원정보수정실패");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("멤버데이터서블릿: 회원정보수정실패");
		}	finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch (Exception e) {
				e.getStackTrace();
				System.out.println("멤버데이터서블릿: 데이터닫기 실패");
			}
		}

	}
}
