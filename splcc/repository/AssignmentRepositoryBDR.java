//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package {{systemName|lower}}.ev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import {{systemName|lower}}.ev.data.Assignment;
{% if data.option.categories|length > 0 %}
import {{systemName|lower}}.ev.data.{{data.option.entity}}.Type{{data.option.entity}};
{% endif %}

import {{systemName|lower}}.ev.exception.AssignmentNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.util.PersistenceMechanismException;
import {{systemName|lower}}.ev.util.PersistenceMechanismRDBMS;

public class AssignmentRepositoryBDR implements AssignmentRepository {
	
	private static AssignmentRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public AssignmentRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static AssignmentRepositoryBDR getInstance(){
		if(instance == null){
			instance = new AssignmentRepositoryBDR();
		}
		return instance;
	}

	
	@Override
	public void insert(Assignment assignment) throws RepositoryException{
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO assignement (idUser, idReview, date, idSubmission{% if data.option.categories|length > 0 %},type{{data.option.entity}}{% endif %}{% if data.option.properties|length > 0 %}{% for property in data.option.properties %},{{property.name}}{% endfor %}{% endif %}) Values('"
							+assignment.getIdReviwerUser() +"', '"
				            +assignment.getIdReview()+"', '"
				            +assignment.getDate()+"', '"
				            +assignment.getIdReviewSubmission()
			            {% if data.option.categories|length > 0 %}
							+"', '"+{{data.option.entity|lower}}.getType{{data.option.entity}}()
						{% endif %}				
						{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
							+"', '"+{{data.option.entity|lower}}.get{{property.name|capitalize}}()   
						{% endfor %}{% endif %}	        
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
	public void remove(Assignment assignment) throws AssignmentNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM assignement WHERE idUser = '"+ assignment.getIdReviwerUser() +"' AND idReview = '"+ assignment.getIdReview() +"' AND idSubmission = '"+ assignment.getIdReviewSubmission() +"'  ");
            if (i == 0) {
            	throw new AssignmentNotFoundException(assignment.getIdReview());
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
	public Assignment search(Assignment assignment1) throws AssignmentNotFoundException,
			RepositoryException {
		Assignment assignment = null;
		assignment = new Assignment();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from assignement WHERE idUser = '"+ assignment1.getIdReviwerUser() +"' AND idReview = '"+ assignment1.getIdReview() +"' AND idSubmission = '"+ assignment1.getIdReviewSubmission() +"' " );
            if (resultset.next()) {   
            	assignment.setIdReview(resultset.getInt("idReview"));
            	assignment.setIdReviewSubmission(resultset.getInt("idSubmission"));
            	assignment.setIdReviwerUser(resultset.getInt("idUser"));
            	assignment.setDate(resultset.getString("date"));
            {% if data.option.categories|length > 0 %}
            	{{data.option.entity|lower}}.setType{{data.option.entity}}(Type{{data.option.entity}}.valueOf(resultset.getString("type{{data.option.entity}}")));
            {% endif %}
            {% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.get{% if property.type|javatype == 'int' %}Int{% else %}{{property.type|javatype}}{% endif %}("{{property.name}}"));
			{% endfor %}{% endif %}
            	
            } else {
            	throw new AssignmentNotFoundException(assignment.getIdReview());
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
		return assignment;
	}

	@Override
	public List<Assignment> getAssignments() throws RepositoryException {
		Assignment assignment=null;
		ArrayList<Assignment> list = new ArrayList<Assignment>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from assignement");
            while (resultset.next()) {
            	assignment = new Assignment();
            	assignment.setIdReview(resultset.getInt("idReview"));
            	assignment.setIdReviewSubmission(resultset.getInt("idSubmission"));
            	assignment.setIdReviwerUser(resultset.getInt("idUser"));
            	assignment.setDate(resultset.getString("date"));
		    {% if data.option.categories|length > 0 %}
            	{{data.option.entity|lower}}.setType{{data.option.entity}}(Type{{data.option.entity}}.valueOf(resultset.getString("type{{data.option.entity}}")));
            {% endif %}     
    		{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.get{% if property.type|javatype == 'int' %}Int{% else %}{{property.type|javatype}}{% endif %}("{{property.name}}"));
			{% endfor %}{% endif %}			
				list.add(assignment);
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
	public void update(Assignment assignment) throws AssignmentNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

    	    statement.executeUpdate("UPDATE assignement SET idReview = '"+ assignment.getIdReview() +
    	    										 "', idSubmission = '" + assignment.getIdReviewSubmission()+
    	    		                                 "', idUser = '" + assignment.getIdReviwerUser() +
    	    		                                 "', date = '"+ assignment.getDate() + 
		                                 	    {% if data.option.categories|length > 0 %}
									                 "', type{{data.option.entity}} = '"+ {{data.option.entity|lower}}.getType{{data.option.entity}}() +						
									            {% endif %}    
    		                                 	{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
											         "', {{property.name}} = '"+ {{data.option.entity|lower}}.get{{property.name|capitalize}}() +
												{% endfor %}{% endif %}
    	    		                                 "' WHERE idUser = '"+ assignment.getIdReviwerUser() +"' AND idReview = '"+ assignment.getIdReview() +"' AND idSubmission = '"+ assignment.getIdReviewSubmission() +"' ");

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
	public boolean isThere(Assignment assignment) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM assignement WHERE idUser = '"+ assignment.getIdReviwerUser() +"' AND idReview = '"+ assignment.getIdReview() +"' AND idSubmission = '"+ assignment.getIdReviewSubmission() +"' ");
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