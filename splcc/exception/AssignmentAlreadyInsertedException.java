//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package {{systemName|lower}}.ev.exception;

public class AssignmentAlreadyInsertedException extends Exception {

	private int idReview;

	public AssignmentAlreadyInsertedException(int idReview) {
		super(EXC_ALREADY_EXISTS);
		this.idReview = idReview;
	}

	public int getidUser() {
		return idReview;
	}
}
//#endif