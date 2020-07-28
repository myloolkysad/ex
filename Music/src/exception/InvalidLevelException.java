package exception;

public class InvalidLevelException extends Exception {
	public InvalidLevelException(){
		super("레벨 초과입니다. 답변이 불가합니다");
	}
}
