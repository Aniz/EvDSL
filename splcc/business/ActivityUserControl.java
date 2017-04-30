//#if ${RegistrationUserActivity} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.ActivityUser;
import {{systemName|lower}}.ev.exception.ActivityUserAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.ActivityUserNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.ActivityUserRepository;

public class ActivityUserControl {

	private ActivityUserRepository activityUserList;
	
	public ActivityUserControl(ActivityUserRepository repository){
		this.activityUserList = repository;
	}
	
	public void insert(ActivityUser activityUser) throws ActivityUserAlreadyInsertedException, RepositoryException{
		if (activityUser != null) {
            if (activityUserList.isThere(activityUser) == false) {
            	activityUserList.insert(activityUser);
            } else {
                throw new ActivityUserAlreadyInsertedException(activityUser.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(ActivityUser activityUser) throws ActivityUserAlreadyInsertedException, RepositoryException, ActivityUserNotFoundException{		
		activityUserList.remove(activityUser);
	}
	
	public void update(ActivityUser activityUser) throws ActivityUserAlreadyInsertedException, RepositoryException, ActivityUserNotFoundException{
		activityUserList.update(activityUser);
	}
	
	public ActivityUser search(int idActivity) throws ActivityUserAlreadyInsertedException, RepositoryException, ActivityUserNotFoundException{
		return activityUserList.search(idActivity);
	}

	public boolean isThere(ActivityUser activityUser) throws RepositoryException {
		return activityUserList.isThere(activityUser);
	}

	public List<ActivityUser> getActivityUserList() throws RepositoryException {
		return activityUserList.getActivityUserList();  
	}
	
	public int getActivityUserLastId() throws RepositoryException{
		return activityUserList.getActivityUserLastId(); 
	}
	
	public List<ActivityUser> getActivitiesById(int idActivity) throws RepositoryException{
		return activityUserList.getActivitiesById(idActivity);
	}
	
	public List<String> getParticipantsPerActivity(int idActivity) throws RepositoryException{
		return activityUserList.getParticipantsPerActivity(idActivity);
	}
}
//#endif