//#if ${InsertAuthors} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class SubmissionAuthorNotFoundException extends Exception {

    private int idSubmissionAuthor;

    public SubmissionAuthorNotFoundException(int idSubmissionAuthor){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idSubmissionAuthor = idSubmissionAuthor;
    }

    public int getidSubmissionAuthor(){
        return idSubmissionAuthor;
    }

}
//#endif