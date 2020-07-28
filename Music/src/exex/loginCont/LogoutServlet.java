package exex.loginCont;
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
import dto.MemberDto;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet{
	private static final long serialVersionUID = -4274700572038677000L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
			
		
		HttpSession session = request.getSession();
		session.invalidate();

		//RequestDispatcher rd = request.getRequestDispatcher("suc.jsp");
		response.sendRedirect("Main.jsp"); // 처리 후 보낼 곳 페이지 명
		
		}

	
}
