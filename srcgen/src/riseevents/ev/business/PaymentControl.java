//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package riseevents.ev.business;

import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;

import riseevents.ev.data.Payment;
import riseevents.ev.exception.PaymentAlreadyInsertedException;
import riseevents.ev.exception.PaymentNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.PaymentRepository;


public class PaymentControl {
	
    private PaymentRepository paymentList;
	
	public PaymentControl(PaymentRepository repository){
		this.paymentList = repository;
	}
	public List<Payment> getPaymentList() throws RepositoryException {
		return paymentList.getPaymentList();  
	}

	public int getPaymentLastId() throws RepositoryException{
		return paymentList.getPaymentLastId();
	}
	
	public void type(Payment payment, Payment out) throws DocumentException, IOException {
		payment.startarAcaoTypePayment(out);
	}
}
//#endif