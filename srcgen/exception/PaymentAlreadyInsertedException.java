//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package rise.splcc.exception;

public class PaymentAlreadyInsertedException extends Exception {

	private int idPayment;

	public PaymentAlreadyInsertedException(int idPayment) {
		super(EXC_ALREADY_EXISTS);
		this.idPayment = idPayment;
	}

	public int getidPayment() {
		return idPayment;
	}
}
//#endif