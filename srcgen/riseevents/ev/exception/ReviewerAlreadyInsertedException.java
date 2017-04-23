//#if ${Reviewer} == "T"
package riseevents.ev.exception;

public class ReviewerAlreadyInsertedException extends Exception {

	private int idUser;

	public ReviewerAlreadyInsertedException(int idUser) {
		super(EXC_ALREADY_EXISTS);
		this.idUser = idUser;
	}

	public int getidUser() {
		return idUser;
	}
}
//#endif