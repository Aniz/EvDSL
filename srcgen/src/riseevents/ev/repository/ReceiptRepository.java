//#if ${Receipt} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.Receipt;
import riseevents.ev.exception.ReceiptNotFoundException;
import riseevents.ev.exception.RepositoryException;

public interface ReceiptRepository {

	public void insert(Receipt receipt) throws RepositoryException;
	
	public List<Receipt> getReceiptList() throws RepositoryException;
	
	public boolean isThere(int idReceipt) throws RepositoryException;
	
	public int getReceiptLastId() throws RepositoryException;
	
	public void remove(int idReceipt) throws ReceiptNotFoundException, RepositoryException;
	
	public void update(Receipt receipt) throws ReceiptNotFoundException, RepositoryException;
	
	public Receipt search(int idReceipt) throws ReceiptNotFoundException, RepositoryException;
}
//#endif