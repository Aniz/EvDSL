package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.Activity;
import riseevents.ev.data.Event;
import riseevents.ev.exception.EventNotFoundException;
import riseevents.ev.exception.RepositoryException;



public interface EventRepository {
	

	public void insert(Event event) throws RepositoryException;

	public void remove(int idEvent) throws EventNotFoundException, RepositoryException;

	public Event search(int idEvent) throws EventNotFoundException, RepositoryException;
	
	public List<Event> getEventList() throws RepositoryException;

	public void update(Event evento) throws EventNotFoundException, RepositoryException;

	public boolean isThere(int idEvent) throws RepositoryException;
	
	public int getEventLastId() throws RepositoryException;
	
	public int getEventIdByName(String eventName) throws RepositoryException;
	
}