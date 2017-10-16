// Autogenerated by EvDSL
package riseevents.ev.repository;
import riseevents.ev.util.LibraryOfDSL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import riseevents.ev.data.NewOption;
import riseevents.ev.exception.NewOptionNotFoundException;
import riseevents.ev.exception.NewOptionAlreadyInsertedException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.util.PersistenceMechanismException;
import riseevents.ev.util.PersistenceMechanismRDBMS;
import riseevents.ev.data.NewOption.TypeNewOption;

public class NewOptionRepositoryBDR implements NewOptionRepository{
	
	private static NewOptionRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public NewOptionRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static NewOptionRepositoryBDR getInstance(){
		if(instance == null){
			instance = new NewOptionRepositoryBDR();
		}
		return instance;
	}
	
	@Override
	public void insert(NewOption newoption) throws RepositoryException{
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO NewOption (typeNewOption) Values('" +
newoption.getTypeNewOption()+"')");
		
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
	public void remove(int idNewOption) throws NewOptionNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM NewOption WHERE idNewOption = '"+ idNewOption+"'"); 
		    if (i == 0) {
            	throw new NewOptionNotFoundException(idNewOption);
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
	public NewOption search(int idEntity) throws NewOptionNotFoundException,
			RepositoryException {
		NewOption newoption = null;
		newoption = new NewOption();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from NewOption WHERE idNewOption =" + idEntity);
            if (resultset.next()) {  	
    			newoption.setTypeNewOption(TypeNewOption.valueOf(resultset.getString("typeNewOption")));
	
		     } else {
            	throw new NewOptionNotFoundException(idEntity);
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
		return newoption;
	}

	@Override
	public List<NewOption> getNewOptionList() throws RepositoryException {
		NewOption activity=null;
		ArrayList<NewOption> list = new ArrayList<NewOption>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from NewOption");
            while (resultset.next()) {
            	NewOption newoption = new NewOption();
    			newoption.setTypeNewOption(TypeNewOption.valueOf(resultset.getString("typeNewOption")));
	
				list.add(newoption);
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
	public void update(NewOption newoption) throws NewOptionNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();
    	    statement.executeUpdate("UPDATE NewOption SET   typeNewOption = '"+ newoption.getTypeNewOption() +"' WHERE idNewOption = '"+ newoption.getIdNewOption()+"'");

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
	public int getNewOptionLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EEventDB' AND TABLE_NAME= 'activityorganizer'");
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
	public List<NewOption> getEntityById(int idEntity) throws RepositoryException {
		NewOption newoption=null;
		ArrayList<NewOption> list = new ArrayList<NewOption>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from activityorganizer where idEntity ='"+ idEntity + "'");
            while (resultset.next()) {
            	newoption = new NewOption();
            	newoption.setIdNewOption(resultset.getInt("idNewOption"));
				list.add(newoption);
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
	public boolean isThere(int idEntity) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM NewOption WHERE idNewOption = '" + idEntity +"'");
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

}
//#endif