//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class ReviewNotFoundException extends Exception {

    private int idReview;

    public ReviewNotFoundException(int idReview){
        super ("Revisao não encontrada!");
        this.idReview = idReview;
    }

    public int getidReview(){
        return idReview;
    }

}
//#endif