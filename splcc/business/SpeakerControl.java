//#if ${Speaker} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.Speaker;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.SpeakerAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.SpeakerNotFoundException;
import {{systemName|lower}}.ev.repository.SpeakerRepository;

public class SpeakerControl {

	private SpeakerRepository speakers;
	
	public SpeakerControl(SpeakerRepository repository){
		this.speakers = repository;
	}
	
	{% if 'Insert' in data.commands %}
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
	{% endif %}
	{% if 'Remove' in data.commands %}
	public void remove(int idUser) throws SpeakerAlreadyInsertedException, RepositoryException, SpeakerNotFoundException{
		if(speakers.isThere(idUser))
			speakers.remove(idUser);
		else
			throw new SpeakerNotFoundException(idUser);	 
	}
	{% endif %}
	{% if 'Update' in data.commands %}
	public void update(Speaker speaker) throws SpeakerAlreadyInsertedException, RepositoryException, SpeakerNotFoundException{
		if(speakers.isThere(speaker.getIdUser()))
			speakers.update(speaker);
		else
			throw new SpeakerNotFoundException(speaker.getIdUser());	 
	}
	{% endif %}
	{% if 'Search' in data.commands %}
	public Speaker search(int idUser) throws SpeakerAlreadyInsertedException, RepositoryException, SpeakerNotFoundException{
		return speakers.search(idUser);
	}
	{% endif %}
	public boolean isThere(int idUser) throws RepositoryException {
		return speakers.isThere(idUser);
	}

	public List<Speaker> getSpeakers() throws RepositoryException { 
		return speakers.getSpeakers();  
}

}
//#endif