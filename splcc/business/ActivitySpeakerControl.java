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
	
    private ActivitySpeakerRepository activitiesSpeakers;
	
	public ActivitySpeakerControl(ActivitySpeakerRepository repository){
		this.activitiesSpeakers = repository;
	}
	
	public void insert(ActivitySpeaker activitySpeaker) throws ActivitySpeakerAlreadyInsertedException, RepositoryException{
		if (activitySpeaker != null) {
            if (activitiesSpeakers.isThere(activitySpeaker) == false) {
            	activitiesSpeakers.insert(activitySpeaker);
            } else {
                throw new ActivitySpeakerAlreadyInsertedException(activitySpeaker.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}

	public void remove(ActivitySpeaker activitySpeaker) throws ActivitySpeakerAlreadyInsertedException, RepositoryException, ActivitySpeakerNotFoundException{		
		activitiesSpeakers.remove(activitySpeaker);
	}
	
	public void update(ActivitySpeaker activitySpeaker) throws ActivitySpeakerAlreadyInsertedException, RepositoryException, ActivitySpeakerNotFoundException{
		activitiesSpeakers.update(activitySpeaker);
	}
	
	public ActivitySpeaker search(int idActivity) throws ActivitySpeakerAlreadyInsertedException, RepositoryException, ActivitySpeakerNotFoundException{
		return activitiesSpeakers.search(idActivity);
	}

	public boolean isThere(ActivitySpeaker activitySpeaker) throws RepositoryException {
		return activitiesSpeakers.isThere(activitySpeaker);
	}

	public List<ActivitySpeaker> getActivitiesSpeakers() throws RepositoryException {
		return activitiesSpeakers.getActivitiesSpeakers();  
	}
	
	public int getActivitySpeakerLastId() throws RepositoryException{
		return activitiesSpeakers.getActivitySpeakerLastId();
	}
	
	public List<ActivitySpeaker> getActivitiesById(int idActivity) throws RepositoryException{
		return activitiesSpeakers.getActivitiesById(idActivity);
	}
}
//#endif