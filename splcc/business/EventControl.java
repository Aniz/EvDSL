package {{systemName|lower}}.ev.business;

import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;

import {{systemName|lower}}.ev.data.Activity;
import {{systemName|lower}}.ev.data.Event;
import {{systemName|lower}}.ev.exception.EventAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.EventNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.EventRepository;


public class EventControl {
	
    private EventRepository eventList;
    private Event event;
	
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
{% if 'program' in data.statments %}
	// Generated by DSL	
	public void generateProgram(List<Activity> activities, Event event) throws DocumentException, IOException{
		event.generateProgram(activities);
	}
	//
{% endif %}
{% if 'importantDates' in data.statments %}
	// Generated by DSL
	public void generateImportantDates (String abstractDate, String fullPaperDate, String notificationDate, Event event) throws DocumentException, IOException{
		event.generateImportantDates(abstractDate, fullPaperDate, notificationDate);
	}
	//
{% endif %}
{% if 'reportsFrequencyPerEvent' in data.statments %}
	// Generated by DSL
	public void frequencyPerEvent(List<String> ParticipantsPerEvent, Event event) throws DocumentException, IOException{
		event.frequencyPerEvent(ParticipantsPerEvent);
	}
	public List<String> getParticipantsPerEvent (int idEvent) throws RepositoryException{
		return eventList.getParticipantsPerEvent(idEvent);
	}
	//
{% endif %}
}
