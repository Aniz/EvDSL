//#if ${InsertAuthors} == "T"
package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

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