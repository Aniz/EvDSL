//#if ${Receipt} == "T" 
package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

public class ReceiptAlreadyInsertedException extends Exception {

	private int idReceipt;

	public ReceiptAlreadyInsertedException(int idReceipt) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idReceipt = idReceipt;
	}

	public int getidReceipt() {
		return idReceipt;
	}
}
//#endif