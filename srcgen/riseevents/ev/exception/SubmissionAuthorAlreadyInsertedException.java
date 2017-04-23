//#if ${InsertAuthors} == "T"
package riseevents.ev.exception;

public class SubmissionAuthorAlreadyInsertedException extends Exception {

	private int idSubmissionAuthor;

	public SubmissionAuthorAlreadyInsertedException(int idSubmissionAuthor) {
		super(EXC_ALREADY_EXISTS);
        this.idSubmissionAuthor = idSubmissionAuthor;
	}

	public int getidSubmissionAuthor() {
		return idSubmissionAuthor;
	}
}
//#endif