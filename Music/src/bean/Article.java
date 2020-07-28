package bean;

import java.sql.Timestamp;
import java.util.Date;

public class Article {
	//	id	title	grp	seq	lv postingDate, readCount, writerName, password, content
	// aritlce_id, title, grp, seq, lv, posting_date, read_count, writer_name, password, content
	private int id;						 //글번호
	private String title; 				//제목
	private int grp; 					//그룹번호
	private int seq;					//순서
	private int lv; 					//레벨
	private String postingDate;    //게시일시
	private int readCount; 			//조회수
	private String writerName; 		//글쓴이
	private String password; 			//암호
	private String content; 			//내용
	private boolean deleted;
	
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getGrp() {
		return grp;
	}
	public void setGrp(int grp) {
		this.grp = grp;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public String getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
	
}
