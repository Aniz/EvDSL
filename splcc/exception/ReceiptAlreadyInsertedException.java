//#if ${Receipt} == "T" 
package rise.splcc.exception;

public class ReceiptAlreadyInsertedException extends Exception {

	private int idReceipt;

	public ReceiptAlreadyInsertedException(int idReceipt) {
		super(EXC_ALREADY_EXISTS);
		this.idReceipt = idReceipt;
	}

	public int getidReceipt() {
		return idReceipt;
	}
}
//#endif