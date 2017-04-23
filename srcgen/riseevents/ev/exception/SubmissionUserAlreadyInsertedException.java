//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package riseevents.ev.exception;

public class SubmissionUserAlreadyInsertedException extends Exception {

	private int idSubmissionUser;

	public SubmissionUserAlreadyInsertedException(int idSubmissionUser) {
		super(EXC_ALREADY_EXISTS);
		this.idSubmissionUser = idSubmissionUser;
	}

	public int getidSubmissionUser() {
		return idSubmissionUser;
	}
}
//#endif