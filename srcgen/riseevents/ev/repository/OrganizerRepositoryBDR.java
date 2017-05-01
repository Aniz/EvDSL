//#if ${Organizer} == "T"
package riseevents.ev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import riseevents.ev.data.Organizer;
import riseevents.ev.data.User.TypeUser;
import riseevents.ev.data.Organizer.TypeOrganizer;
import riseevents.ev.data.User.TypeUser;
import riseevents.ev.exception.OrganizerNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.util.PersistenceMechanismException;
import riseevents.ev.util.PersistenceMechanismRDBMS;

public class OrganizerRepositoryBDR implements OrganizerRepository{

	private static OrganizerRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public OrganizerRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static OrganizerRepositoryBDR getInstance(){
		if(instance == null){
			instance = new OrganizerRepositoryBDR();
		}
		return instance;
	}

	@Override
	public void insert(Organizer organizer) throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO User Values('"+organizer.getIdUser()
				+"','" + organizer.getPassword()
				+"', '"+organizer.getNameUser()
				+"', '"+organizer.getEmail() 
				+"', '"+organizer.getFiliation()
				+"', '"+organizer.getTypeUser()
				+"', '"+organizer.getAaaa()   
				+"', '"+organizer.getBbbb()   
				+"')");

           	statement.executeUpdate("INSERT INTO organizer Values('"+organizer.getIdUser()
				+"', '"+organizer.getTypeOrganizer()
				+"', '"+organizer.getContato()   
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
	public void remove(int idUser) throws OrganizerNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM Organizer WHERE idUser = '"+ idUser+"'");
            if (i == 0) {
            	throw new OrganizerNotFoundException(idUser);
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
	public Organizer search(int idUser) throws OrganizerNotFoundException,
			RepositoryException {
		Organizer organizer = null;
		organizer = new Organizer();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from organizer as o inner join user on user.idUser = o.idUser and o.idUser =" + idUser);
            if (resultset.next()) {   
            	organizer.setIdUser(resultset.getInt("idUser"));
            	organizer.setPassword(resultset.getString("password"));
            	organizer.setNameUser(resultset.getString("nameUser"));       	
            	organizer.setEmail(resultset.getString("email"));
            	organizer.setFiliation(resultset.getString("filiation"));
				organizer.setTypeOrganizer(TypeOrganizer.valueOf(resultset.getString("typeOrganizer")));
				organizer.setContato(resultset.getString("contato"));
			
        
				organizer.setTypeUser(TypeUser.valueOf(resultset.getString("typeOrganizer")));
				organizer.setAaaa(resultset.getInt("aaaa"));
				organizer.setBbbb(resultset.getString("bbbb"));
			

            } else {
            	throw new OrganizerNotFoundException(idUser);
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
		return organizer;
	}

	@Override
	public List<Organizer> getOrganizerList() throws RepositoryException {
		Organizer organizer = null;
		ArrayList<Organizer> list = new ArrayList<Organizer>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select Organizer.idUser, password, nameUser, email, filiation from Organizer inner join User on Organizer.idUser = User.idUser;");
            while (resultset.next()) {
            	organizer = new Organizer();
            	organizer.setIdUser(resultset.getInt("idUser"));
            	organizer.setPassword(resultset.getString("password"));
            	organizer.setNameUser(resultset.getString("nameUser"));
            	organizer.setEmail(resultset.getString("email"));
            	organizer.setFiliation(resultset.getString("filiation"));
				organizer.setTypeOrganizer(TypeOrganizer.valueOf(resultset.getString("typeOrganizer")));
				organizer.setContato(resultset.getString("contato"));
			
        
				organizer.setTypeUser(TypeUser.valueOf(resultset.getString("typeUser")));
				organizer.setAaaa(resultset.getInt("aaaa"));
				organizer.setBbbb(resultset.getString("bbbb"));
			

				list.add(organizer);
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
	public void update(Organizer organizer) throws OrganizerNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();
            statement.executeUpdate("UPDATE User SET password = '"+ 
	    		organizer.getPassword() +
                "', nameUser = '"+ organizer.getNameUser() +
                "',email = '"+ organizer.getEmail() +
                "', filiation = '" + organizer.getFiliation() +

				"', typeUser = '"+ organizer.getTypeUser() + 
				"', aaaa = '"+ organizer.getAaaa() + 
				"', bbbb = '"+ organizer.getBbbb() + 
                "' WHERE idUser = '"+ organizer.getIdUser()+"'");

            	statement.executeUpdate("UPDATE organizer SET typeOrganizer = '"+ organizer.getTypeOrganizer() + 					"', contato = '"+ organizer.getContato() + 
            	"' WHERE idUser = '"+ organizer.getIdUser()+"'");   
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
	public boolean isThere(int idUser) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM Organizer WHERE idUser = '" + idUser + "'");
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