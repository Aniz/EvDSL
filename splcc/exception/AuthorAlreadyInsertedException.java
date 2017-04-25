//#if ${InsertAuthors} == "T"
package {{systemName|lower}}.ev.exception;

public class AuthorAlreadyInsertedException extends Exception {
import {{systemName|lower}}.ev.util.ExceptionMessages;

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