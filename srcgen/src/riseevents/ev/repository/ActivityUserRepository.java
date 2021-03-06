//#if ${RegistrationUserActivity} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.ActivityUser;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.ActivityUserNotFoundException;

public interface ActivityUserRepository {
	
	public void insert(ActivityUser activityUser) throws RepositoryException;

	public void remove(ActivityUser activityUser) throws ActivityUserNotFoundException, RepositoryException;

	public ActivityUser search(int idUser) throws ActivityUserNotFoundException, RepositoryException;
	
	public List<ActivityUser> getActivityUserList() throws RepositoryException;

	public void update(ActivityUser activityUser) throws ActivityUserNotFoundException, RepositoryException;

	public boolean isThere(ActivityUser activityUser) throws RepositoryException;
	
	public int getActivityUserLastId() throws RepositoryException;
	
	public List<ActivityUser> getActivitiesById(int idActivity) throws RepositoryException;
	
}
//#endif