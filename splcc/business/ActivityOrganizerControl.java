//#if ${RegistrationOrganizerActivity} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.ActivityOrganizer;
import {{systemName|lower}}.ev.exception.ActivityOrganizerAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.ActivityOrganizerNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.ActivityOrganizerRepository;

public class ActivityOrganizerControl {

private ActivityOrganizerRepository actitivityOrganizerList;
	
	public ActivityOrganizerControl(ActivityOrganizerRepository repository){
		this.actitivityOrganizerList = repository;
	}
	
	public void insert(ActivityOrganizer activityOrganizer) throws ActivityOrganizerAlreadyInsertedException, RepositoryException{
		if (activityOrganizer != null) {
            if (actitivityOrganizerList.isThere(activityOrganizer) == false) {
            	actitivityOrganizerList.insert(activityOrganizer);
            } else {
                throw new ActivityOrganizerAlreadyInsertedException(activityOrganizer.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}

	public void remove(ActivityOrganizer activityOrganizer) throws ActivityOrganizerAlreadyInsertedException, RepositoryException, ActivityOrganizerNotFoundException{		
		actitivityOrganizerList.remove(activityOrganizer);
	}
	
	public void update(ActivityOrganizer activityOrganizer) throws ActivityOrganizerAlreadyInsertedException, RepositoryException, ActivityOrganizerNotFoundException{
		actitivityOrganizerList.update(activityOrganizer);
	}
	
	public ActivityOrganizer search(ActivityOrganizer activity) throws ActivityOrganizerAlreadyInsertedException, RepositoryException, ActivityOrganizerNotFoundException{
		return actitivityOrganizerList.search(activity);
	}

	public boolean isThere(ActivityOrganizer activityOrganizer) throws RepositoryException {
		return actitivityOrganizerList.isThere(activityOrganizer);
	}

	public List<ActivityOrganizer> getActivityOrganizerList() throws RepositoryException {
		return actitivityOrganizerList.getActivityOrganizerList();  
	}
	
	public int getActivityOrganizerLastId() throws RepositoryException{
		return actitivityOrganizerList.getActivityOrganizerLastId();
	}
	
	public List<ActivityOrganizer> getActivitiesById(int idActivity) throws RepositoryException{
		return actitivityOrganizerList.getActivitiesById(idActivity);
	}
}
//#endif