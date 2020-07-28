package bean;

import java.util.Collections;
import java.util.List;

public class ArticlePage {
	private List<Article> articleList;  // 현재 페이지에 보여질 게시글 리스트
	private int requestPage; 				// 사용자 요청 페이지
	private int totalPageCount;		   // 총 페이지 수
	private int startRow; 				   // 현재 페이지에 보여질 시작행 번호
	private int endRow; 				   // 현재 페이지에 보여질 끝행 번호
	private int beginPage; 			   // 페이지 하단의 링크에 쓰일 시작 페이지
	private int endPage; 				   // 페이지 하단의 링크에 쓰일 끝페이지

	// /////////////////////////////////////////////////////////////////////////////////////////
	public ArticlePage() {
		this(Collections.<Article> emptyList(), 0, 0, 0, 0, 0, 0);
	}

	public ArticlePage(List<Article> articleList, int requestPage,
			int totalPageCount, int startRow, int endRow, int beginPage,
			int endPage) {
		this.articleList = articleList;
		this.requestPage = requestPage;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
		this.beginPage = beginPage;
		this.endPage = endPage;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////

	public List<Article> getArticleList() {
		return articleList;
	}

	public int getRequestPage() {
		return requestPage;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public int getEndPage() {
		return endPage;
	}
	
	
	// /////////////////////////////////////////////////////////////////////////////////////////
	public boolean isHasArticle(){
		if(articleList.isEmpty()==true){
			return false;
		}else {
			return true;
		}
	}

}
