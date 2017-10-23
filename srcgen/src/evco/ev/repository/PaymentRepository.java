//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package evco.ev.repository;

import java.util.List;

import evco.ev.data.Payment;
import evco.ev.exception.PaymentNotFoundException;
import evco.ev.exception.RepositoryException;


public interface PaymentRepository {

	public void insert(Payment payment) throws RepositoryException;
	
	public List<Payment> getPaymentList() throws RepositoryException;
	
	public boolean isThere(int idPayment) throws RepositoryException;

	public int getPaymentLastId() throws RepositoryException;

	public void update(Payment payment) throws PaymentNotFoundException, RepositoryException;

	public void remove(int idPayment) throws PaymentNotFoundException, RepositoryException;

	public Payment search(int idPayment) throws PaymentNotFoundException, RepositoryException;
	
}
//#endif