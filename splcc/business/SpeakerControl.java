//#if ${Speaker} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.Speaker;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.SpeakerAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.SpeakerNotFoundException;
import {{systemName|lower}}.ev.repository.SpeakerRepository;

public class SpeakerControl {

	private SpeakerRepository speakerList;
	
	public SpeakerControl(SpeakerRepository repository){
		this.speakerList = repository;
	}
	
	{% if 'Insert' in data.commands %}
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
	{% endif %}
	{% if 'Remove' in data.commands %}
	public void remove(int idUser) throws SpeakerAlreadyInsertedException, RepositoryException, SpeakerNotFoundException{
		if(speakerList.isThere(idUser))
			speakerList.remove(idUser);
		else
			throw new SpeakerNotFoundException(idUser);	 
	}
	{% endif %}
	{% if 'Update' in data.commands %}
	public void update(Speaker speaker) throws SpeakerAlreadyInsertedException, RepositoryException, SpeakerNotFoundException{
		if(speakerList.isThere(speaker.getIdUser()))
			speakerList.update(speaker);
		else
			throw new SpeakerNotFoundException(speaker.getIdUser());	 
	}
	{% endif %}
	{% if 'Search' in data.commands %}
	public Speaker search(int idUser) throws SpeakerAlreadyInsertedException, RepositoryException, SpeakerNotFoundException{
		return speakerList.search(idUser);
	}
	{% endif %}
	public boolean isThere(int idUser) throws RepositoryException {
		return speakerList.isThere(idUser);
	}

	public List<Speaker> getSpeakerList() throws RepositoryException { 
		return speakerList.getSpeakerList();  
}

}
//#endif