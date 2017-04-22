//#if ${InsertAuthors} == "T"
package rise.splcc.exception;

public class SubmissionAuthorNotFoundException extends Exception {

    private int idSubmissionAuthor;

    public SubmissionAuthorNotFoundException(int idSubmissionAuthor){
        super(EXC_NOT_FOUND);
        this.idSubmissionAuthor = idSubmissionAuthor;
    }

    public int getidSubmissionAuthor(){
        return idSubmissionAuthor;
    }

}
//#endif