//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package {{systemName|lower}}.ev.exception;

public class ReviewAlreadyInsertedException extends Exception {
	
	private int idReview;

	public ReviewAlreadyInsertedException(int idReview) {
		super(EXC_NOT_FOUND);
        this.idReview = idReview;
	}

	public int getidReview() {
		return idReview;
	}
	
}
//#endif