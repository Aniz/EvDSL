//#if ${Receipt} == "T"
package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.Receipt;
import riseevents.ev.exception.ReceiptAlreadyInsertedException;
import riseevents.ev.exception.ReceiptNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.ReceiptRepository;


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