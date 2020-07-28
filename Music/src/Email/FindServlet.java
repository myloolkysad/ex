package Email;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseConn.DBConnection;
import dto.MemberDto;

@WebServlet("/FindServlet")
public class FindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String memberID;
	private String memberPW;
	MemberDto member = new MemberDto();
	
	String randome;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String userphone = request.getParameter("userphone");
		String name = request.getParameter("username");
		
		System.out.println("파인드 서블릿 진입 : "+userphone+name);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			
			String sql = "select * from music.member where userPhone=? and userName=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userphone);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				member.setMemberid(rs.getString("userid"));
				member.setMemberMail(rs.getString("userMail"));
				member.setMemberName(rs.getString("userName"));
				
				FindMail(member.getMemberMail());
				System.out.println("파인드 서블릿 메일 발송");
				
				sql = "update member set userpw=? where userid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, randome);
				pstmt.setString(2, member.getMemberid());
				pstmt.executeUpdate();
				
				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script language='javascript'>");
				out.println("alert('전송이 완료되었습니다.<p>아이디에 등록된 메일을 확인해주세요.'); history.go(-1);");   //알림창을 띄운 뒤 페이지 뒤로 이동
				out.println("window.close();");
				out.print("</script>");
				out.flush();
				
			} else {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script language='javascript'>");
				out.println("alert('아이디 또는 이름을 확인해주세요.'); history.go(-1);");   //알림창을 띄운 뒤 페이지 뒤로 이동
				out.print("</script>");
				out.flush();
			}
			
	
		}catch (Exception e) { System.out.println("파인드서블릿 오류");	}
		finally {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch (Exception e) {
				e.getStackTrace();
				System.out.println("파인드 서블릿 데이터닫기 오류");
			}
		}
		
	}
	
	
		public void FindMail(String mail) {
						
			String fromName = "22";  // 보여질 발신자 이름
		 	String fromEmail = "test@gmail.com"; //보여질 발신 이메일
		
	        Properties props = System.getProperties();
	        props.put("mail.smtp.user", fromName); // 서버 아이디만 쓰기
			props.put("mail.smtp.host", "smtp.gmail.com"); // 구글 SMTP
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
	           
	        Authenticator auth = new MyAuthentication();
	         
	        //session 생성 및  MimeMessage생성
	        Session session = Session.getDefaultInstance(props, auth);
	        
	        MimeMessage msg = new MimeMessage(session); //메시지 객체 생성
	         
	        try{
	            //편지보낸시간
	            msg.setSentDate(new Date());
	             
	            InternetAddress from = new InternetAddress(fromEmail);    //이메일 정보 생성(발신자)
	            from.setPersonal("뮤직웬관리자","UTF-8");
	            
	            // 이메일 발신자
	            msg.setFrom(from);
	            

	             
	            // 이메일 수신자
//	            String email = request.getParameter("receiver"); //사용자가 입력한 이메일 받아오기
	            InternetAddress to = new InternetAddress(mail);  //이메일 정보 생성(수신자)
	            msg.setRecipient(Message.RecipientType.TO, to);   //발송인과 수신인을 구분
	             
	            // 이메일 제목
	            msg.setSubject("회원님의 계정정보 발신", "UTF-8");    
	             
	            // 이메일 내용 
	            

//	            String code = request.getParameter("code_check"); //인증번호 값 받기
	            randome = Integer.toString(MyAuthentication.getRandom());	            
	            
	            
//	            request.setAttribute("code", randome);      //코드를 세션에 태우기
	            msg.setText(member.getMemberName()+"님의 아이디와 새로운 비밀번호 입니다. // "+member.getMemberid()+"  "+randome, "UTF-8"); //메시지 객체에 UTF-8타입으로 randome을 작성
	             
	            // 이메일 헤더 
	            msg.setHeader("content-Type", "text/html");
	             
	            //메일보내기
	            Transport.send(msg);  // 이메일 발송
	            System.out.println("보냄!");
	             
	        }catch (AddressException addr_e) {
	            addr_e.printStackTrace();
	        }catch (MessagingException msg_e) {
	            msg_e.printStackTrace();
	        }
	        catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//	        RequestDispatcher rd = request.getRequestDispatcher("checkcode.jsp");
//	        rd.forward(request, response);
	        
	        
	        
	        
		}
	
	    public static int getRandom(){
	    	int random = 0;
	    	random = (int)Math.floor((Math.random()*(99999-10000+1)))+10000;
	    	return random;
	    }

}