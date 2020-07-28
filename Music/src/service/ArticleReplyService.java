package service;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Date;

import util.ConnectionProvider;
import util.JdbcUtil;
import bean.Article;
import dao.ArticleDao;
import exception.ArticleNotFoundException;
import exception.RepleCountOverflowException;

public class ArticleReplyService {
	private static ArticleReplyService instance = new ArticleReplyService();

	public static ArticleReplyService getInstance() {
		return instance;
	}

	private ArticleReplyService() {
	}


	public int reply(int parentId, Article reply) throws Exception {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			ArticleDao dao = ArticleDao.getInstance();
			
			return dao.reply(con,parentId, reply); // 현재 쓴 글의 글번호를 반환
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(con);
		}
	}
}
