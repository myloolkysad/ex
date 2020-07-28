package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ArticleDao;
import exception.ArticleNotFoundException;

import util.ConnectionProvider;
import util.JdbcUtil;

import bean.Article;

public class ArticleReadService {
	private static ArticleReadService instance = new ArticleReadService();

	public static ArticleReadService getInstance() {
		return instance;
	}

	private ArticleReadService() {
	}

	// ///////////////////////////////////////////////////////////////////////////////////
	// jsp로부터 하나의 글번호를 전달받아서 dao를 통해 해당글을 읽어오고 전달하는 메소드
	public Article readArticle(int articleId, boolean read) throws SQLException, ArticleNotFoundException {
		Connection con =null;
		try {
			con =ConnectionProvider.getConnection();
			ArticleDao dao = ArticleDao.getInstance();
			Article article = dao.selectById(con, articleId, read);
			if(article==null){
				throw new ArticleNotFoundException(); 
			}
			return article;
		} catch (SQLException e) {
			throw new SQLException(e+"readarticle에러");
		} finally {
			JdbcUtil.close(con);
		}
		
	}

}
