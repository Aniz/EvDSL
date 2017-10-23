//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class CheckingCopyNotFoundException extends Exception {

    private int idCheckingCopy;

    public CheckingCopyNotFoundException(int idCheckingCopy){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idCheckingCopy = idCheckingCopy;
    }

    public int getidCheckingCopy(){
        return idCheckingCopy;
    }
}
//#endif