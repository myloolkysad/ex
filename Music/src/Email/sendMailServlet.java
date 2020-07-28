package Email;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class sendMailServlet {
	private static final long serialVersionUID = 1L;
	
	String randome;

		public String sendMail(String mail) {
			String code = null;
			
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
	            msg.setSubject("비밀번호 인증번호", "UTF-8");    
	             
	            // 이메일 내용 
	            

//	            String code = request.getParameter("code_check"); //인증번호 값 받기
	            randome = Integer.toString(MyAuthentication.getRandom());	            
	            
	            
//	            request.setAttribute("code", randome);      //코드를 세션에 태우기
	            code = randome;
	            msg.setText(randome, "UTF-8"); //메시지 객체에 UTF-8타입으로 randome을 작성
	             
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
			return code;
	    
//	        RequestDispatcher rd = request.getRequestDispatcher("checkcode.jsp");
//	        rd.forward(request, response);
	        
	        
	        
	        
		}
	
	
	
	}

	class MyAuthentication extends Authenticator {
	      
	    PasswordAuthentication pa;
	    
	 
	    public MyAuthentication(){
	         
	        String id = "adoino131@gmail.com";       // 구글 ID
	        String pw = "aahi159^";          // 구글 비밀번호
	 
	        // ID와 비밀번호를 입력한다.
	        pa = new PasswordAuthentication(id, pw);
	      
	    }
	 
	    // 시스템에서 사용하는 인증정보
	    public PasswordAuthentication getPasswordAuthentication() {
	        return pa;
	    }
	    
	    public static int getRandom(){
	    	int random = 0;
	    	random = (int)Math.floor((Math.random()*(99999-10000+1)))+10000;
	    	return random;
	    }

}