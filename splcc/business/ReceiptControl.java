//#if ${Receipt} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.Receipt;
import {{systemName|lower}}.ev.exception.ReceiptAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.ReceiptNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.ReceiptRepository;


public class ReceiptControl {
	
    private ReceiptRepository receiptList;
	
	public ReceiptControl(ReceiptRepository repository){
		this.receiptList = repository;
	}

	public void insert(Receipt receipt) throws ReceiptAlreadyInsertedException, RepositoryException{
		if (receipt != null) {
			if (!receiptList.isThere(receipt.getIdReceipt())) 
				receiptList.insert(receipt);
			else
				throw new ReceiptAlreadyInsertedException(receipt.getIdReceipt());
		} else {
            throw new IllegalArgumentException();
        }
	}

	public List<Receipt> getReceiptList() throws RepositoryException{
		return receiptList.getReceiptList();  
	}
	
	
	public void remove(int idReceipt) throws ReceiptAlreadyInsertedException, RepositoryException, ReceiptNotFoundException{
		receiptList.remove(idReceipt);
	}
	
	public void update(Receipt review) throws ReceiptAlreadyInsertedException, RepositoryException, ReceiptNotFoundException{
		receiptList.update(review);
	}
	
	public int getReceiptLastId() throws RepositoryException{
		return receiptList.getReceiptLastId();
	}
	
	public Receipt search(int idReceipt) throws ReceiptAlreadyInsertedException, RepositoryException, ReceiptNotFoundException{
		return receiptList.search(idReceipt);
	}
}
//#endif