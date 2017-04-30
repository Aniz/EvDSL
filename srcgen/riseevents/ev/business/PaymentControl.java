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

	public void insert(Payment payment) throws PaymentAlreadyInsertedException, RepositoryException{
		if (payment != null) {
			if (!paymentList.isThere(payment.getIdPayment())) 
				paymentList.insert(payment);
			else
				throw new PaymentAlreadyInsertedException(payment.getIdPayment());
		} else {
            throw new IllegalArgumentException();
        }
	}
	public List<Payment> getPayments() throws RepositoryException {
		return paymentList.getPaymentList();  
	}


	public int getPaymentLastId() throws RepositoryException{
		return paymentList.getPaymentLastId();
	}

	
	public void update(Payment payment) throws PaymentAlreadyInsertedException, RepositoryException, PaymentNotFoundException{
		paymentList.update(payment);
	}
	public void remove(int idPayment) throws PaymentAlreadyInsertedException, RepositoryException, PaymentNotFoundException{
		paymentList.remove(idPayment);
	}
	public Payment search(int idPayment) throws PaymentAlreadyInsertedException, RepositoryException, PaymentNotFoundException{
		return paymentList.search(idPayment);
	}
	
	public void type(Payment payment, Payment out) throws DocumentException, IOException {
		payment.startarAcaoTypePayment(out);
	}
}
//#endif