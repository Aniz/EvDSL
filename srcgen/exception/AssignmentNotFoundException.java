//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package rise.splcc.exception;

public class AssignmentNotFoundException extends Exception {

    private int idReview;

    public AssignmentNotFoundException(int idReview){
        super(EXC_NOT_FOUND);
        this.idReview = idReview;
    }

    public int getidReview(){
        return idReview;
    }

}
//#endif