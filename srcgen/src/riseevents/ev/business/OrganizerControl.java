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
	
	
	public boolean isThere(int idUser) throws RepositoryException {
		return organizerList.isThere(idUser);
	}

	public List<Organizer> getOrganizerList() throws RepositoryException { 
		return organizerList.getOrganizerList();  
}

}
//#endif