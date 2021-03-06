//#if ${Reviewer} == "T"
package riseevents.ev.exception;
import riseevents.ev.util.ExceptionMessages;

public class ReviewerAlreadyInsertedException extends Exception {

	private int idUser;

	public ReviewerAlreadyInsertedException(int idUser) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idUser = idUser;
	}

	public int getidUser() {
		return idUser;
	}
}
//#endif