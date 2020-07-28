package service;

import java.sql.*;
import util.*;
import dao.ArticleDao;
import bean.Article;


public class ArticleWriteService {
	private static ArticleWriteService instance = new ArticleWriteService();

	public static ArticleWriteService getInstance() {
		return instance;
	}

	private ArticleWriteService() {
	}

	
	///////////////////////////////////////////////////////////
	//�۾��� ���� �޼ҵ�, ����ڰ� �Է��� �� �ܿ� ������ ���� Article�� ä���ִ� ����.
	public int write(Article article) throws SQLException{
		Connection con = null;
		try {
			  
			con = ConnectionProvider.getConnection();
			ArticleDao dao = ArticleDao.getInstance();
			int articleId = dao.insert(con, article);
			if(articleId == -1){
				JdbcUtil.rollback(con);
				throw new RuntimeException("�۾��⿡��!");
			}
			//con.commit(); //Ʈ����� �Ϸ�
			return articleId;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e+"writeService-write����");
		} finally {
			JdbcUtil.close(con);
		}
	}
}
