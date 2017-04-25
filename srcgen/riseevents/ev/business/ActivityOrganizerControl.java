//#if ${RegistrationOrganizerActivity} == "T"
package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.ActivityOrganizer;
import riseevents.ev.exception.ActivityOrganizerAlreadyInsertedException;
import riseevents.ev.exception.ActivityOrganizerNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.ActivityOrganizerRepository;

public class ActivityOrganizerControl {

private ActivityOrganizerRepository activitiesOrganizers;
	
	public ActivityOrganizerControl(ActivityOrganizerRepository repository){
		this.activitiesOrganizers = repository;
	}
	
	public void insert(ActivityOrganizer activityOrganizer) throws ActivityOrganizerAlreadyInsertedException, RepositoryException{
		if (activityOrganizer != null) {
            if (activitiesOrganizers.isThere(activityOrganizer) == false) {
            	activitiesOrganizers.insert(activityOrganizer);
            } else {
                throw new ActivityOrganizerAlreadyInsertedException(activityOrganizer.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}

	public void remove(ActivityOrganizer activityOrganizer) throws ActivityOrganizerAlreadyInsertedException, RepositoryException, ActivityOrganizerNotFoundException{		
		activitiesOrganizers.remove(activityOrganizer);
	}
	
	public void update(ActivityOrganizer activityOrganizer) throws ActivityOrganizerAlreadyInsertedException, RepositoryException, ActivityOrganizerNotFoundException{
		activitiesOrganizers.update(activityOrganizer);
	}
	
	public ActivityOrganizer search(int idActivity) throws ActivityOrganizerAlreadyInsertedException, RepositoryException, ActivityOrganizerNotFoundException{
		return activitiesOrganizers.search(idActivity);
	}

	public boolean isThere(ActivityOrganizer activityOrganizer) throws RepositoryException {
		return activitiesOrganizers.isThere(activityOrganizer);
	}

	public List<ActivityOrganizer> getActivitiesOrganizers() throws RepositoryException {
		return activitiesOrganizers.getActivitiesOrganizers();  
	}
	
	public int getActivityOrganizerLastId() throws RepositoryException{
		return activitiesOrganizers.getActivityOrganizerLastId();
	}
	
	public List<ActivityOrganizer> getActivitiesById(int idActivity) throws RepositoryException{
		return activitiesOrganizers.getActivitiesById(idActivity);
	}
}
//#endif