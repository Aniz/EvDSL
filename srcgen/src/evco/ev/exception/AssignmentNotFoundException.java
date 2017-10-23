//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

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