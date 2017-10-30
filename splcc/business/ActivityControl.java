//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"

package {{systemName|lower}}.ev.business;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import {{systemName|lower}}.ev.data.Activity;
import {{systemName|lower}}.ev.exception.ActivityAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.ActivityNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.ActivityRepository;

import com.lowagie.text.DocumentException;


public class ActivityControl {
	
    private ActivityRepository activityList;
	
	public ActivityControl(ActivityRepository repository){
		this.activityList = repository;
	}
	{% if 'Insert' in data.commands %}
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
	{% endif %}
	{% if 'Remove' in data.commands %}
	public void remove(int idActivity) throws ActivityAlreadyInsertedException, RepositoryException, ActivityNotFoundException{
		activityList.remove(idActivity);
	}
	{% endif %}
	{% if 'Update' in data.commands %}
	public void update(Activity activity) throws ActivityAlreadyInsertedException, RepositoryException, ActivityNotFoundException{
		activityList.update(activity);
	}
	{% endif %}
	{% if 'Search' in data.commands %}
	public Activity search(int idActivity) throws ActivityAlreadyInsertedException, RepositoryException, ActivityNotFoundException{
		return activityList.search(idActivity);
	}
	{% endif %}
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
	
{% if 'reportsFrequencyPerActivity' in data.statments %}
	// Generated by DSL
	public void frequencyPerActivity(List<String> participantsPerActivity, Activity activity , String eventName) throws DocumentException, IOException{
		activity.frequencyPerActivity(participantsPerActivity, eventName);
	}
	//
{% endif %}
{% if 'reportsListOfAuthors' in data.statments %}
	// Generated by DSL
	public void listOfAuthorsPerActivity(Set<String> authorsPerActivity, Activity activity) throws DocumentException, IOException{
		activity.listOfAuthorsPerActivity(authorsPerActivity);
	}
	public List<String> getListOfAuthorsPerActivity(int idActivity) throws RepositoryException{
		return activityList.getListOfAuthorsPerActivity(idActivity);
	}
	//
{% endif %}
}
//#endif