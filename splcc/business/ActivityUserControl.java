//#if ${RegistrationUserActivity} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.ActivityUser;
import {{systemName|lower}}.ev.exception.ActivityUserAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.ActivityUserNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.ActivityUserRepository;

public class ActivityUserControl {

	private ActivityUserRepository activitiesUsers;
	
	public ActivityUserControl(ActivityUserRepository repository){
		this.activitiesUsers = repository;
	}
	
	public void insert(ActivityUser activityUser) throws ActivityUserAlreadyInsertedException, RepositoryException{
		if (activityUser != null) {
            if (activitiesUsers.isThere(activityUser) == false) {
            	activitiesUsers.insert(activityUser);
            } else {
                throw new ActivityUserAlreadyInsertedException(activityUser.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(ActivityUser activityUser) throws ActivityUserAlreadyInsertedException, RepositoryException, ActivityUserNotFoundException{		
		activitiesUsers.remove(activityUser);
	}
	
	public void update(ActivityUser activityUser) throws ActivityUserAlreadyInsertedException, RepositoryException, ActivityUserNotFoundException{
		activitiesUsers.update(activityUser);
	}
	
	public ActivityUser search(int idActivity) throws ActivityUserAlreadyInsertedException, RepositoryException, ActivityUserNotFoundException{
		return activitiesUsers.search(idActivity);
	}

	public boolean isThere(ActivityUser activityUser) throws RepositoryException {
		return activitiesUsers.isThere(activityUser);
	}

	public List<ActivityUser> getActivitiesUsers() throws RepositoryException {
		return activitiesUsers.getActivitiesUsers();  
	}
	
	public int getActivityUserLastId() throws RepositoryException{
		return activitiesUsers.getActivityUserLastId(); 
	}
	
	public List<ActivityUser> getActivitiesById(int idActivity) throws RepositoryException{
		return activitiesUsers.getActivitiesById(idActivity);
	}
	
	public List<String> getParticipantsPerActivity(int idActivity) throws RepositoryException{
		return activitiesUsers.getParticipantsPerActivity(idActivity);
	}
}
//#endif