//#if ${Receipt} == "T"
package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.Receipt;
import {{systemName|lower}}.ev.exception.ReceiptNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;

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