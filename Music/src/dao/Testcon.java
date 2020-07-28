package dao;

import service.ArticlePageService;
import assets.DBConnectionMgr;
import bean.ArticlePage;

public class Testcon {
	static long currentTime;
	static long afterTime;
	static long afterTime2;
	
	
	
	public String returnTime(){
		
		ArticlePage articlePage=null;
		ArticlePageService service = ArticlePageService.getInstance();
		
		try {
			
			currentTime=System.currentTimeMillis();
			articlePage = service.getArticlePageMgr(1);
			afterTime2=System.currentTimeMillis()-currentTime;
			
			currentTime=System.currentTimeMillis();
			articlePage = service.getArticlePage(1);
			afterTime=System.currentTimeMillis()-currentTime;
			
			
		} catch (Exception e) {}
		
		return afterTime+","+afterTime2;
	}
}
