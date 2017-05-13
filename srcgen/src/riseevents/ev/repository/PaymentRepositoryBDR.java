//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package riseevents.ev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import riseevents.ev.data.Payment;
import riseevents.ev.data.Payment.StatusPayment;
import riseevents.ev.data.Payment.TypePayment;
import riseevents.ev.exception.PaymentNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.util.PersistenceMechanismException;
import riseevents.ev.util.PersistenceMechanismRDBMS;

public class PaymentRepositoryBDR implements PaymentRepository {
	
	private static PaymentRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public PaymentRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static PaymentRepositoryBDR getInstance(){
		if(instance == null){
			instance = new PaymentRepositoryBDR();
		}
		return instance;
	}
	
	
	@Override
	public void insert(Payment payment) throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO payment (idPayment, idRegistration, barcode, date, value, status,typePayment) Values('"
				+payment.getIdPayment()
				+ "', '"+payment.getIdRegistration()
				+ "', '"+payment.getBarcode()
				+ "', '"+payment.getDate()
				+ "', '"+payment.getValue() 
				+ "', '"+payment.getStatus() 
				+"', '"+payment.getTypePayment()
				
	        
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
	public List<Payment> getPaymentList() throws RepositoryException {
		Payment payment = null;
		ArrayList<Payment> list = new ArrayList<Payment>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from payment");
            while (resultset.next()) {
                payment = new Payment();
                payment.setIdPayment(resultset.getInt("idPayment"));
                payment.setIdRegistration(resultset.getInt("idRegistration"));
                payment.setStatus(StatusPayment.valueOf(resultset.getString("status")));
                payment.setDate(resultset.getString("date"));
                payment.setValue(resultset.getFloat("value"));
                payment.setBarcode(resultset.getString("barcode"));
            	payment.setTypePayment(TypePayment.valueOf(resultset.getString("typePayment")));
          
				list.add(payment);
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
	public boolean isThere(int idPayment) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM payment WHERE idPayment = '" + idPayment + "'");
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
	public int getPaymentLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EeventDB' AND TABLE_NAME= 'payment'");
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
	public void update(Payment payment) throws PaymentNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

    	    statement.executeUpdate("UPDATE payment SET status = '"+ payment.getStatus() + 
    	    		                                 "', barcode = '"+ payment.getBarcode()+ 
    	    		                                 "', date = '"+ payment.getDate() + 
    	    		                                 "', value = '"+ payment.getValue() +
									                 "', typePayment = '"+ payment.getTypePayment() +						
    
    	    		                                 "' WHERE idPayment = '"+ payment.getIdPayment()+"'");

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
	public void remove(int idPayment) throws PaymentNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM payment WHERE idPayment = '"+ idPayment+"'");
            if (i == 0) {
            	throw new PaymentNotFoundException(idPayment);
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
	public Payment search(int idPayment) throws PaymentNotFoundException,
			RepositoryException {
		Payment payment = null;
		payment = new Payment();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from payment WHERE idPayment =" + idPayment);
            if (resultset.next()) {   
                 payment.setIdPayment(resultset.getInt("idPayment"));
                 payment.setIdRegistration(resultset.getInt("idRegistration"));
                 payment.setStatus(StatusPayment.valueOf(resultset.getString("status")));
                 payment.setDate(resultset.getString("date"));
                 payment.setValue(resultset.getFloat("value"));
                 payment.setBarcode(resultset.getString("barcode"));
            	payment.setTypePayment(TypePayment.valueOf(resultset.getString("typePayment")));
           
            } else {
            	throw new PaymentNotFoundException(idPayment);
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
		return payment;
	}

}
//#endif