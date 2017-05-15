//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"

package riseevents.ev.business;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import riseevents.ev.data.Activity;
import riseevents.ev.exception.ActivityAlreadyInsertedException;
import riseevents.ev.exception.ActivityNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.ActivityRepository;

import com.lowagie.text.DocumentException;


public class ActivityControl {
	
    private ActivityRepository activitieList;
	
	public ActivityControl(ActivityRepository repository){
		this.activitieList = repository;
	}
	public void insert(Activity activity) throws ActivityAlreadyInsertedException, RepositoryException{
		if (activity != null) {
            if (!activitieList.isThere(activity.getIdActivity())) {
                activitieList.insert(activity);
            } else {
                throw new ActivityAlreadyInsertedException(activity.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}
	public void remove(Activity activity) throws ActivityAlreadyInsertedException, RepositoryException, ActivityNotFoundException{
		activitieList.remove(activity);
	}
	public void update(Activity activity) throws ActivityAlreadyInsertedException, RepositoryException, ActivityNotFoundException{
		activitieList.update(activity);
	}
	public Activity search(int idActivity) throws ActivityAlreadyInsertedException, RepositoryException, ActivityNotFoundException{
		return activitieList.search(idActivity);
	}
	public boolean isThere(int idActivity) throws RepositoryException {
		return activitieList.isThere(idActivity);
	}

	public List<Activity> getActivityList() throws RepositoryException {
		return activitieList.getActivityList();  
	}
	
	public int getActivityLastId() throws RepositoryException{
		return activitieList.getActivityLastId();
	}
	
	public int getActivityIdByName(String activityName) throws RepositoryException{
		return activitieList.getActivityIdByName(activityName);
	}
	
	public List<Activity> getActivitiesByEvent(int idEvent) throws RepositoryException{
		return activitieList.getActivitiesByEvent(idEvent);
	}
	
	public float getEventMainTrackValue(int idEvent) throws RepositoryException{
		return activitieList.getEventMainTrackValue(idEvent);
	}
	
	public int getActivityMainTrackId(int idEvent) throws RepositoryException{
		return activitieList.getActivityMainTrackId(idEvent);
	}

	public int getEventbyActivity(int idActivity) throws RepositoryException{
		return activitieList.getEventbyActivity(idActivity);
	}
	
}
//#endif