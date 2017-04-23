//#if ${InsertAuthors} == "T"
package riseevents.ev.exception;

public class AuthorAlreadyInsertedException extends Exception {

	private int idAuthor;

	public AuthorAlreadyInsertedException(int idAuthor) {
		super(EXC_ALREADY_EXISTS);
		this.idAuthor = idAuthor;
	}

	public int getidAuthor() {
		return idAuthor;
	}
}
//#endif