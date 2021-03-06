//#if ${InsertAuthors} == "T"
package riseevents.ev.exception;
import riseevents.ev.util.ExceptionMessages;

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