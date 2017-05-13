//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package riseevents.ev.exception;
import riseevents.ev.util.ExceptionMessages;

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