//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.Payment;
import {{systemName|lower}}.ev.exception.PaymentNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;


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