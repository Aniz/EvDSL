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
	
	public boolean isThere(int idUser) throws RepositoryException {
		return speakerList.isThere(idUser);
	}

	public List<Speaker> getSpeakerList() throws RepositoryException { 
		return speakerList.getSpeakerList();  
}

}
//#endif