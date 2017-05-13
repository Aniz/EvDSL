//#if ${Organizer} == "T"
package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.Organizer;
import riseevents.ev.exception.OrganizerAlreadyInsertedException;
import riseevents.ev.exception.OrganizerNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.OrganizerRepository;

public class OrganizerControl {

	private OrganizerRepository organizerList;

	
	public OrganizerControl(OrganizerRepository organizerRepository){
		this.organizerList = organizerRepository;

	}
	
	public void insert(Organizer organizer) throws OrganizerAlreadyInsertedException, RepositoryException{
		if (organizer != null) {
			if (!organizerList.isThere(organizer.getIdUser())) {
				organizerList.insert(organizer);
			}else
				throw new OrganizerAlreadyInsertedException(organizer.getIdUser());
		} 
		else {
            throw new IllegalArgumentException();
        }
	}
	public void remove(int idUser) throws OrganizerAlreadyInsertedException, RepositoryException, OrganizerNotFoundException{
		if(organizerList.isThere(idUser))
			organizerList.remove(idUser);
		else
			throw new OrganizerNotFoundException(idUser);

	}
	public void update(Organizer organizer) throws OrganizerAlreadyInsertedException, RepositoryException, OrganizerNotFoundException{
		if(organizerList.isThere(organizer.getIdUser()))
			organizerList.update(organizer);
		else
			throw new OrganizerNotFoundException(organizer.getIdUser());
	}
	public Organizer search(int idUser) throws OrganizerAlreadyInsertedException, RepositoryException, OrganizerNotFoundException{
		return organizerList.search(idUser);
	}
	
	public boolean isThere(int idUser) throws RepositoryException {
		return organizerList.isThere(idUser);
	}

	public List<Organizer> getOrganizerList() throws RepositoryException { 
		return organizerList.getOrganizerList();  
}

}
//#endif