//#if ${RegistrationUserActivity} == "T"
package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.ActivityUser;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.ActivityUserNotFoundException;

public interface ActivityUserRepository {
	
	public void insert(ActivityUser activityUser) throws RepositoryException;

	public void remove(ActivityUser activityUser) throws ActivityUserNotFoundException, RepositoryException;

	public ActivityUser search(int idUser) throws ActivityUserNotFoundException, RepositoryException;
	
	public List<ActivityUser> getActivityUserList() throws RepositoryException;

	public void update(ActivityUser activityUser) throws ActivityUserNotFoundException, RepositoryException;

	public boolean isThere(ActivityUser activityUser) throws RepositoryException;
	
	public int getActivityUserLastId() throws RepositoryException;
	
	public List<ActivityUser> getActivitiesById(int idActivity) throws RepositoryException;
	
	{% if 'reportsFrequencyperActivity' in data.statments %}
	// Generated by DSL
	public List<String> getParticipantsPerActivity(int idActivity) throws RepositoryException;
	//
	{% endif %}
}
//#endif