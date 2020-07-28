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
	//글쓰기 수행 메소드, 사용자가 입력한 값 외에 나머지 값을 Article에 채워넣는 역할.
	public int write(Article article) throws SQLException{
		Connection con = null;
		try {
			  
			con = ConnectionProvider.getConnection();
			ArticleDao dao = ArticleDao.getInstance();
			int articleId = dao.insert(con, article);
			if(articleId == -1){
				JdbcUtil.rollback(con);
				throw new RuntimeException("글쓰기에러!");
			}
			//con.commit(); //트랜잭션 완료
			return articleId;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e+"writeService-write에러");
		} finally {
			JdbcUtil.close(con);
		}
	}
}
