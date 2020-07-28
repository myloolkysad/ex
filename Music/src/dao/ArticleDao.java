package dao;

/**
 *  싱글톤
 *  01. 총 개수 조회 - int selectCount
 *  
 *  02. 글들을 firstRow, endRow조회해서 list형식으로 글 반환  - List<Article> select 
 * 
 * 03. rs 을 article형식으로 변환 - Article makeArticleFromResultSet
 * 
 *  04. 전달받은 article을 db에 삽입 - int insert  
 * 
 *  05. 글 번호를 입력받아서 article 객체로 변환 - Article selectById  
 *
 * 06. article 을 받아서 기존의 글을 수정하는 메소드 - int update 
 * 
 *  07. 글번호를 입력받아서 디비에서 지우는 메소드 - void delete
 *
 * 08. db에서 최소값을 가지는 순서번호를 조회하는 메소드 - String selectLastSequenceNumber
 *  
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import bean.Article;
import assets.*;
import util.*;

public class ArticleDao {
	private static ArticleDao instance = new ArticleDao();

	public static ArticleDao getInstance() {
		return instance;
	}

	private ArticleDao() {
	}

	// ///////////////////////////////////////////////////////////////////
	// 01. selectCount : 총 개수
	public int selectCount(Connection con) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select count(*) from board");
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			throw new SQLException(e + "dao - selectCount에러");
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}// end SelectCount

	// db에 저장된 글 중에서 원하는 글을 읽어와서 list형식으로 만들어 반환하는 메소드
	// 02. List<Article> select - 글들을 firstRow, endRow조회해서 list형식으로 글 반환
	public List<Article> select(Connection con, int firstRow, int endRow)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM board ORDER BY grp DESC , seq ASC limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, firstRow - 1);
			pstmt.setInt(2, endRow - firstRow + 1);
			rs = pstmt.executeQuery();

			if (rs.next() == false) {
				return Collections.emptyList();
			}
			List<Article> articleList = new ArrayList<>();
			do {
				articleList.add(makeArticleFromResultSet(rs, false));
			} while (rs.next());

			return articleList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e + "dao-select 에러");

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// resultset으로부터 article형식의 객체를 만들어 주는 내부 메소드
	// 03. Article makeArticleFromResultSet - rs 을 article형식으로 변환.
	private Article makeArticleFromResultSet(ResultSet rs, boolean readContent)
			throws SQLException {
		Article article = new Article();
		try {
			if (readContent) {
				article.setContent(rs.getString("content"));
			}
			// article_id title grp seq lvl posting_date read_count writer_name
			// password content
			article.setId(rs.getInt("article_id"));
			article.setGrp(rs.getInt("grp"));
			article.setSeq(rs.getInt("seq"));

			article.setPostingDate(rs.getString("posting_date"));
			article.setReadCount(rs.getInt("read_count"));
			article.setWriterName(rs.getString("writer_name"));
			article.setPassword(rs.getString("password"));
			article.setTitle(rs.getString("title"));
			article.setLv(rs.getInt("lvl"));
			article.setDeleted(rs.getInt("deleted")==1?true:false);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e + "dao-makeArticleFromResultSet 에러");
		}
		return article;
	}

	// 전달받은 article객체를 db에 삽입하는 메소드
	// 04. int insert - 전달받은 article을 db에 삽입
	public int insert(Connection con, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		int grp;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM board ORDER BY grp DESC limit 1");
			if (rs.next()) {
				grp = rs.getInt("grp") + 1;
			} else
				grp = 1;

			String sql = "insert into board values(NULL, ?, ?, '1', '0', CURRENT_TIMESTAMP, 0, ?, ?, ?, 0);";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setInt(2, grp);
			pstmt.setString(3, article.getWriterName());
			pstmt.setString(4, article.getPassword());
			pstmt.setString(5, article.getContent());
			int result = pstmt.executeUpdate();

			if (result > 0) {
				stmt = con.createStatement();
				rs = stmt.executeQuery("select last_insert_id(article_id) "
						+ "from board order by article_id desc");
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
			return -1;
		} catch (SQLException e) {
			throw new SQLException(e + "dao-insert 에러");
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 내가 만든 답변 메소드 
		public int reply(Connection con, int parentId, Article article) throws SQLException{
			/*
			 * 첫번째 글에 대한 댓글
			 * 그룹레벨 받아와야하고, 시퀀스 받아와야한다. 
			 * 레벨 받아와야 한다. 
			 * update board set seq = seq + 1 where grp = 1 and seq > 1;
			 * insert into board( title, grp, seq, lvl ) values('반가워요', 1, 1+1,
			 * 0+1);
			 */
			PreparedStatement pstmt = null;
			Statement stmt = null;
			ResultSet rs = null;
			String sql=null;
			int grp=0,lvl=0,seq=0;
			
			try {
				stmt=con.createStatement();
				sql="select grp, seq, lvl from board where article_id="+parentId;
				rs = stmt.executeQuery(sql);
				if(rs.next()){
					grp=rs.getInt("grp");
					seq=rs.getInt("seq");
					lvl=rs.getInt("lvl");
				}
				
				//트랙잭션 동기화
				con.setAutoCommit(false);
				sql="update board set seq = seq + 1 where grp = ? and seq > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, grp);
				pstmt.setInt(2, seq);
				pstmt.executeUpdate();
//	article_id	title	grp	seq	lvl	posting_date	read_count	writer_name	password	content
// insert into board( title, grp, seq, lvl ) values('반가워요', 1, 1+1,0+1);
				sql = "insert into board values(NULL, ?, ?, ?, ?, CURRENT_TIMESTAMP, 0, ?, ?, ?, 0);";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, article.getTitle());
				pstmt.setInt(2, grp);
				pstmt.setInt(3, seq+1);
				pstmt.setInt(4, lvl+1);
				pstmt.setString(5, article.getWriterName());
				pstmt.setString(6, article.getPassword());
				pstmt.setString(7, article.getContent());
				int result = pstmt.executeUpdate();
				con.commit();
				
				
				if (result > 0) {
					stmt = con.createStatement();
					rs = stmt.executeQuery("select last_insert_id(article_id) "
							+ "from board order by article_id desc");
					if (rs.next()) {
						return rs.getInt(1);
					}
				}
				return -1;
				
			} catch (SQLException e) {
				con.rollback();
				throw new SQLException(e + "dao-reply 에러");
			}finally{
				con.setAutoCommit(true);
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
				JdbcUtil.close(pstmt);
			}
		}
		
	// 특정 글번호를 전달받아서 db에서 해당글을 읽어들이고
	// resultSet을 Article로 가공하여 반환하는 메소드
	// 05. Article selectById 글 번호를 입력받아서 article 객체로 변환
	public Article selectById(Connection con, int articleId, boolean read)
			throws SQLException {
		PreparedStatement pstmt_select = null;
		PreparedStatement pstmt_update_read_count = null;

		ResultSet rs = null;
		Article article = null;
		try {
			pstmt_select = con.prepareStatement("select * from board where article_id=?");
			pstmt_select.setInt(1, articleId);
			rs = pstmt_select.executeQuery();

			if (rs.next() == true) {
				if(read){
					String sql = "update board set read_count = read_count+1 where article_id=?";
					pstmt_update_read_count = con.prepareStatement(sql);
					pstmt_update_read_count.setInt(1, articleId);
					pstmt_update_read_count.executeUpdate(); // 조회수 증가	
				}								
				article = makeArticleFromResultSet(rs, true); // select결과v resultset가공
			}
		} catch (SQLException e) {
			throw new SQLException(e + "dao-selectById에러");
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt_select);
			JdbcUtil.close(pstmt_update_read_count);

		}
		return article;
	}

	// ////////////////////////////////////////////////////////////////////////////////
	// 새로운 게시글(Article)을 받아서 기존의 Article_id가 일치하는 글을 수정하는 메소드
	// 06 int update - article 을 받아서 기존의 글을 수정하는 메소드.
	public int update(Connection con, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con
					.prepareStatement("update board set title=?, content=? where article_id=?");
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setInt(3, article.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e + "dao-update에러");
		} finally {
			JdbcUtil.close(pstmt);

		}
	}

	// 07 void delete- 글번호를 입력받아서 디비에서 지우는 메소드
	public void delete(Connection con, int articleId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("update board set  title='원본이 삭제되었습니다.', content='삭제된 내용입니다. 게시판 속도 향상을 위해 링크를 남겨두었습니다.' , deleted='1' where article_id=?");
			pstmt.setInt(1, articleId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e + "dao-delete에러");
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// db에서 순서번호가 입력받은 2개의 사이를 범위로 하여 그 중 최소값을 가지는 순서번호를
	// 조회하는 메소드 - 즉 지금 답변 달고자 하는 부모글의 자식글들(형제글)중 가장 작은 순서번호를
	// 조회
	// 08 String selectLastSequenceNumber - db에서 최소값을 가지는 순서번호를 조회하는 메소드
	public String selectLastSequenceNumber(Connection con, String searchMax,
			String searchMin) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select min(sequence_no) from article where "
					+ "sequence_no < ? and sequence_no >=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, searchMax);
			pstmt.setString(2, searchMin);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString(1);
			} else {
				return null;
			}

		} catch (SQLException e) {
			throw new SQLException(e + "dao selectLastSequence 에러");
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}	
}
