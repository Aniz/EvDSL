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
	
    private ActivityRepository activityList;
	
	public ActivityControl(ActivityRepository repository){
		this.activityList = repository;
	}
	public void insert(Activity activity) throws ActivityAlreadyInsertedException, RepositoryException{
		if (activity != null) {
            if (!activityList.isThere(activity.getIdActivity())) {
                activityList.insert(activity);
            } else {
                throw new ActivityAlreadyInsertedException(activity.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}
	public void remove(int idActivity) throws ActivityAlreadyInsertedException, RepositoryException, ActivityNotFoundException{
		activityList.remove(idActivity);
	}
	public void update(Activity activity) throws ActivityAlreadyInsertedException, RepositoryException, ActivityNotFoundException{
		activityList.update(activity);
	}
	public Activity search(int idActivity) throws ActivityAlreadyInsertedException, RepositoryException, ActivityNotFoundException{
		return activityList.search(idActivity);
	}
	public boolean isThere(int idActivity) throws RepositoryException {
		return activityList.isThere(idActivity);
	}

	public List<Activity> getActivityList() throws RepositoryException {
		return activityList.getActivityList();  
	}
	
	public int getActivityLastId() throws RepositoryException{
		return activityList.getActivityLastId();
	}
	
	public int getActivityIdByName(String activityName) throws RepositoryException{
		return activityList.getActivityIdByName(activityName);
	}
	
	public List<Activity> getActivitiesByEvent(int idEvent) throws RepositoryException{
		return activityList.getActivitiesByEvent(idEvent);
	}
	
	public float getEventMainTrackValue(int idEvent) throws RepositoryException{
		return activityList.getEventMainTrackValue(idEvent);
	}
	
	public int getActivityMainTrackId(int idEvent) throws RepositoryException{
		return activityList.getActivityMainTrackId(idEvent);
	}

	public int getEventbyActivity(int idActivity) throws RepositoryException{
		return activityList.getEventbyActivity(idActivity);
	}
	
}
//#endif