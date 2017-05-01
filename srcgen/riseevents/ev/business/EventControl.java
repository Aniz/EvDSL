package riseevents.ev.business;

import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;

import riseevents.ev.data.Activity;
import riseevents.ev.data.Event;
import riseevents.ev.exception.EventAlreadyInsertedException;
import riseevents.ev.exception.EventNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.EventRepository;


public class EventControl {
	
    private EventRepository eventList;
	
	public EventControl(EventRepository repository){
		this.eventList = repository;
	}
	
	public void insert(Event event) throws EventAlreadyInsertedException, RepositoryException{
		if (event != null) {
            if (!eventList.isThere(event.getIdEvent())) {
                eventList.insert(event);
            } else {
                throw new EventAlreadyInsertedException(event.getIdEvent());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(int idEvent) throws EventAlreadyInsertedException, RepositoryException, EventNotFoundException{
		eventList.remove(idEvent);
	}
	
	public void update(Event event) throws EventAlreadyInsertedException, RepositoryException, EventNotFoundException{
		eventList.update(event);
	}
	
	public Event search(int idEvent) throws EventAlreadyInsertedException, RepositoryException, EventNotFoundException{
		return eventList.search(idEvent);
	}

	public boolean isThere(int idEvent) throws RepositoryException {
		return eventList.isThere(idEvent);
	}

	public List<Event> getEventList() throws RepositoryException {
		return eventList.getEventList();  
	}
	
	public int getEventLastId() throws RepositoryException{
		return eventList.getEventLastId();
	}
	
	public int getEventIdByName(String eventName) throws RepositoryException{
		return eventList.getEventIdByName(eventName);
	}	
}