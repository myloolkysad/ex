package exception;

public class ArticleNotFoundException extends Exception {
	
	public ArticleNotFoundException(){
		super("해당 게시글을 찾을 수가 없습니다.");
	}
}
