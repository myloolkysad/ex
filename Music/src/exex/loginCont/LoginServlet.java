package exex.loginCont;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conn.play.PlaySearchServlet;
import databaseConn.DBConnection;
import dto.MemberDto;
import dto.PlayDto;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = -4274700572038677000L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
						
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
					System.out.println(userID + "로그인 성공");
					
					MemberDto member = new MemberDto();
					member.setMemberid(rs.getString("userid"));
					member.setMemberpw(rs.getString("userpw"));
					member.setMemberName(rs.getString("userName"));
					member.setMemberBirth(rs.getString("userBirth"));
					member.setMemberPhone(rs.getString("userPhone"));
					member.setMemberMail(rs.getString("userMail"));
					member.setMemberAddress(rs.getString("userAddress"));
					member.setMemberSignUpDay(rs.getString("userSignUpDay"));
					member.setMemberFinalLogin(rs.getString("userFinalLogin"));
					member.setMemberFreeTicket(rs.getString("userFreeTicket"));
					if(rs.getString("userFreeTicket") == null) { member.setMemberFreeTicket("이용권 미이용"); }
					
					new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										
					String userFinalLogin = df.format(cal.getTime());
					//유저 마지막 접속시간 갱신
					sql = "update member set userFinalLogin=? where userid=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userFinalLogin);
					pstmt.setString(2, userID);
					pstmt.executeUpdate();
					
					//이용권 갱신
					
					if(!member.getMemberFreeTicket().equals("이용권 미이용")) {
						LocalDate now = LocalDate.now();
						LocalDate ticketDay = LocalDate.parse(member.getMemberFreeTicket());
					
						if(ticketDay.isBefore(now)) {
							member.setMemberFreeTicket("이용권 미이용");
						
							sql = "update member set userFreeTicket=? where userid=?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, "0000-00-00");
							pstmt.setString(2, userID);
							pstmt.executeUpdate();
						}
					}
					
					//재생목록 불러오기
					PlaySearchServlet playSearch = new PlaySearchServlet();
					ArrayList<PlayDto>playList = playSearch.getplayList(userID);
					
					HttpSession session = request.getSession();
					session.setAttribute("playList", playList);
					
					if(userID.equals("admin")) {
						System.out.println("관리자 로그인");
						
						session.setAttribute("admin", member);
						response.sendRedirect("ManagerMain.jsp?id="+member.getMemberid()); // 처리 후 보낼 곳 페이지 명
						
					}else {
					
					
					session.setAttribute("member", member);
					
//					RequestDispatcher rd = request.getRequestDispatcher("suc.jsp");
					response.sendRedirect("LoginMember.jsp?id="+member.getMemberid()); // 처리 후 보낼 곳 페이지 명
					}
				}
				
				else { 
					System.out.println("비밀번호틀림");
					response.setContentType("text/html; charset=UTF-8");

					PrintWriter out = response.getWriter();
					response.setContentType("text/html; charset=UTF-8");
					out.println("<script language='javascript'>");
					out.println("alert('계정을 확인해주세요'); history.go(-1);");   //알림창을 띄운 뒤 페이지 뒤로 이동
					out.print("</script>");
					out.flush();
				}  //비밀번호 불일치 보낼 페이지 명 
			} else {
				System.out.println("계정없음");
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script language='javascript'>");      
				out.println("alert('계정을 확인해주세요'); history.go(-1);");  //알림창을 띄운 뒤 페이지 뒤로 이동
				out.print("</script>");
				out.flush();
			} //계정없을 때
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
