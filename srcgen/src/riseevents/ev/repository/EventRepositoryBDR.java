package riseevents.ev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import riseevents.ev.data.Activity;
import riseevents.ev.data.Event;
import riseevents.ev.data.Event.TypeEvent;
import riseevents.ev.exception.EventNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.util.PersistenceMechanismException;
import riseevents.ev.util.PersistenceMechanismRDBMS;

public class EventRepositoryBDR implements EventRepository {
	
	private static EventRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public EventRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static EventRepositoryBDR getInstance(){
		if(instance == null){
			instance = new EventRepositoryBDR();
		}
		return instance;
	}


	@Override
	public void insert(Event event) throws RepositoryException{
		//removi idOrganizdor. de acordo com o modelo relacional nao temos o  atributo idOrganizador dentro de Eventos
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO Event (eventName, period, place, institution,sponsors,typeEvent) Values('"
					+event.getEventName()
					+"', '"+event.getPeriod()
					+"', '"+event.getPlace()
					+"', '"+event.getInstitution() 
					+"', '"+event.getSponsors() 
				     +"', '"+event.getTypeEvent()
	        
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
	public void remove(int idEvent) throws EventNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM Event WHERE idEvent = '"+ idEvent+"'");
            if (i == 0) {
            	throw new EventNotFoundException(idEvent);
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
	public Event search(int idEvent) throws EventNotFoundException,
			RepositoryException {
		Event event = null;
		event = new Event();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from Event WHERE idEvent =" + idEvent);
            if (resultset.next()) {   
            	event.setIdEvent(resultset.getInt("idEvent"));
            	event.setEventName(resultset.getString("EventName"));
            	event.setPeriod(resultset.getString("period"));
            	event.setPlace(resultset.getString("place"));
            	event.setInstitution(resultset.getString("institution"));
            	event.setSponsors(resultset.getString("sponsors"));
				event.setTypeEvent(TypeEvent.valueOf(resultset.getString("typeEvent")));
					        
            } else {
            	throw new EventNotFoundException(idEvent);
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

	@Override
	public List<Event> getEventList() throws RepositoryException {
		Event event = null;
		ArrayList<Event> list = new ArrayList<Event>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from Event");
            while (resultset.next()) {
                event = new Event();
                event.setIdEvent(resultset.getInt("idEvent"));
            	event.setEventName(resultset.getString("eventName"));
            	event.setPeriod(resultset.getString("period"));
            	event.setPlace(resultset.getString("place"));
            	event.setInstitution(resultset.getString("institution"));
            	event.setSponsors(resultset.getString("sponsors"));
				event.setTypeEvent(TypeEvent.valueOf(resultset.getString("typeEvent")));
					        
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
	public void update(Event event) throws EventNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

            		statement.executeUpdate("UPDATE Event SET eventName = '"+ event.getEventName() +"', period = '"+ event.getPeriod() +"', place = '"+ event.getPlace() +"',institution = '"+ event.getInstitution() +"', sponsors = '"+ event.getSponsors() +"' WHERE idEvent = '"+ event.getIdEvent()+"'");

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
	public boolean isThere(int idEvent) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM Event WHERE idEvent = '" + idEvent + "'");
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
	
	public int getEventIdByName(String eventName) throws RepositoryException{
		int answer = -1;
		try{
			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("Select idEvent from Event where eventName = '" + eventName + "'");
			resultset.first();
			answer = resultset.getInt("idEvent");
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

}