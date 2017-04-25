//#if ${InsertAuthors} == "T"
package riseevents.ev.exception;

public class AuthorNotFoundException extends Exception {
import riseevents.ev.util.ExceptionMessages;

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