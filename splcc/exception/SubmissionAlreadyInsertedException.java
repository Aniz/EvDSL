//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package rise.splcc.exception;

public class SubmissionAlreadyInsertedException extends Exception {

	private int idSubmission;

	public SubmissionAlreadyInsertedException(int idSubmission) {
		super(EXC_ALREADY_EXISTS);
		this.idSubmission = idSubmission;
	}

	public int getidSubmission() {
		return idSubmission;
	}
}
//#endif