//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class AssignmentAlreadyInsertedException extends Exception {

	private int idReview;

	public AssignmentAlreadyInsertedException(int idReview) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idReview = idReview;
	}

	public int getidUser() {
		return idReview;
	}
}
//#endif