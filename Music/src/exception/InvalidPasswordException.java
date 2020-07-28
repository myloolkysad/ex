package exception;

public class InvalidPasswordException extends Exception {
	public InvalidPasswordException(){
		super("암호가 잘못되었습니다.");
	}
}
