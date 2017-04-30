//#if ${Organizer} == "T"
package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.Organizer;
import {{systemName|lower}}.ev.exception.OrganizerNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;

public interface OrganizerRepository {

	public void insert(Organizer organizer) throws RepositoryException;

	// retirado pq o delete on cascade ja sera usado. motivo: carantia da consistencia do banco
	public void remove(int idUser) throws OrganizerNotFoundException, RepositoryException;

	public Organizer search(int idUser) throws OrganizerNotFoundException, RepositoryException;
	
	public List<Organizer> getOrganizerList() throws RepositoryException;

	public void update(Organizer organizer) throws OrganizerNotFoundException, RepositoryException;

	public boolean isThere(int idUser) throws RepositoryException;
}
//#endif