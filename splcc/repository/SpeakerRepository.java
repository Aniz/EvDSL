//#if ${Speaker} == "T"
package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.Speaker;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.SpeakerNotFoundException;

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