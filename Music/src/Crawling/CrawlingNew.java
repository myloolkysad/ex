package Crawling;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlingNew {

	public ArrayList<NewVO> crawlingStart1(){
		ArrayList<NewVO> list1 = new ArrayList<NewVO>();
		
		String url = "https://www.melon.com/new/index.htm";
		Document melon =null;
		
		try {
			melon = Jsoup.connect(url).get();
		}catch(IOException e) {
			e.printStackTrace();
		}
		Elements titles = melon.select("td>div>div>div.ellipsis.rank01>span>a");
		Elements artist = melon.select("div>table>tbody>tr>td>div>div>div.ellipsis.rank02>a");
		Elements album = melon.select("td>div>div>div.ellipsis.rank03>a");
		Elements src = melon.select("div>table>tbody>tr>td>div>a");
		
		for(int i=0; i<titles.size();i++) {
			Element e1 = titles.get(i);
			Element e2 = artist.get(i);
			Element e3 = album.get(i);
			Element e4 = src.get(i);
			
			String song = e1.text();
			String artistName = e2.text();
			String albumName = e3.text();
			String srcName = e4.html();
			
			NewVO a = new NewVO(song,artistName, albumName, srcName);
			list1.add(a);
			
		}
		System.out.println("Crawling complete");
		return list1;
	}
}
