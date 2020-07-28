package dao;

/**
 *  �̱���
 *  01. �� ���� ��ȸ - int selectCount
 *  
 *  02. �۵��� firstRow, endRow��ȸ�ؼ� list�������� �� ��ȯ  - List<Article> select 
 * 
 * 03. rs �� article�������� ��ȯ - Article makeArticleFromResultSet
 * 
 *  04. ���޹��� article�� db�� ���� - int insert  
 * 
 *  05. �� ��ȣ�� �Է¹޾Ƽ� article ��ü�� ��ȯ - Article selectById  
 *
 * 06. article �� �޾Ƽ� ������ ���� �����ϴ� �޼ҵ� - int update 
 * 
 *  07. �۹�ȣ�� �Է¹޾Ƽ� ��񿡼� ����� �޼ҵ� - void delete
 *
 * 08. db���� �ּҰ��� ������ ������ȣ�� ��ȸ�ϴ� �޼ҵ� - String selectLastSequenceNumber
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
	// 01. selectCount : �� ����
	public int selectCount(Connection con) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select count(*) from board");
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			throw new SQLException(e + "dao - selectCount����");
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}// end SelectCount

	// db�� ����� �� �߿��� ���ϴ� ���� �о�ͼ� list�������� ����� ��ȯ�ϴ� �޼ҵ�
	// 02. List<Article> select - �۵��� firstRow, endRow��ȸ�ؼ� list�������� �� ��ȯ
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
			throw new SQLException(e + "dao-select ����");

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// resultset���κ��� article������ ��ü�� ����� �ִ� ���� �޼ҵ�
	// 03. Article makeArticleFromResultSet - rs �� article�������� ��ȯ.
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
			throw new SQLException(e + "dao-makeArticleFromResultSet ����");
		}
		return article;
	}

	// ���޹��� article��ü�� db�� �����ϴ� �޼ҵ�
	// 04. int insert - ���޹��� article�� db�� ����
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
			throw new SQLException(e + "dao-insert ����");
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	// ���� ���� �亯 �޼ҵ� 
		public int reply(Connection con, int parentId, Article article) throws SQLException{
			/*
			 * ù��° �ۿ� ���� ���
			 * �׷췹�� �޾ƿ;��ϰ�, ������ �޾ƿ;��Ѵ�. 
			 * ���� �޾ƿ;� �Ѵ�. 
			 * update board set seq = seq + 1 where grp = 1 and seq > 1;
			 * insert into board( title, grp, seq, lvl ) values('�ݰ�����', 1, 1+1,
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
				
				//Ʈ����� ����ȭ
				con.setAutoCommit(false);
				sql="update board set seq = seq + 1 where grp = ? and seq > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, grp);
				pstmt.setInt(2, seq);
				pstmt.executeUpdate();
//	article_id	title	grp	seq	lvl	posting_date	read_count	writer_name	password	content
// insert into board( title, grp, seq, lvl ) values('�ݰ�����', 1, 1+1,0+1);
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
				throw new SQLException(e + "dao-reply ����");
			}finally{
				con.setAutoCommit(true);
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
				JdbcUtil.close(pstmt);
			}
		}
		
	// Ư�� �۹�ȣ�� ���޹޾Ƽ� db���� �ش���� �о���̰�
	// resultSet�� Article�� �����Ͽ� ��ȯ�ϴ� �޼ҵ�
	// 05. Article selectById �� ��ȣ�� �Է¹޾Ƽ� article ��ü�� ��ȯ
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
					pstmt_update_read_count.executeUpdate(); // ��ȸ�� ����	
				}								
				article = makeArticleFromResultSet(rs, true); // select���v resultset����
			}
		} catch (SQLException e) {
			throw new SQLException(e + "dao-selectById����");
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt_select);
			JdbcUtil.close(pstmt_update_read_count);

		}
		return article;
	}

	// ////////////////////////////////////////////////////////////////////////////////
	// ���ο� �Խñ�(Article)�� �޾Ƽ� ������ Article_id�� ��ġ�ϴ� ���� �����ϴ� �޼ҵ�
	// 06 int update - article �� �޾Ƽ� ������ ���� �����ϴ� �޼ҵ�.
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
			throw new SQLException(e + "dao-update����");
		} finally {
			JdbcUtil.close(pstmt);

		}
	}

	// 07 void delete- �۹�ȣ�� �Է¹޾Ƽ� ��񿡼� ����� �޼ҵ�
	public void delete(Connection con, int articleId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement("update board set  title='������ �����Ǿ����ϴ�.', content='������ �����Դϴ�. �Խ��� �ӵ� ����� ���� ��ũ�� ���ܵξ����ϴ�.' , deleted='1' where article_id=?");
			pstmt.setInt(1, articleId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e + "dao-delete����");
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// db���� ������ȣ�� �Է¹��� 2���� ���̸� ������ �Ͽ� �� �� �ּҰ��� ������ ������ȣ��
	// ��ȸ�ϴ� �޼ҵ� - �� ���� �亯 �ް��� �ϴ� �θ���� �ڽı۵�(������)�� ���� ���� ������ȣ��
	// ��ȸ
	// 08 String selectLastSequenceNumber - db���� �ּҰ��� ������ ������ȣ�� ��ȸ�ϴ� �޼ҵ�
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
			throw new SQLException(e + "dao selectLastSequence ����");
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}	
}
