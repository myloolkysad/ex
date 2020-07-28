package service;

import java.sql.Connection;
import java.util.List;

import util.ConnectionProvider;
import util.DBMgrLoader;
import util.JdbcUtil;


import dao.ArticleDao;
import assets.DBConnectionMgr;
import bean.Article;
import bean.ArticlePage;

public class ArticlePageService {
	
	
	private static ArticlePageService instance = new ArticlePageService();
	public static ArticlePageService getInstance() {
		return instance;
	}
	private ArticlePageService() {
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	public static final int COUNT_PER_PAGE=10;
	
	public ArticlePage getArticlePage(int requestPage) throws Exception{
		if(requestPage<0){
			throw new Exception("적합하지 않은 페이지 정보");
		}
		ArticleDao dao = ArticleDao.getInstance();
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			int totalArticleCount = dao.selectCount(con); //게시글 총 갯수 획득
			if(totalArticleCount == 0){
				return new ArticlePage();
			}
			
			int totalPageCount = totalArticleCount / COUNT_PER_PAGE;
			if(totalArticleCount % COUNT_PER_PAGE != 0 )
				totalPageCount++;
			
			//화면 하단에 보일 시작, 끝 페이지 수 계산
			int beginPage = (requestPage-1) / 10* 10 +1;
			int endPage = beginPage+9;
			
			if(endPage > totalPageCount)
				endPage = totalPageCount;
			
			//dao에게 전달하여 db에서 읽어올 글의 인덱스
			int firstRow = (requestPage -1 ) * COUNT_PER_PAGE+1;
			int endRow = firstRow+ COUNT_PER_PAGE-1;
			
			if(endRow > totalArticleCount)
				endRow = totalArticleCount;
			
			System.out.println("totalArticleCount"+totalArticleCount);
			System.out.println("beginPage:"+beginPage);
			System.out.println("endPage:"+endPage);
			System.out.println("firstRow:"+firstRow);
			System.out.println("endRow:"+endRow);
			//dao를 이용하여 articlelist획득
			List<Article> articleList = dao.select(con, firstRow, endRow);
			ArticlePage articlePage = new ArticlePage(articleList, requestPage, totalPageCount, firstRow, endRow, beginPage, endPage);
			return articlePage;
			
		} catch (Exception e) {
			System.out.println("ArticlePage 에러");
			throw e;
		}finally{
			JdbcUtil.close(con);
		}
	}
	
	
	public ArticlePage getArticlePageMgr(int requestPage) throws Exception{
		if(requestPage<0){
			throw new Exception("적합하지 않은 페이지 정보");
		}
		ArticleDao dao = ArticleDao.getInstance();
		Connection con = null;
		
		try {
			
			con = DBMgrLoader.getConnection();
			int totalArticleCount = dao.selectCount(con); //게시글 총 갯수 획득
			if(totalArticleCount == 0){
				return new ArticlePage();
			}
			
			int totalPageCount = totalArticleCount / COUNT_PER_PAGE;
			if(totalArticleCount % COUNT_PER_PAGE != 0 )
				totalPageCount++;
			
			//화면 하단에 보일 시작, 끝 페이지 수 계산
			int beginPage = (requestPage-1) / 10* 10 +1;
			int endPage = beginPage+9;
			
			if(endPage > totalPageCount)
				endPage = totalPageCount;
			
			//dao에게 전달하여 db에서 읽어올 글의 인덱스
			int firstRow = (requestPage -1 ) * COUNT_PER_PAGE+1;
			int endRow = firstRow+ COUNT_PER_PAGE-1;
			
			if(endRow > totalArticleCount)
				endRow = totalArticleCount;
			
			System.out.println("totalArticleCount"+totalArticleCount);
			System.out.println("beginPage:"+beginPage);
			System.out.println("endPage:"+endPage);
			System.out.println("firstRow:"+firstRow);
			System.out.println("endRow:"+endRow);
			//dao를 이용하여 articlelist획득
			List<Article> articleList = dao.select(con, firstRow, endRow);
			ArticlePage articlePage = new ArticlePage(articleList, requestPage, totalPageCount, firstRow, endRow, beginPage, endPage);
			return articlePage;
			
		} catch (Exception e) {
			System.out.println("ArticlePage 에러");
			throw e;
		}finally{
			JdbcUtil.close(con);
		}
	}
	

}
