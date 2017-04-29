//#if ${Speaker} == "T"
package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.Speaker;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.SpeakerAlreadyInsertedException;
import riseevents.ev.exception.SpeakerNotFoundException;
import riseevents.ev.repository.SpeakerRepository;

public class SpeakerControl {

	private SpeakerRepository speakers;
	
	public SpeakerControl(SpeakerRepository repository){
		this.speakers = repository;
	}
	
	public void insert(Speaker speaker) throws SpeakerAlreadyInsertedException, RepositoryException{
		if (speaker != null) {
			if (!speakers.isThere(speaker.getIdUser())) 
				speakers.insert(speaker);
			else
				throw new SpeakerAlreadyInsertedException(speaker.getIdUser());
		} else {
            throw new IllegalArgumentException();
        }
	}
	public void remove(int idUser) throws SpeakerAlreadyInsertedException, RepositoryException, SpeakerNotFoundException{
		if(speakers.isThere(idUser))
			speakers.remove(idUser);
		else
			throw new SpeakerNotFoundException(idUser);	 
	}
	public void update(Speaker speaker) throws SpeakerAlreadyInsertedException, RepositoryException, SpeakerNotFoundException{
		if(speakers.isThere(speaker.getIdUser()))
			speakers.update(speaker);
		else
			throw new SpeakerNotFoundException(speaker.getIdUser());	 
	}
	public Speaker search(int idUser) throws SpeakerAlreadyInsertedException, RepositoryException, SpeakerNotFoundException{
		return speakers.search(idUser);
	}
	public boolean isThere(int idUser) throws RepositoryException {
		return speakers.isThere(idUser);
	}

	public List<Speaker> getSpeakers() throws RepositoryException { 
		return speakers.getSpeakers();  
}

}
//#endif