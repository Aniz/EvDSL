//#if ${RegistrationSpeakerActivity} == "T"
package evco.ev.repository;

import java.util.List;

import evco.ev.data.ActivitySpeaker;
import evco.ev.exception.ActivitySpeakerNotFoundException;
import evco.ev.exception.RepositoryException;



public interface ActivitySpeakerRepository {
	
	
	public void insert(ActivitySpeaker activityspeaker) throws RepositoryException;

	public void remove(ActivitySpeaker activityspeaker) throws ActivitySpeakerNotFoundException, RepositoryException;

	public ActivitySpeaker search(ActivitySpeaker activitySpeaker) throws ActivitySpeakerNotFoundException, RepositoryException;
	
	public List<ActivitySpeaker> getActivitySpeakerList() throws RepositoryException;

	public void update(ActivitySpeaker activityo) throws ActivitySpeakerNotFoundException, RepositoryException;

	public boolean isThere(ActivitySpeaker activitySpeaker) throws RepositoryException;
	
	public int getActivitySpeakerLastId() throws RepositoryException;
	
	public List<ActivitySpeaker> getActivitiesById(int idActivity) throws RepositoryException;

}
//#endif