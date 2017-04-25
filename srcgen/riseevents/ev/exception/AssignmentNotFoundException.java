//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package riseevents.ev.exception;

public class AssignmentNotFoundException extends Exception {

    private int idReview;

    public AssignmentNotFoundException(int idReview){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idReview = idReview;
    }

    public int getidReview(){
        return idReview;
    }

}
//#endif