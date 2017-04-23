//#if ${Reviewer} == "T"
package riseevents.ev.exception;

public class ReviewerNotFoundException extends Exception {

    private int idReviewer;

    public ReviewerNotFoundException(int idReviewer){
        super(EXC_NOT_FOUND);
        this.idReviewer = idReviewer;
    }

    public int getidReviewer(){
        return idReviewer;
    }

}
//#endif