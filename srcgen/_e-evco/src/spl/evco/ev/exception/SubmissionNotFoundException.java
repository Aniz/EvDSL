//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class SubmissionNotFoundException extends Exception {

    private int idSubmission;

    public SubmissionNotFoundException(int idSubmission){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idSubmission = idSubmission;
    }

    public int getidSubmission(){
        return idSubmission;
    }

}
//#endif