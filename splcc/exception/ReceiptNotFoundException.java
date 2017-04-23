//#if ${Receipt} == "T" 
package {{systemName|lower}}.ev.exception;

public class ReceiptNotFoundException extends Exception {

    private int idReceipt;

    public ReceiptNotFoundException(int idReceipt){
        super(EXC_NOT_FOUND);
        this.idReceipt = idReceipt;
    }

    public int getidReceipt(){
        return idReceipt;
    }

}
//#endif