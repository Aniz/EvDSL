//#if ${RegistrationSpeakerActivity} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.ActivitySpeaker;
import riseevents.ev.exception.ActivitySpeakerNotFoundException;
import riseevents.ev.exception.RepositoryException;



public interface ActivitySpeakerRepository {
	
	
	public void insert(ActivitySpeaker activityspeaker) throws RepositoryException;

	public void remove(ActivitySpeaker activityspeaker) throws ActivitySpeakerNotFoundException, RepositoryException;

	public ActivitySpeaker search(int idActivity) throws ActivitySpeakerNotFoundException, RepositoryException;
	
	public List<ActivitySpeaker> getActivitySpeakerList() throws RepositoryException;

	public void update(ActivitySpeaker activityo) throws ActivitySpeakerNotFoundException, RepositoryException;

	public boolean isThere(ActivitySpeaker activitySpeaker) throws RepositoryException;
	
	public int getActivitySpeakerLastId() throws RepositoryException;
	
	public List<ActivitySpeaker> getActivitiesById(int idActivity) throws RepositoryException;

}
//#endif