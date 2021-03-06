//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

public class PaymentAlreadyInsertedException extends Exception {

	private int idPayment;

	public PaymentAlreadyInsertedException(int idPayment) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idPayment = idPayment;
	}

	public int getidPayment() {
		return idPayment;
	}
}
//#endif