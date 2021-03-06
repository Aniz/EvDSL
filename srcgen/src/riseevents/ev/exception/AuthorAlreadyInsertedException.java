//#if ${InsertAuthors} == "T"
package riseevents.ev.exception;
import riseevents.ev.util.ExceptionMessages;

public class AuthorAlreadyInsertedException extends Exception {

	private int idAuthor;

	public AuthorAlreadyInsertedException(int idAuthor) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idAuthor = idAuthor;
	}

	public int getidAuthor() {
		return idAuthor;
	}
}
//#endif