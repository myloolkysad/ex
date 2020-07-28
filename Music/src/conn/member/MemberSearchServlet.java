package conn.member;
import java.io.IOException;
import java.io.PrintWriter;
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
import dto.MemberDto;


public class MemberSearchServlet {
	
	public boolean check(String id) {
		
		boolean check = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			 conn = DBConnection.getConnection();
			
			String sql = "select * from music.member where userid=?";  
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); 
						
			if(rs.next()) { check = true;	} 
			else { check = false; }  
			
		} catch (ClassNotFoundException e) {
			System.out.println("멤버찾기 실패 : ClassNotFoundException����");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("멤버찾기 실패22: SQLException ����");
		}	finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch (Exception e) {
				e.getStackTrace();
				System.out.println("데이터 닫기 오류");
			}
		}
		
		return check;
	}
	
	public ArrayList<MemberDto> getMemberList() {
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			 conn = DBConnection.getConnection();
			
			String sql = "select * from music.member";  
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
						
			while(rs.next()) { 
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
				
				list.add(member);
			} 
		} catch (ClassNotFoundException e) {
			System.out.println("멤버찾기 실패 : ClassNotFoundException����");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("멤버찾기 실패22: SQLException ����");
		}	finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch (Exception e) {
				e.getStackTrace();
				System.out.println("데이터 닫기 오류");
			}
		}
		
		return list;
	}
}
