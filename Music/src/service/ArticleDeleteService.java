package service;

import java.sql.Connection;

import util.ConnectionProvider;
import util.JdbcUtil;

import bean.Article;

import dao.ArticleDao;
import exception.ArticleNotFoundException;
import exception.InvalidPasswordException;

public class ArticleDeleteService {
private static ArticleDeleteService instance = new ArticleDeleteService();

public static ArticleDeleteService getInstance() {
	return instance;
}

private ArticleDeleteService() {
}

////////
//
public void deleteArticle(int articleId, String password) throws Exception{
	Connection con=null;
	try {
		con = ConnectionProvider.getConnection();
		ArticleDao dao = ArticleDao.getInstance();
		Article originalArticle = dao.selectById(con, articleId, false);
		
		if(originalArticle ==null){
			throw new ArticleNotFoundException();
		}
		if(originalArticle.getPassword().equals(password)==false)
			throw new InvalidPasswordException();
		dao.delete(con, articleId);
	} catch (Exception e) {
		throw e;
	} finally {
		JdbcUtil.close(con);
	}
}
}
