//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package {{systemName|lower}}.ev.business;

import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;

import {{systemName|lower}}.ev.data.Payment;
import {{systemName|lower}}.ev.exception.PaymentAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.PaymentNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.PaymentRepository;


public class PaymentControl {
	
    private PaymentRepository paymentList;
	
	public PaymentControl(PaymentRepository repository){
		this.paymentList = repository;
	}

	{% if 'Insert' in data.commands %}
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
	{% endif %}
	public List<Payment> getPayments() throws RepositoryException {
		return paymentList.getPaymentList();  
	}


	public int getPaymentLastId() throws RepositoryException{
		return paymentList.getPaymentLastId();
	}

	{% if 'Update' in data.commands %}	
	public void update(Payment payment) throws PaymentAlreadyInsertedException, RepositoryException, PaymentNotFoundException{
		paymentList.update(payment);
	}
	{% endif %}
	{% if 'Remove' in data.commands %}
	public void remove(int idPayment) throws PaymentAlreadyInsertedException, RepositoryException, PaymentNotFoundException{
		paymentList.remove(idPayment);
	}
	{% endif %}
	{% if 'Search' in data.commands %}
	public Payment search(int idPayment) throws PaymentAlreadyInsertedException, RepositoryException, PaymentNotFoundException{
		return paymentList.search(idPayment);
	}
	{% endif %}
	
	public void type(Payment payment, Payment out) throws DocumentException, IOException {
		payment.startarAcaoTypePayment(out);
	}
}
//#endif