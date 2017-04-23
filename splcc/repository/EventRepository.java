package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.Activity;
import {{systemName|lower}}.ev.data.Event;
import {{systemName|lower}}.ev.exception.EventNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;



public interface EventRepository {
	

	public void insert(Event event) throws RepositoryException;

	public void remove(int idEvent) throws EventNotFoundException, RepositoryException;

	public Event search(int idEvent) throws EventNotFoundException, RepositoryException;
	
	public List<Event> getEvents() throws RepositoryException;

	public void update(Event evento) throws EventNotFoundException, RepositoryException;

	public boolean isThere(int idEvent) throws RepositoryException;
	
	public int getEventLastId() throws RepositoryException;
	
	public int getEventIdByName(String eventName) throws RepositoryException;
	
{% if 'reportsFrequencyperEvent' in data.statments %}
	// Generated by DSL
	public List<String> getParticipantsPerEvent(int idEvent) throws RepositoryException;
	//
{% endif %}
}
