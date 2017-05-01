//#if ${RegistrationOrganizerActivity} == "T"
package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.ActivityOrganizer;
import {{systemName|lower}}.ev.exception.ActivityOrganizerNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;

public interface ActivityOrganizerRepository {

	public void insert(ActivityOrganizer activityOrganzier) throws RepositoryException;

	public void remove(ActivityOrganizer activityOrganzier) throws ActivityOrganizerNotFoundException, RepositoryException;

	public ActivityOrganizer search(int idActivity) throws ActivityOrganizerNotFoundException, RepositoryException;
	
	public List<ActivityOrganizer> getActivityOrganizerList() throws RepositoryException;

	public void update(ActivityOrganizer activityOrganzier) throws ActivityOrganizerNotFoundException, RepositoryException;

	public boolean isThere(ActivityOrganizer activityOrganzier) throws RepositoryException;
	
	public int getActivityOrganizerLastId() throws RepositoryException;
	
	public List<ActivityOrganizer> getActivitiesById(int idActivity) throws RepositoryException;

}
//#endif