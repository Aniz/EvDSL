//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.Payment;
import riseevents.ev.exception.PaymentNotFoundException;
import riseevents.ev.exception.RepositoryException;


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