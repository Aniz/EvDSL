//#if ${RegistrationOrganizerActivity} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.ActivityOrganizer;
import riseevents.ev.exception.ActivityOrganizerNotFoundException;
import riseevents.ev.exception.RepositoryException;

public interface ActivityOrganizerRepository {

	public void insert(ActivityOrganizer activityOrganzier) throws RepositoryException;

	public void remove(ActivityOrganizer activityOrganzier) throws ActivityOrganizerNotFoundException, RepositoryException;

	public ActivityOrganizer search(ActivityOrganizer activityOrganizer) throws ActivityOrganizerNotFoundException, RepositoryException;
	
	public List<ActivityOrganizer> getActivityOrganizerList() throws RepositoryException;

	public void update(ActivityOrganizer activityOrganzier) throws ActivityOrganizerNotFoundException, RepositoryException;

	public boolean isThere(ActivityOrganizer activityOrganzier) throws RepositoryException;
	
	public int getActivityOrganizerLastId() throws RepositoryException;
	
	public List<ActivityOrganizer> getActivitiesById(int idActivity) throws RepositoryException;

}
//#endif