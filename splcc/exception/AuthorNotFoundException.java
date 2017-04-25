//#if ${InsertAuthors} == "T"
package {{systemName|lower}}.ev.exception;

public class AuthorNotFoundException extends Exception {
import {{systemName|lower}}.ev.util.ExceptionMessages;

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