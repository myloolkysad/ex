package service;

import java.sql.Connection;

import dao.ArticleDao;
import exception.ArticleNotFoundException;
import exception.InvalidPasswordException;

import util.ConnectionProvider;
import util.JdbcUtil;

import bean.Article;

public class ArticleUpdateService {
	private static ArticleUpdateService instance = new ArticleUpdateService();

	public static ArticleUpdateService getInstance() {
		return instance;
	}

	private ArticleUpdateService() {
	}


	
	///////////////////////////////////////////////////////////////////////////////
	//����ڰ� �Է��� ���� ������ password�� ������ ���Ͽ� ��ġ�ϴ� ��쿡�� �����ϱ����
	public int update(Article article) throws Exception{
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			ArticleDao dao =  ArticleDao.getInstance();
			Article savedArticle = dao.selectById(con, article.getId(), false);
			if(savedArticle==null){
				throw new ArticleNotFoundException();
			}
			if(savedArticle.getPassword().equals(article.getPassword())== false)
				throw new InvalidPasswordException();
			
			int result = dao.update(con, article);
			if(result != 1){
				throw new ArticleNotFoundException();
			}
			return article.getId();
		} catch (Exception e) {
			throw e;
		} finally {
			JdbcUtil.close(con);
			
		}
	}
	
}
