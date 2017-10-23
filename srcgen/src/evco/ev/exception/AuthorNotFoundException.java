//#if ${InsertAuthors} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class AuthorNotFoundException extends Exception {
    private int idAuthor;

    public AuthorNotFoundException(int idAuthor){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idAuthor = idAuthor;
    }

    public int getidAuthor(){
        return idAuthor;
    }

}
//#endif