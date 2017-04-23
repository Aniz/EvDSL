//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package riseevents.ev.exception;

public class SubmissionNotFoundException extends Exception {

    private int idSubmission;

    public SubmissionNotFoundException(int idSubmission){
        super(EXC_NOT_FOUND);
        this.idSubmission = idSubmission;
    }

    public int getidSubmission(){
        return idSubmission;
    }

}
//#endif