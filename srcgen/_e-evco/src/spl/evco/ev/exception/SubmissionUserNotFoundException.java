//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class SubmissionUserNotFoundException extends Exception {

    private int idSubmissionUser;

    public SubmissionUserNotFoundException(int idSubmissionUser){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idSubmissionUser = idSubmissionUser;
    }

    public int getidSubmissionUser(){
        return idSubmissionUser;
    }

}
//#endif