//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package riseevents.ev.exception;
import riseevents.ev.util.ExceptionMessages;

public class SubmissionAlreadyInsertedException extends Exception {

	private int idSubmission;

	public SubmissionAlreadyInsertedException(int idSubmission) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idSubmission = idSubmission;
	}

	public int getidSubmission() {
		return idSubmission;
	}
}
//#endif