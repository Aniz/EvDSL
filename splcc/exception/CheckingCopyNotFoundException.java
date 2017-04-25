//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

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