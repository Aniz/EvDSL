//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class CheckingCopyAlreadyInsertedException extends Exception {
	private int idCheckingCopy;

	public CheckingCopyAlreadyInsertedException(int idCheckingCopy) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idCheckingCopy = idCheckingCopy;
	}

	public int getidCheckingCopy() {
		return idCheckingCopy;
	}
	
}
//#endif