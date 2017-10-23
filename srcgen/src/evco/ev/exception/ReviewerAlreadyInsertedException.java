//#if ${Reviewer} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

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