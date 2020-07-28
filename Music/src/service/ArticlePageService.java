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
			throw new Exception("�������� ���� ������ ����");
		}
		ArticleDao dao = ArticleDao.getInstance();
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			int totalArticleCount = dao.selectCount(con); //�Խñ� �� ���� ȹ��
			if(totalArticleCount == 0){
				return new ArticlePage();
			}
			
			int totalPageCount = totalArticleCount / COUNT_PER_PAGE;
			if(totalArticleCount % COUNT_PER_PAGE != 0 )
				totalPageCount++;
			
			//ȭ�� �ϴܿ� ���� ����, �� ������ �� ���
			int beginPage = (requestPage-1) / 10* 10 +1;
			int endPage = beginPage+9;
			
			if(endPage > totalPageCount)
				endPage = totalPageCount;
			
			//dao���� �����Ͽ� db���� �о�� ���� �ε���
			int firstRow = (requestPage -1 ) * COUNT_PER_PAGE+1;
			int endRow = firstRow+ COUNT_PER_PAGE-1;
			
			if(endRow > totalArticleCount)
				endRow = totalArticleCount;
			
			System.out.println("totalArticleCount"+totalArticleCount);
			System.out.println("beginPage:"+beginPage);
			System.out.println("endPage:"+endPage);
			System.out.println("firstRow:"+firstRow);
			System.out.println("endRow:"+endRow);
			//dao�� �̿��Ͽ� articlelistȹ��
			List<Article> articleList = dao.select(con, firstRow, endRow);
			ArticlePage articlePage = new ArticlePage(articleList, requestPage, totalPageCount, firstRow, endRow, beginPage, endPage);
			return articlePage;
			
		} catch (Exception e) {
			System.out.println("ArticlePage ����");
			throw e;
		}finally{
			JdbcUtil.close(con);
		}
	}
	
	
	public ArticlePage getArticlePageMgr(int requestPage) throws Exception{
		if(requestPage<0){
			throw new Exception("�������� ���� ������ ����");
		}
		ArticleDao dao = ArticleDao.getInstance();
		Connection con = null;
		
		try {
			
			con = DBMgrLoader.getConnection();
			int totalArticleCount = dao.selectCount(con); //�Խñ� �� ���� ȹ��
			if(totalArticleCount == 0){
				return new ArticlePage();
			}
			
			int totalPageCount = totalArticleCount / COUNT_PER_PAGE;
			if(totalArticleCount % COUNT_PER_PAGE != 0 )
				totalPageCount++;
			
			//ȭ�� �ϴܿ� ���� ����, �� ������ �� ���
			int beginPage = (requestPage-1) / 10* 10 +1;
			int endPage = beginPage+9;
			
			if(endPage > totalPageCount)
				endPage = totalPageCount;
			
			//dao���� �����Ͽ� db���� �о�� ���� �ε���
			int firstRow = (requestPage -1 ) * COUNT_PER_PAGE+1;
			int endRow = firstRow+ COUNT_PER_PAGE-1;
			
			if(endRow > totalArticleCount)
				endRow = totalArticleCount;
			
			System.out.println("totalArticleCount"+totalArticleCount);
			System.out.println("beginPage:"+beginPage);
			System.out.println("endPage:"+endPage);
			System.out.println("firstRow:"+firstRow);
			System.out.println("endRow:"+endRow);
			//dao�� �̿��Ͽ� articlelistȹ��
			List<Article> articleList = dao.select(con, firstRow, endRow);
			ArticlePage articlePage = new ArticlePage(articleList, requestPage, totalPageCount, firstRow, endRow, beginPage, endPage);
			return articlePage;
			
		} catch (Exception e) {
			System.out.println("ArticlePage ����");
			throw e;
		}finally{
			JdbcUtil.close(con);
		}
	}
	

}
