//#if ${Speaker} == "T"
package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.Speaker;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.SpeakerAlreadyInsertedException;
import riseevents.ev.exception.SpeakerNotFoundException;
import riseevents.ev.repository.SpeakerRepository;

public class SpeakerControl {

	private SpeakerRepository speakerList;
	
	public SpeakerControl(SpeakerRepository repository){
		this.speakerList = repository;
	}
	
	public void insert(Speaker speaker) throws SpeakerAlreadyInsertedException, RepositoryException{
		if (speaker != null) {
			if (!speakerList.isThere(speaker.getIdUser())) 
				speakerList.insert(speaker);
			else
				throw new SpeakerAlreadyInsertedException(speaker.getIdUser());
		} else {
            throw new IllegalArgumentException();
        }
	}
	public void remove(int idUser) throws SpeakerAlreadyInsertedException, RepositoryException, SpeakerNotFoundException{
		if(speakerList.isThere(idUser))
			speakerList.remove(idUser);
		else
			throw new SpeakerNotFoundException(idUser);	 
	}
	public void update(Speaker speaker) throws SpeakerAlreadyInsertedException, RepositoryException, SpeakerNotFoundException{
		if(speakerList.isThere(speaker.getIdUser()))
			speakerList.update(speaker);
		else
			throw new SpeakerNotFoundException(speaker.getIdUser());	 
	}
	public Speaker search(int idUser) throws SpeakerAlreadyInsertedException, RepositoryException, SpeakerNotFoundException{
		return speakerList.search(idUser);
	}
	public boolean isThere(int idUser) throws RepositoryException {
		return speakerList.isThere(idUser);
	}

	public List<Speaker> getSpeakerList() throws RepositoryException { 
		return speakerList.getSpeakerList();  
}

}
//#endif