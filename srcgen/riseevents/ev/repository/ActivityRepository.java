//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.Activity;
import riseevents.ev.exception.ActivityNotFoundException;
import riseevents.ev.exception.RepositoryException;



public interface ActivityRepository {
	
	
	public void insert(Activity activity) throws RepositoryException;

	public void remove(int idActivity) throws ActivityNotFoundException, RepositoryException;

	public Activity search(int idActivity) throws ActivityNotFoundException, RepositoryException;
	
	public List<Activity> getActivities() throws RepositoryException;

	public void update(Activity activityo) throws ActivityNotFoundException, RepositoryException;

	public boolean isThere(int idActivity) throws RepositoryException;
	
	public int getActivityLastId() throws RepositoryException;
	
	public int getActivityIdByName(String activityName) throws RepositoryException;
	
	public List<Activity> getActivitiesByEvent(int idEvent) throws RepositoryException;
	
    public float getEventMainTrackValue(int idEvent) throws RepositoryException;
	
	public int getActivityMainTrackId(int idEvent) throws RepositoryException;

	public int getEventbyActivity(int idActivity) throws RepositoryException;
	
}
//#endif