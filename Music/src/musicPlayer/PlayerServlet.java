package musicPlayer;


import java.io.File;
//음악플레이어 컨트롤러(메인)
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
import dto.PlayDto;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

@WebServlet("/PlayerServlet")
public class PlayerServlet extends HttpServlet  {
	
	private static final long serialVersionUID = -4274700572038677000L;
	
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static String runningMusicName = null;
	BasicPlayer mp3 = new BasicPlayer();
	
	int n = 0;
	ArrayList<PlayDto> playList;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		request = req;
		response = resp;
		request.setCharacterEncoding("UTF-8");
		main();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		request = req;
		response = resp;
		request.setCharacterEncoding("UTF-8");
		main();
	}
	
	public void main() throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto) session.getAttribute("member");
		System.out.println("플레이서블릿 멤버값: "+member);
		
		if(member == null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script language='javascript'>");
			out.println("alert('로그인 후 이용해 주세요');");
			out.println("history.go(-1);");   //서블릿 작동 후 원래 페이지로 이동
			out.print("</script>");
			out.flush();
		}
		
		
		
		if(member != null) {
		
		System.out.println(member);
		
		if(member.getMemberFreeTicket().equals("이용권 미이용")) {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html; charset=UTF-8");
				out.println("<script language='javascript'>");
				out.println("alert('이용권 구매 후 이용해주세요');");
				out.println("history.go(-1);");   //서블릿 작동 후 원래 페이지로 이동
				out.print("</script>");
				out.flush();
		}
		else {
		
		String cont = request.getParameter("waht");
		String music = request.getParameter("musicName");
		playList = (ArrayList<PlayDto>) session.getAttribute("playList");
		
		
		
		System.out.println("플레이어 서블릿 : mp 파라미터 값 "+cont);
		System.out.println("플레이어 서블릿 : musicName 파라미터 값 "+music);
		System.out.println("플레이어 서블릿 : playList size"+ playList.size());
		
		for(int i=0; i<playList.size();i++) {
			if(music.equals(playList.get(i).getPlayMusicName())) { 
				n = i; 
				runningMusicName = playList.get(i).getPlayMusicName(); 
				System.out.println("초기 n값 : "+n);
				System.out.println("초기 재생노래 : "+runningMusicName);
				break;
			}
		}
		
		session.setAttribute("musicName", runningMusicName);
		
		BasicPlayerListener lo = new BasicPlayerListener() {
		@Override
		public void stateUpdated(BasicPlayerEvent arg0) {
			if(arg0.getCode() == BasicPlayerEvent.EOM) {
				System.out.println("Event: EOM, 자동다음곡 재생");
				
//				try {
//				if(n!=0) {
//					n--;
//					runningMusicName = playList.get(n).getPlayMusicName();
//					mp3.open(new File("c://upload/"+playList.get(n).getPlayMusicName()+".mp3"));
//				}
//				else {
//					n = (playList.size()-1);
//					runningMusicName = playList.get(n).getPlayMusicName();
//					mp3.open(new File("c://upload/"+playList.get(n).getPlayMusicName()+".mp3"));
//				} 
//				}
//				catch (BasicPlayerException e) {
//					System.out.println("30000");
//					e.printStackTrace();
//				}
				try {
					
					n++;
					Thread.sleep(1000);
					if(n==playList.size()) {
						n = 0;
						runningMusicName = playList.get(n).getPlayMusicName();
						mp3.open(new File("c://upload/"+playList.get(n).getPlayMusicName()+".mp3"));
						System.out.println("재생곡: "+runningMusicName);
						System.out.println("자동재생 n값 : "+n);
					}
					else {
						runningMusicName = playList.get(n).getPlayMusicName();
						mp3.open(new File("c://upload/"+playList.get(n).getPlayMusicName()+".mp3"));
						System.out.println("재생곡: "+runningMusicName);
						System.out.println("자동재생 n값 : "+n);
					}
				
				mp3.play();
				} catch (BasicPlayerException e) {
					System.out.println("30000");
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

		@Override
		public void opened(Object arg0, Map arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void progress(int arg0, long arg1, byte[] arg2, Map arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setController(BasicController arg0) {
			
			
		}
	};
	
	mp3.addBasicPlayerListener(lo);
		

			if(cont.equals("play")) {
				try {
					mp3.stop();
					mp3.open(new File("c://upload/"+playList.get(n).getPlayMusicName()+".mp3"));
					System.out.println("재생곡: "+runningMusicName);
					mp3.play();	
				} catch (BasicPlayerException e) {
					System.out.println("3");
					e.printStackTrace();
				}

			}
			
			if(cont.equals("pause")) {
				try {
					mp3.pause();
				} catch (BasicPlayerException e) {
					System.out.println("4");
					e.printStackTrace();
				}
			}
			
			if(cont.equals("resume")) {
				try {
					mp3.resume();
				} catch (BasicPlayerException e) {
					System.out.println("5");
					e.printStackTrace();
				}
			}
			
			if(cont.equals("stop")) {
				System.out.println("정지진입");
				try {
					mp3.stop();
					System.out.println(mp3.getStatus());
				} catch (BasicPlayerException e) {
					System.out.println("6");
					e.printStackTrace();
				}
			}      
			if(cont.equals("volumeup")) {
				try {
				float n = mp3.getGainValue();
				
				if(n<=-16.9888) { mp3.setGain(0.1); }
				else if(n<=-16.9874175) { mp3.setGain(0.2); }
				else if(n<=-16.984175) { mp3.setGain(0.3); }
				else if(n<=-7.4458423) { mp3.setGain(0.4); }
				else if(n<=-4.9475974) { mp3.setGain(0.5); }
				else if(n<=-3.0096858) { mp3.setGain(0.6); }
				else if(n<=-1.4262656) { mp3.setGain(0.7); }
				else if(n<=-0.08747645) { mp3.setGain(0.8); }
				else if(n<=0.0) { mp3.setGain(0.8); }
				else if(n<=1.072253) { mp3.setGain(0.9); }
				else if(n<=2.0952187) { mp3.setGain(1); }
				else if(n<=3.0103) { mp3.setGain(1.1); }
				else if(n<=3.8380973) { mp3.setGain(1.2); }
				else if(n<=4.593822) { mp3.setGain(1.3); }
				else if(n<=5.289025) { mp3.setGain(1.4); }
				else if(n<=5.932685) { mp3.setGain(1.41); }
				else if(n<=5.9945035) { mp3.setGain(1.414); }
					
				System.out.println(mp3.getGainValue());
				
				}catch(Exception e) { System.out.println("볼륨업 오류"); }
			}
			
			if(cont.equals("volumedown")) {
				try {
				float n = mp3.getGainValue();
				
				if(n>=6.019108) { mp3.setGain(1.41); }
				else if(n>=5.9945035) { mp3.setGain(1.4); }
				else if(n>=5.932685) { mp3.setGain(1.3); }
				else if(n>=5.289025) { mp3.setGain(1.2); }
				else if(n>=4.593822) { mp3.setGain(1.1); }
				else if(n>=3.8380973) { mp3.setGain(1); }
				else if(n>=3.0103) { mp3.setGain(0.9); }
				else if(n>=2.0952187) { mp3.setGain(0.8); }
				else if(n>=1.072253) { mp3.setGain(0.7); }
				else if(n>=0.0) {mp3.setGain(0.7); }
				else if(n>=-0.08747645) { mp3.setGain(0.6); }
				else if(n>=-1.4262656) { mp3.setGain(0.5); }
				else if(n>=-3.0096858) { mp3.setGain(0.4); }
				else if(n>=-4.9475794) { mp3.setGain(0.3); }
				else if(n>=-7.4458423) { mp3.setGain(0.2); }
				else if(n>=-16.984175) { mp3.setGain(0.1); }
				else { mp3.setGain(-1.0); }
				
				System.out.println(mp3.getGainValue());
					
				}catch(Exception e) { System.out.println("볼륨다운 오류"); }
					
				
			}

			if(cont.equals("previous")) {
				try {
					n--;
					
					if(n<0) {
						n = playList.size();
						n--;
						runningMusicName = playList.get(n).getPlayMusicName();
						mp3.open(new File("c://upload/"+playList.get(n).getPlayMusicName()+".mp3"));
						System.out.println("재생곡: "+runningMusicName);
						System.out.println("자동재생 n값 : "+n);
					}
					else {
						runningMusicName = playList.get(n).getPlayMusicName();
						mp3.open(new File("c://upload/"+playList.get(n).getPlayMusicName()+".mp3"));
						System.out.println("재생곡: "+runningMusicName);
						System.out.println("자동재생 n값 : "+n);
					}
				
				mp3.play();
				} catch (BasicPlayerException e) {
					System.out.println("30000");
					e.printStackTrace();
				}
			}

			
			if(cont.equals("next")) {
				try {
					n++;
					
					if(n==playList.size()) {
						n = 0;
						runningMusicName = playList.get(n).getPlayMusicName();
						mp3.open(new File("c://upload/"+playList.get(n).getPlayMusicName()+".mp3"));
						System.out.println("재생곡: "+runningMusicName);
						System.out.println("자동재생 n값 : "+n);
					}
					else {
						runningMusicName = playList.get(n).getPlayMusicName();
						mp3.open(new File("c://upload/"+playList.get(n).getPlayMusicName()+".mp3"));
						System.out.println("재생곡: "+runningMusicName);
						System.out.println("자동재생 n값 : "+n);
					}
				
				mp3.play();
				} catch (BasicPlayerException e) {
					System.out.println("30000");
					e.printStackTrace();
				}
			}

//			RequestDispatcher rd = request.getRequestDispatcher("suc.jsp");
			response.sendRedirect("LoginMember.jsp?id="+member);
//			PrintWriter out = response.getWriter();
//			response.setContentType("text/html; charset=UTF-8");
//			out.println("<script language='javascript'>");
//			out.println("history.go(0);");
//			out.println("opener.location.reload();");
//			out.println("location.reload();");
//			out.println("history.go(-1);");   //서블릿 작동 후 원래 페이지로 이동
//			out.print("</script>");
//			out.flush();		
		
		}
	}
  }	
}