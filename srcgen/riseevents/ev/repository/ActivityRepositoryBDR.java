//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
package riseevents.ev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import riseevents.ev.data.Activity;
import riseevents.ev.data.Activity.TypeActivity;
import riseevents.ev.exception.ActivityNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.util.PersistenceMechanismException;
import riseevents.ev.util.PersistenceMechanismRDBMS;

public class ActivityRepositoryBDR implements ActivityRepository {
	
	private static ActivityRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public ActivityRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static ActivityRepositoryBDR getInstance(){
		if(instance == null){
			instance = new ActivityRepositoryBDR();
		}
		return instance;
	}

	
	@Override
	public void insert(Activity activity) throws RepositoryException{
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO Activity (idEvent, nameActivity, descriptionActivity, value, hourlyLoad, date, hour, numberOfParticipants, registrationLimit ,typeActivity,aaaa,bbbb) Values ('"
								+ activity.getIdEvent()
					            +"', '"+ activity.getNameActivity()
					            +"', '"+ activity.getDescriptionActivity()
					            +"', '"+ activity.getValue() 
					            +"', '"+ activity.getHourlyLoad()
					            +"', '"+ activity.getDate()
					            +"', '"+ activity.getHour()
					            +"', '"+ activity.getNumberOfParticipants()
					            +activity.getRegistrationLimit()
							    +"', '"+activity.getTypeActivity()
	
								+"', '"+activity.getAaaa()   
								+"', '"+activity.getBbbb()   
						+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
	}

	@Override
	public void remove(int idActivity) throws ActivityNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM Activity WHERE idActivity = '"+ idActivity+"'");
            if (i == 0) {
            	throw new ActivityNotFoundException(idActivity);
            }
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
		} catch(SQLException e){
            throw new RepositoryException(e);            
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
	}

	@Override
	public Activity search(int idActivity) throws ActivityNotFoundException,
			RepositoryException {
		Activity activity = null;
		activity = new Activity();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from Activity WHERE idActivity =" + idActivity);
            if (resultset.next()) {   
            	activity.setIdActivity(resultset.getInt("idActivity"));
            	activity.setIdEvent(resultset.getInt("idEvent"));
            	activity.setNameActivity(resultset.getString("nameActivity"));
            	activity.setDescriptionActivity(resultset.getString("descriptionActivity"));
            	activity.setValue(resultset.getFloat("value"));
            	activity.setHourlyLoad(resultset.getFloat("hourlyLoad"));
            	activity.setDate(resultset.getString("date"));
            	activity.setHour(resultset.getString("hour"));
            	activity.setNumberOfParticipants(resultset.getInt("numberOfParticipants"));
            	activity.setRegistrationLimit(resultset.getInt("registrationLimit"));
    			activity.setTypeActivity(TypeActivity.valueOf(resultset.getString("typeActivity")));
				activity.setAaaa(resultset.getInt("aaaa"));
				activity.setBbbb(resultset.getString("bbbb"));
            } else {
            	throw new ActivityNotFoundException(idActivity);
            }
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return activity;
	}

	@Override
	public List<Activity> getActivityList() throws RepositoryException {
		Activity activity=null;
		ArrayList<Activity> list = new ArrayList<Activity>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from Activity");
            while (resultset.next()) {
            	activity = new Activity();
            	activity.setIdActivity(resultset.getInt("idActivity"));
            	activity.setIdEvent(resultset.getInt("idEvent"));
            	activity.setNameActivity(resultset.getString("nameActivity"));
            	activity.setDescriptionActivity(resultset.getString("descriptionActivity"));
            	activity.setValue(resultset.getFloat("value"));
            	activity.setHourlyLoad(resultset.getFloat("hourlyLoad"));
            	activity.setDate(resultset.getString("date"));
            	activity.setHour(resultset.getString("hour"));
            	activity.setNumberOfParticipants(resultset.getInt("numberOfParticipants"));
            	activity.setRegistrationLimit(resultset.getInt("registrationLimit"));
    			activity.setTypeActivity(TypeActivity.valueOf(resultset.getString("typeActivity")));
				activity.setAaaa(resultset.getInt("aaaa"));
				activity.setBbbb(resultset.getString("bbbb"));
	
				list.add(activity);
            } 
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return list;
	}

	@Override
	public void update(Activity activity) throws ActivityNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

    	    statement.executeUpdate("UPDATE Activity SET nameActivity = '"+ activity.getNameActivity() +
    	    										 "', idEvent = '" + activity.getIdEvent()+
    	    		                                 "', descriptionActivity = '" + activity.getDescriptionActivity() +
    	    		                                 "', activityType = '"+ activity.getTypeActivity() +
    	    		                                 "', value = '"+ activity.getValue() + 
    	    		                                 "', hourlyLoad = '"+ activity.getHourlyLoad()+ 
    	    		                                 "', date = '"+ activity.getDate() + 
    	    		                                 "', hour = '"+ activity.getHour() +
    	    		                                 "', numberOfParticipants = '"+ activity.getNumberOfParticipants() +
    	    		                                 "', registrationLimit = '"+ activity.getRegistrationLimit() +
									    	        "', typeActivity = '"+ activity.getTypeActivity() +
											         "', aaaa = '"+ activity.getAaaa() +
											         "', bbbb = '"+ activity.getBbbb() +
											         "' WHERE idActivity = '"+ activity.getIdActivity()+"'");

		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
		} catch (SQLException e) {
		    throw new RepositoryException(e);
	    } finally {
	    	try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
	}

	@Override
	public boolean isThere(int idActivity) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM Activity WHERE idActivity = '" + idActivity + "'");
            answer = resultset.next();
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
        return answer;
	}

	@Override
	public int getActivityLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EEventDB' AND TABLE_NAME= 'Activity'");
            resultset.first();
            answer = resultset.getInt("proximo_valor");
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
        return answer;
	}
	@Override
	public int getActivityIdByName(String nameActivity) throws RepositoryException{
		int answer = -1;
		try{
			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("Select idActivity from Activity where NameActivity = '" + nameActivity + "'");
			resultset.first();
			answer = resultset.getInt("idActivity");
			resultset.close();
		} catch(PersistenceMechanismException e){
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
	}
    return answer;
	}
	
	public int getEventbyActivity(int idActivity) throws RepositoryException{
		int answer = -1;
		try{
			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("Select idEvent from Activity where idActivity = '" + idActivity + "'");
			resultset.first();
			answer = resultset.getInt("idEvent");
			resultset.close();
		} catch(PersistenceMechanismException e){
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
	}
    return answer;
	}
	
	@Override
	public List<Activity> getActivitiesByEvent(int idEvent) throws RepositoryException{
		Activity activity=null;
		ArrayList<Activity> list = new ArrayList<Activity>();
		 try {
	            Statement statement = (Statement) pm.getCommunicationChannel();
	            ResultSet resultset = statement.executeQuery("select * from Activity where idEvent = '"+ idEvent+ "'");
	            while (resultset.next()) {
	            	activity = new Activity();
	            	activity.setIdActivity(resultset.getInt("idActivity"));
	            	activity.setIdEvent(resultset.getInt("idEvent"));
	            	activity.setNameActivity(resultset.getString("nameActivity"));
	            	activity.setDescriptionActivity(resultset.getString("descriptionActivity"));
	            	activity.setValue(resultset.getFloat("value"));
	            	activity.setHourlyLoad(resultset.getFloat("hourlyLoad"));
	            	activity.setDate(resultset.getString("date"));
	            	activity.setHour(resultset.getString("hour"));
	            	activity.setNumberOfParticipants(resultset.getInt("numberOfParticipants"));
	            	activity.setRegistrationLimit(resultset.getInt("registrationLimit"));			
					activity.setTypeActivity(TypeActivity.valueOf(resultset.getString("typeActivity")));						    	    
					activity.setAaaa(resultset.getInt("aaaa"));
					activity.setBbbb(resultset.getString("bbbb"));
	
					list.add(activity);
	            } 
				resultset.close();
			} catch(PersistenceMechanismException e){
	            throw new RepositoryException(e);
	        } catch (SQLException e) {
				throw new RepositoryException(e);
			} finally {
				try {
					pm.releaseCommunicationChannel();
				} catch (PersistenceMechanismException ex) {
					throw new RepositoryException(ex);
				}
			}
			return list;
	}
	
	public float getEventMainTrackValue(int idEvent) throws RepositoryException{
		float value = -1;
		try{
			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("select value from activity where activity.`idEvent` = '" + idEvent + "and activity.activityType = MainTrack" + "'");
			resultset.first();
			value = resultset.getInt("value");
			resultset.close();
		} catch(PersistenceMechanismException e){
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
	}
    return value;
	}
	
	public int getActivityMainTrackId(int idEvent) throws RepositoryException{
		int value = -1;
		try{
			Statement statement = (Statement) pm.getCommunicationChannel();
			ResultSet resultset = statement.executeQuery("select idActivity from activity where activity.`idEvent` = '" + idEvent + "and activity.activityType = MainTrack" + "'");
			resultset.first();
			value = resultset.getInt("idActivity");
			resultset.close();
		} catch(PersistenceMechanismException e){
			throw new RepositoryException(e);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
	}
    return value;
	}

}
//#endif