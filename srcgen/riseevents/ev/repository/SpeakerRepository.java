//#if ${Speaker} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.Speaker;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.SpeakerNotFoundException;

public interface SpeakerRepository {
	
	
	public void insert(Speaker speaker) throws RepositoryException;

	// retirado pq o delete on cascade ja sera usado. motivo: carantia da consistencia do banco
	public void remove(int idSpeaker) throws SpeakerNotFoundException, RepositoryException;

	public Speaker search(int idUser) throws SpeakerNotFoundException, RepositoryException;
	
	public List<Speaker> getSpeakers() throws RepositoryException;

	public void update(Speaker speaker) throws SpeakerNotFoundException, RepositoryException;

	public boolean isThere(int idUser) throws RepositoryException;
	
	

}
//#endif