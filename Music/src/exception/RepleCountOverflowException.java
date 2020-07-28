package exception;


//답변의 개수가 한계치(현재 99가 최대갯수)를 초과하는 발생시킬 예외클래스
public class RepleCountOverflowException extends Exception{
	public RepleCountOverflowException(){
		super("답변의 갯수가 최대치를 초과하였습니다. 답변 작성이 불가능합니다.");
	}
}
