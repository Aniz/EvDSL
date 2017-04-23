//#if ${Reviewer} == "T"
package riseevents.ev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import riseevents.ev.data.Reviewer;
import riseevents.ev.data.Reviewer.TypeReviewer;

import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.ReviewerNotFoundException;
import riseevents.ev.util.PersistenceMechanismException;
import riseevents.ev.util.PersistenceMechanismRDBMS;

public class ReviewerRepositoryBDR implements ReviewerRepository{
	
	
	private static ReviewerRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	
	public ReviewerRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static ReviewerRepositoryBDR getInstance(){
		if(instance == null){
			instance = new ReviewerRepositoryBDR();
		}
		return instance;
	}
	
	@Override
	public void insert(Reviewer reviewer) throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO User Values('"+reviewer.getIdUser()
				+"','" +reviewer.getPassword()
				+"', '"+reviewer.getNameUser()
				+"', '"+reviewer.getTypeUser()
				+"', '"+reviewer.getEmail() 
				+"', '"+reviewer.getFiliation()
				+"', '"+reviewer.getTypeUser()
				+"', '"+reviewer.getContato()   
				+"')");

			statement.executeUpdate("INSERT INTO reviewer Values('"+reviewer.getIdUser()
				+"','" + reviewer.getKnowledgeArea()
				+"', '"+reviewer.getTypeReviewer()
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
	public void remove(int idUser) throws ReviewerNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM reviewer WHERE idUser = '"+ idUser+"'");
            if (i == 0) {
            	throw new ReviewerNotFoundException(idUser);
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
	public Reviewer search(int idUser) throws ReviewerNotFoundException,
			RepositoryException {
		Reviewer reviewer = null;
		reviewer = new Reviewer();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from reviewer as r inner join user on user.idUser = r.idUser and r.idUser =" + idUser);
            if (resultset.next()) {   
            	reviewer.setIdUser(resultset.getInt("idUser"));
            	reviewer.setKnowledgeArea(resultset.getString("knowledgeArea"));
            	reviewer.setPassword(resultset.getString("password"));
            	reviewer.setNameUser(resultset.getString("nameUser"));       	
            	reviewer.setEmail(resultset.getString("email"));
            	reviewer.setFiliation(resultset.getString("filiation"));
			 	reviewer.setTypeReviewer(TypeReviewer.valueOf(resultset.getString("typeReviewer")));

			 	reviewer.setTypeUser(TypeUser.valueOf(resultset.getString("typeUser")));
				reviewer.setContato(resultset.getString("contato"));
			
        	resultset.close();   
            } else {
            	throw new ReviewerNotFoundException(idUser);
            }
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
		return reviewer;
	}

	@Override
	public List<Reviewer> getReviewers() throws RepositoryException {
		Reviewer reviewer = null;
		ArrayList<Reviewer> list = new ArrayList<Reviewer>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select reviewer.idUser, password, nameUser, typeUser, email, filiation, knowledgeArea from reviewer inner join User on reviewer.idUser = user.idUser;");
            while (resultset.next()) {
            	reviewer = new Reviewer();
            	reviewer.setIdUser(resultset.getInt("idUser"));
            	reviewer.setPassword(resultset.getString("password"));
            	reviewer.setNameUser(resultset.getString("nameUser"));
            	reviewer.setEmail(resultset.getString("email"));
            	reviewer.setFiliation(resultset.getString("filiation"));
            	reviewer.setKnowledgeArea(resultset.getString("knowledgeArea"));
			 	reviewer.setTypeReviewer(TypeReviewer.valueOf(resultset.getString("typeReviewer")));

			 	reviewer.setTypeUser(TypeUser.valueOf(resultset.getString("typeUser")));
				reviewer.setContato(resultset.getString("contato"));
			
        		list.add(reviewer);
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
	public void update(Reviewer reviewer) throws ReviewerNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();
            statement.executeUpdate("UPDATE reviewer SET knowledgeArea = '"+ reviewer.getKnowledgeArea() +
					"', typeReviewer = '"+ reviewer.getTypeReviewer() + 
            	"' WHERE idUser = '"+ reviewer.getIdUser()+"'");
            
            	statement.executeUpdate("UPDATE User SET password = '"+ 
            		reviewer.getPassword() +
                    "', nameUser = '"+ reviewer.getNameUser() +
                    "',email = '"+ reviewer.getEmail() +
                    "', filiation = '" + reviewer.getFiliation() +
					"', typeReviewer = '"+ user.getTypeUser() + 
						"', contato = '"+ user.getContato() + 
                 	"' WHERE idUser = '"+ reviewer.getIdUser()+"'");
            
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
            ResultSet resultset = statement.executeQuery("SELECT * FROM reviewer WHERE idUser = '" + idUser + "'");
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
	public Reviewer getReviewerByknowledgeArea(String keyword) throws ReviewerNotFoundException,
			RepositoryException {
		Reviewer reviewer = null;
		reviewer = new Reviewer();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM reviewer WHERE knowledgeArea LIKE '%"+keyword+"%'");
            while (resultset.next()) {
            	reviewer.setIdUser(resultset.getInt("idUser"));
            	reviewer.setKnowledgeArea(resultset.getString("knowledgeArea"));
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
		return reviewer;
	}

}
//#endif