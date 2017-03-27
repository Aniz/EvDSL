//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package rise.splcc.exception;

public class CheckingCopyAlreadyInsertedException extends Exception {

	private int idCheckingCopy;

	public CheckingCopyAlreadyInsertedException(int idCheckingCopy) {
		super(EXC_ALREADY_EXISTS);
		this.idCheckingCopy = idCheckingCopy;
	}

	public int getidCheckingCopy() {
		return idCheckingCopy;
	}
	
}
//#endif