//#if ${InsertAuthors} == "T"
package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

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