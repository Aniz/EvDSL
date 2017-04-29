//#if ${Organizer} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.Organizer;
import {{systemName|lower}}.ev.exception.OrganizerAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.OrganizerNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.OrganizerRepository;

public class OrganizerControl {

	private OrganizerRepository organizers;

	
	public OrganizerControl(OrganizerRepository organizerRepository){
		this.organizers = organizerRepository;

	}
	
	{% if 'Insert' in data.commands %}
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
	{% endif %}
	{% if 'Remove' in data.commands %}
	public void remove(int idUser) throws OrganizerAlreadyInsertedException, RepositoryException, OrganizerNotFoundException{
		if(organizers.isThere(idUser))
			organizers.remove(idUser);
		else
			throw new OrganizerNotFoundException(idUser);

	}
	{% endif %}
	{% if 'Update' in data.commands %}
	public void update(Organizer organizer) throws OrganizerAlreadyInsertedException, RepositoryException, OrganizerNotFoundException{
		if(organizers.isThere(organizer.getIdUser()))
			organizers.update(organizer);
		else
			throw new OrganizerNotFoundException(organizer.getIdUser());
	}
	{% endif %}
	{% if 'Search' in data.commands %}
	public Organizer search(int idUser) throws OrganizerAlreadyInsertedException, RepositoryException, OrganizerNotFoundException{
		return organizers.search(idUser);
	}
	{% endif %}
	
	public boolean isThere(int idUser) throws RepositoryException {
		return organizers.isThere(idUser);
	}

	public List<Organizer> getOrganizers() throws RepositoryException { 
		return organizers.getOrganizers();  
}

}
//#endif