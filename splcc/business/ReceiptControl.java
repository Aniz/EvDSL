//#if ${Receipt} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.Receipt;
import {{systemName|lower}}.ev.exception.ReceiptAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.ReceiptNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.ReceiptRepository;


public class ReceiptControl {
	
    private ReceiptRepository receipts;
	
	public ReceiptControl(ReceiptRepository repository){
		this.receipts = repository;
	}

	public void insert(Receipt receipt) throws ReceiptAlreadyInsertedException, RepositoryException{
		if (receipt != null) {
			if (!receipts.isThere(receipt.getIdReceipt())) 
				receipts.insert(receipt);
			else
				throw new ReceiptAlreadyInsertedException(receipt.getIdReceipt());
		} else {
            throw new IllegalArgumentException();
        }
	}

	public List<Receipt> getReceipts() throws RepositoryException{
		return receipts.getReceipts();  
	}
	
	
	public void remove(int idReceipt) throws ReceiptAlreadyInsertedException, RepositoryException, ReceiptNotFoundException{
		receipts.remove(idReceipt);
	}
	
	public void update(Receipt review) throws ReceiptAlreadyInsertedException, RepositoryException, ReceiptNotFoundException{
		receipts.update(review);
	}
	
	public int getReceiptLastId() throws RepositoryException{
		return receipts.getReceiptLastId();
	}
	
	public Receipt search(int idReceipt) throws ReceiptAlreadyInsertedException, RepositoryException, ReceiptNotFoundException{
		return receipts.search(idReceipt);
	}
}
//#endif