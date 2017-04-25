//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

public class SubmissionUserAlreadyInsertedException extends Exception {

	private int idSubmissionUser;

	public SubmissionUserAlreadyInsertedException(int idSubmissionUser) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idSubmissionUser = idSubmissionUser;
	}

	public int getidSubmissionUser() {
		return idSubmissionUser;
	}
}
//#endif