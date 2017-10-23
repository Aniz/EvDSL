//#if ${RegistrationSpeakerActivity} == "T"
package evco.ev.business;

import java.util.List;

import evco.ev.data.Activity;
import evco.ev.data.ActivitySpeaker;
import evco.ev.exception.ActivitySpeakerAlreadyInsertedException;
import evco.ev.exception.ActivitySpeakerNotFoundException;
import evco.ev.exception.RepositoryException;
import evco.ev.repository.ActivitySpeakerRepository;


public class ActivitySpeakerControl {
	
    private ActivitySpeakerRepository activitySpeakerList;
	
	public ActivitySpeakerControl(ActivitySpeakerRepository repository){
		this.activitySpeakerList = repository;
	}
	
	public void insert(ActivitySpeaker activitySpeaker) throws ActivitySpeakerAlreadyInsertedException, RepositoryException{
		if (activitySpeaker != null) {
            if (activitySpeakerList.isThere(activitySpeaker) == false) {
            	activitySpeakerList.insert(activitySpeaker);
            } else {
                throw new ActivitySpeakerAlreadyInsertedException(activitySpeaker.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}

	public void remove(ActivitySpeaker activitySpeaker) throws ActivitySpeakerAlreadyInsertedException, RepositoryException, ActivitySpeakerNotFoundException{		
		activitySpeakerList.remove(activitySpeaker);
	}
	
	public void update(ActivitySpeaker activitySpeaker) throws ActivitySpeakerAlreadyInsertedException, RepositoryException, ActivitySpeakerNotFoundException{
		activitySpeakerList.update(activitySpeaker);
	}
	
	public ActivitySpeaker search(ActivitySpeaker activity) throws ActivitySpeakerAlreadyInsertedException, RepositoryException, ActivitySpeakerNotFoundException{
		return activitySpeakerList.search(activity);
	}

	public boolean isThere(ActivitySpeaker activitySpeaker) throws RepositoryException {
		return activitySpeakerList.isThere(activitySpeaker);
	}

	public List<ActivitySpeaker> getActivitySpeakerList() throws RepositoryException {
		return activitySpeakerList.getActivitySpeakerList();  
	}
	
	public int getActivitySpeakerLastId() throws RepositoryException{
		return activitySpeakerList.getActivitySpeakerLastId();
	}
	
	public List<ActivitySpeaker> getActivitiesById(int idActivity) throws RepositoryException{
		return activitySpeakerList.getActivitiesById(idActivity);
	}
}
//#endif