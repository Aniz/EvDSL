//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package riseevents.ev.exception;

public class SubmissionUserNotFoundException extends Exception {

    private int idSubmissionUser;

    public SubmissionUserNotFoundException(int idSubmissionUser){
        super(EXC_NOT_FOUND);
        this.idSubmissionUser = idSubmissionUser;
    }

    public int getidSubmissionUser(){
        return idSubmissionUser;
    }

}
//#endif