//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package riseevents.ev.exception;

public class ReviewAlreadyInsertedException extends Exception {
	
	private int idReview;

	public ReviewAlreadyInsertedException(int idReview) {
		super(ExceptionMessages.EXC_NOT_FOUND);
        this.idReview = idReview;
	}

	public int getidReview() {
		return idReview;
	}
	
}
//#endif