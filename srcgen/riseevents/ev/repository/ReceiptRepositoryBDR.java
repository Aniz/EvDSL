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
	public void insert(Event event) throws RepositoryException {
		try {
		Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO Event (idEvent,typeEvent,link,aaaa,bbbb) Values('"
				+event.getIdEvent()
				+ "', '"+event.getIdRegistration()
				+ "', '"+event.getBarcode()
				+ "', '"+event.getDate()
				+ "', '"+event.getValue() 
				+ "', '"+event.getStatus() 
				+"', '"+event.getTypeEvent()
				
				+"', '"+event.getLink()   
				+"', '"+event.getAaaa()   
				+"', '"+event.getBbbb()   
	        
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
	public List<Event> geteventList() throws RepositoryException {
		Event event = null;
		ArrayList<Event> list = new ArrayList<Event>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from Event");
            while (resultset.next()) {
	            event = new Event();
		        event.setIdEvent(resultset.getInt("idEvent"));         
            	event.setTypeEvent(TypeEvent.valueOf(resultset.getString("typeEvent")));
				event.setLink(resultset.getString("link"));
				event.setAaaa(resultset.getInt("aaaa"));
				event.setBbbb(resultset.getString("bbbb"));
				list.add(event);
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
	public boolean isThere(int idEvent) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM Event WHERE idEvent = '" + idEntity + "'");
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
	public int getEventLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EeventDB' AND TABLE_NAME= 'Event'");
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
		    int i = statement.executeUpdate("DELETE FROM Event WHERE idEvent = '"+ idEvent+"'");
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

            		statement.executeUpdate("UPDATE Event SET 
											         link = '"+ event.getLink() +
												     "', aaaa = '"+ event.getAaaa() +
												     "', bbbb = '"+ event.getBbbb() +
									                 typeEvent = "'+ event.getTypeEvent() +						
    
    	    		                                 ' WHERE idEvent = '"+ review.getIdEvent()+"'");

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
	public Receipt search(int idEvent) throws EventNotFoundException,
			RepositoryException {
		Event event = null;
		event = new Event();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from Event WHERE idEvent =" + idEvent);
            if (resultset.next()) {   
                event.setIdEvent(resultset.getInt("idEvent"));         
            	event.setTypeEvent(TypeEvent.valueOf(resultset.getString("typeEvent")));
				event.setLink(resultset.getString("link"));
				event.setAaaa(resultset.getInt("aaaa"));
				event.setBbbb(resultset.getString("bbbb"));
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
		return event;
	}


}
//#endif