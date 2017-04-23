//#if ${Organizer} == "T"
package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.Organizer;
import riseevents.ev.exception.OrganizerAlreadyInsertedException;
import riseevents.ev.exception.OrganizerNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.OrganizerRepository;

public class OrganizerControl {

	private OrganizerRepository organizers;

	
	public OrganizerControl(OrganizerRepository organizerRepository){
		this.organizers = organizerRepository;

	}
	
	//usar instance of para saber qual o objeto dependendo disso direciona para o respectivo repositorio.
	public void insert(Organizer organizer) throws OrganizerAlreadyInsertedException, RepositoryException{
		if (organizer != null) {
			if (!organizers.isThere(organizer.getIdUser())) {
				organizers.insert(organizer);
			}else
				throw new OrganizerAlreadyInsertedException(organizer.getIdUser());
		} 
		else {
            throw new IllegalArgumentException();
        }
	}

	public void remove(int idUser) throws OrganizerAlreadyInsertedException, RepositoryException, OrganizerNotFoundException{
		if(organizers.isThere(idUser))
			organizers.remove(idUser);
		else
			throw new OrganizerNotFoundException(idUser);

	}
	
	public void update(Organizer organizer) throws OrganizerAlreadyInsertedException, RepositoryException, OrganizerNotFoundException{
		if(organizers.isThere(organizer.getIdUser()))
			organizers.update(organizer);
		else
			throw new OrganizerNotFoundException(organizer.getIdUser());
	}

	public Organizer search(int idUser) throws OrganizerAlreadyInsertedException, RepositoryException, OrganizerNotFoundException{
		return organizers.search(idUser);
	}

	public boolean isThere(int idUser) throws RepositoryException {
		return organizers.isThere(idUser);
	}

	public List<Organizer> getOrganizers() throws RepositoryException { 
		return organizers.getOrganizers();  
}

}
//#endif