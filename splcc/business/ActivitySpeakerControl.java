//#if ${RegistrationSpeakerActivity} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.Activity;
import {{systemName|lower}}.ev.data.ActivitySpeaker;
import {{systemName|lower}}.ev.exception.ActivitySpeakerAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.ActivitySpeakerNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.ActivitySpeakerRepository;


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
	
	public ActivitySpeaker search(int idActivity) throws ActivitySpeakerAlreadyInsertedException, RepositoryException, ActivitySpeakerNotFoundException{
		return activitySpeakerList.search(idActivity);
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