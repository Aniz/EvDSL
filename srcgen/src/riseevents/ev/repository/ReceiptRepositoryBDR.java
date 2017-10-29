//#if ${Receipt} == "T"
package riseevents.ev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import riseevents.ev.data.Receipt;
import riseevents.ev.exception.ReceiptNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.util.PersistenceMechanismException;
import riseevents.ev.util.PersistenceMechanismRDBMS;

public class ReceiptRepositoryBDR implements ReceiptRepository {
	
	private static ReceiptRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public ReceiptRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static ReceiptRepositoryBDR getInstance(){
		if(instance == null){
			instance = new ReceiptRepositoryBDR();
		}
		return instance;
	}
	
	
	@Override
	public void insert(Receipt receipt) throws RepositoryException {
		try {
		Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO Receipt (idReceipt) Values('"
				+receipt.getIdReceipt()
				+ "', '"+receipt.getIdRegistration()
				+ "', '"+receipt.getBarcode()
				+ "', '"+receipt.getDate()
				+ "', '"+receipt.getValue() 
				+ "', '"+receipt.getStatus() 
				
	        
		        +"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
	}
	


	@Override
	public List<Receipt> getreceiptList() throws RepositoryException {
		Receipt receipt = null;
		ArrayList<Receipt> list = new ArrayList<Receipt>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from Receipt");
            while (resultset.next()) {
	            receipt = new Receipt();
		        receipt.setIdReceipt(resultset.getInt("idReceipt"));         
				list.add(receipt);
            } 
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return list;
	}

	@Override
	public boolean isThere(int idReceipt) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM Receipt WHERE idReceipt = '" + idEntity + "'");
            answer = resultset.next();
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
        return answer;
	}

	@Override
	public int getReceiptLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EeventDB' AND TABLE_NAME= 'Receipt'");
            resultset.first();
            answer = resultset.getInt("proximo_valor");
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
        return answer;
	}
	
	@Override
	public void remove(int idReceipt) throws ReceiptNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM Receipt WHERE idReceipt = '"+ idReceipt+"'");
            if (i == 0) {
            	throw new ReceiptNotFoundException(idReceipt);
            }
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
		} catch(SQLException e){
            throw new RepositoryException(e);            
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
	}
	
	@Override
	public void update(Receipt review) throws ReceiptNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

            		statement.executeUpdate("UPDATE Receipt SET 
    
    	    		                                 ' WHERE idReceipt = '"+ review.getIdReceipt()+"'");

		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
		} catch (SQLException e) {
		    throw new RepositoryException(e);
	    } finally {
	    	try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
	}
	
	@Override
	public Receipt search(int idReceipt) throws ReceiptNotFoundException,
			RepositoryException {
		Receipt receipt = null;
		receipt = new Receipt();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from Receipt WHERE idReceipt =" + idReceipt);
            if (resultset.next()) {   
                receipt.setIdReceipt(resultset.getInt("idReceipt"));         
		    } else {
            	throw new ReceiptNotFoundException(idReceipt);
            }
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return receipt;
	}


}
//#endif