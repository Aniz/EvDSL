//#if ${Receipt} == "T" 
package riseevents.ev.exception;
import riseevents.ev.util.ExceptionMessages;

public class ReceiptNotFoundException extends Exception {

    private int idReceipt;

    public ReceiptNotFoundException(int idReceipt){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idReceipt = idReceipt;
    }

    public int getidReceipt(){
        return idReceipt;
    }

}
//#endif