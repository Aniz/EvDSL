//#if ${Organizer} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.Organizer;
import {{systemName|lower}}.ev.exception.OrganizerAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.OrganizerNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.OrganizerRepository;

public class OrganizerControl {

	private OrganizerRepository organizerList;

	
	public OrganizerControl(OrganizerRepository organizerRepository){
		this.organizerList = organizerRepository;

	}
	
	{% if 'Insert' in data.commands %}
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
	{% endif %}
	{% if 'Remove' in data.commands %}
	public void remove(int idUser) throws OrganizerAlreadyInsertedException, RepositoryException, OrganizerNotFoundException{
		if(organizerList.isThere(idUser))
			organizerList.remove(idUser);
		else
			throw new OrganizerNotFoundException(idUser);

	}
	{% endif %}
	{% if 'Update' in data.commands %}
	public void update(Organizer organizer) throws OrganizerAlreadyInsertedException, RepositoryException, OrganizerNotFoundException{
		if(organizerList.isThere(organizer.getIdUser()))
			organizerList.update(organizer);
		else
			throw new OrganizerNotFoundException(organizer.getIdUser());
	}
	{% endif %}
	{% if 'Search' in data.commands %}
	public Organizer search(int idUser) throws OrganizerAlreadyInsertedException, RepositoryException, OrganizerNotFoundException{
		return organizerList.search(idUser);
	}
	{% endif %}
	
	public boolean isThere(int idUser) throws RepositoryException {
		return organizerList.isThere(idUser);
	}

	public List<Organizer> getOrganizerList() throws RepositoryException { 
		return organizerList.getOrganizerList();  
}

}
//#endif