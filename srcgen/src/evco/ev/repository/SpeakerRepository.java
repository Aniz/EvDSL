//#if ${Speaker} == "T"
package evco.ev.repository;

import java.util.List;

import evco.ev.data.Speaker;
import evco.ev.exception.RepositoryException;
import evco.ev.exception.SpeakerNotFoundException;

public interface SpeakerRepository {
	
	
	public void insert(Speaker speaker) throws RepositoryException;

	// retirado pq o delete on cascade ja sera usado. motivo: carantia da consistencia do banco
	public void remove(int idSpeaker) throws SpeakerNotFoundException, RepositoryException;

	public Speaker search(int idUser) throws SpeakerNotFoundException, RepositoryException;
	
	public List<Speaker> getSpeakerList() throws RepositoryException;

	public void update(Speaker speaker) throws SpeakerNotFoundException, RepositoryException;

	public boolean isThere(int idUser) throws RepositoryException;
	
	

}
//#endif