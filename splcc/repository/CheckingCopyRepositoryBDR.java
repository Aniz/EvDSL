//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package {{systemName|lower}}.ev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import {{systemName|lower}}.ev.data.CheckingCopy;
{% if data.option.categories|length > 0 %}
import {{systemName|lower}}.ev.data.{{data.option.entity}}.Type{{data.option.entity}};
{% endif %}
import {{systemName|lower}}.ev.exception.CheckingCopyNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.util.PersistenceMechanismException;
import {{systemName|lower}}.ev.util.PersistenceMechanismRDBMS;

public class CheckingCopyRepositoryBDR implements CheckingCopyRepository {
	
	private static CheckingCopyRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public CheckingCopyRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static CheckingCopyRepositoryBDR getInstance(){
		if(instance == null){
			instance = new CheckingCopyRepositoryBDR();
		}
		return instance;
	}
	
	@Override
	public void insert(CheckingCopy checkingCopy) throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO CheckingCopy (idRegistration, idUser, dateOfIssue{% if data.option.properties|length > 0 %}{% for property in data.option.properties %},{{property.name}}{% endfor %}{% endif %}{% if data.option.categories|length > 0 %},type{{data.option.entity}}{% endif %}) Values('"+checkingCopy.getIdRegistration()+"', '"
					+checkingCopy.getIdUser()
					+"', '"+checkingCopy.getDateOfIssue()
				{% if data.option.categories|length > 0 %}
					+"', '"+checkingCopy.getType{{data.option.entity}}()
				{% endif %}
				{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
					+"', '"+checkingCopy.get{{property.name|capitalize}}()   
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
	public List<CheckingCopy> getCheckingCopyList() throws RepositoryException {
		CheckingCopy checkingCopy = null;
		ArrayList<CheckingCopy> list = new ArrayList<CheckingCopy>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from checkingCopy");
            while (resultset.next()) {
                checkingCopy = new CheckingCopy();
                checkingCopy.setIdCheckingCopy(resultset.getInt("idCheckingCopy"));
                checkingCopy.setIdRegistration(resultset.getInt("idRegistration"));
                checkingCopy.setIdUser(resultset.getInt("idUser"));
                checkingCopy.setDateOfIssue(resultset.getString("dateOfIssue"));
        	{% if data.option.categories|length > 0 %}
				checkingCopy.setType{{data.option.entity}}(Type{{data.option.entity}}.valueOf(resultset.getString("type{{data.option.entity}}")));
        	{% endif %}
        	{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				checkingCopy.set{{property.name|capitalize}}(resultset.get{% if property.type|javatype == 'int' %}Int{% else %}{{property.type|javatype}}{% endif %}("{{property.name}}"));
			{% endfor %}{% endif %}			
				list.add(checkingCopy);
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
	public boolean isThere(int idCheckingCopy) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM CheckingCopy WHERE idCheckingCopy = '" + idCheckingCopy + "'");
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
	public int getCheckingCopyLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EeventDB' AND TABLE_NAME= 'CheckingCopy'");
            if(resultset.first()){
            resultset.first();
            answer = resultset.getInt("proximo_valor");
			resultset.close();
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
        return answer;
	}
	
	@Override
	public void remove(int idCheckingCopy) throws CheckingCopyNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM CheckingCopy WHERE idCheckingCopy = '"+ idCheckingCopy+"'");
            if (i == 0) {
            	throw new CheckingCopyNotFoundException(idCheckingCopy);
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
	public void update(CheckingCopy checkingCopy) throws CheckingCopyNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();
    		statement.executeUpdate("UPDATE CheckingCopy SET idRegistration = '"+checkingCopy.getIdRegistration()+"', idUser = '"+checkingCopy.getIdUser()+
    					"', dateOfIssue = '"+ checkingCopy.getDateOfIssue() +
    				{% if data.option.categories|length > 0 %}
						"', type{{data.option.entity}} = '"+ checkingCopy.getType{{data.option.entity}}() +
					{% endif %}
					{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				         "', {{property.name}} = '"+ checkingCopy.get{{property.name|capitalize}}() +
					{% endfor %}{% endif %}     
    					"' WHERE idCheckingCopy = '"+ checkingCopy.getIdCheckingCopy()+"'");
        	
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
	public CheckingCopy search(int idCheckingCopy) throws CheckingCopyNotFoundException,
			RepositoryException {
		CheckingCopy checkingCopy = null;
		checkingCopy = new CheckingCopy();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from checkingCopy WHERE idCheckingCopy =" + idCheckingCopy);
            if (resultset.next()) {   
            	checkingCopy.setIdCheckingCopy(resultset.getInt("idCheckingCopy"));
            	checkingCopy.setIdRegistration(resultset.getInt("idRegistration"));
            	checkingCopy.setIdUser(resultset.getInt("idUser"));
            	checkingCopy.setDateOfIssue(resultset.getString("dateOfIssue"));
            {% if data.option.categories|length > 0 %}
				checkingCopy.setType{{data.option.entity}}(Type{{data.option.entity}}.valueOf(resultset.getString("type{{data.option.entity}}")));
        	{% endif %}
           	{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				checkingCopy.set{{property.name|capitalize}}(resultset.get{% if property.type|javatype == 'int' %}Int{% else %}{{property.type|javatype}}{% endif %}("{{property.name}}"));
			{% endfor %}{% endif %}			
            } else {
            	throw new CheckingCopyNotFoundException(idCheckingCopy);
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
		return checkingCopy;
	}
}
//#endif