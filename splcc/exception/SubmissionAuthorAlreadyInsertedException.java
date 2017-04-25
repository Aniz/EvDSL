//#if ${InsertAuthors} == "T"
package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

public class SubmissionAuthorAlreadyInsertedException extends Exception {

	private int idSubmissionAuthor;

	public SubmissionAuthorAlreadyInsertedException(int idSubmissionAuthor) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
        this.idSubmissionAuthor = idSubmissionAuthor;
	}

	public int getidSubmissionAuthor() {
		return idSubmissionAuthor;
	}
}
//#endif