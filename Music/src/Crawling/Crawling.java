package Crawling;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
	
	
	public ArrayList<SongVO> crawlingStart() {
		
		ArrayList<SongVO> list = new ArrayList<SongVO>();
		
		String url = "https://www.melon.com/chart/index.htm"; //크롤링할 html 주소
		Document melon = null; //document타입 변수
		
			try {
				melon = Jsoup.connect(url).get();  //크롤링 한 뒤 변수에 저장
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		// 1~50위
			Elements titles = melon.select("td>div>div>div.ellipsis.rank01>span>a");  //크롤링한 것 중에서 해당 경로의 값만 가져오기
			Elements artist = melon.select("div>table>tbody>tr>td>div>div>div.ellipsis.rank02>a");
			Elements album = melon.select("td>div>div>div.ellipsis.rank03 > a");
			Elements src = melon.select("div>table>tbody>tr>td>div>a");
		
		
		//곡명
//		for(Element e : titles) {
//			String song = e.text();
//			System.out.println("곡명: "+song);
//			System.out.println("===========");
//		}
		
		//가수명
//		for(Element e : artist) {
//			String artistName = e.text();
//			System.out.println("가수명: "+artistName);
//			System.out.println("===========");
//		}
		
		//앨범명
//		for(Element e : album) {
//			String albumName = e.text();
//			System.out.println("앨범명: "+albumName);
//			System.out.println("===========");
//		}

		for(int i=0; i<titles.size();i++) { //열거형을 리스트형으로 변환
			Element e1 = titles.get(i);
			Element e2 = artist.get(i);
			Element e3 = album.get(i);
			Element e4 = src.get(i);
			
			String song = e1.text();
			String artistName = e2.text();
			String albumName = e3.text();
			String srcName = e4.html();
			
			SongVO s = new SongVO(song, artistName, albumName, srcName);
			list.add(s);
		}
		System.out.println("Crawling complete");
		
		return list;
	}//crawling 끝
	
}
