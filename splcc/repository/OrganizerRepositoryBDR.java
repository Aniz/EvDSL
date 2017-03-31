//#if ${Organizer} == "T"
package rise.splcc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import rise.splcc.data.Organizer;
{% if data.option.categories|length > 0 %}
import rise.splcc.data.{{data.option.entity}}.Type{{data.option.entity}};
{% endif %}
import rise.splcc.data.User.TypeUser;
import rise.splcc.exception.OrganizerNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.util.PersistenceMechanismException;
import rise.splcc.util.PersistenceMechanismRDBMS;

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
				+"', '"+organizer.getTypeUser()
				+"', '"+organizer.getEmail() 
				+"', '"+organizer.getFiliation()
			{% if extraData.option.categories|length > 0 %}
				+"', '"+{{data.option.entity|lower}}.getType{{extraData.option.entity}}()
			{% endif %}
			{% if extraData.option.properties|length > 0 %}{% for property in extraData.option.properties %}
				+"', '"+{{extraData.option.entity|lower}}.get{{property.name|capitalize}}()   
			{% endfor %}{% endif %}
				+"')");

			{% if data.option.properties|length > 0 or data.option.categories|length > 0 %}
           	statement.executeUpdate("INSERT INTO organizer Values('"+organizer.getIdUser()
			{% if data.option.categories|length > 0 %}
				+"', '"+{{data.option.entity|lower}}.getType{{data.option.entity}}()
			{% endif %}
			{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				+"', '"+{{data.option.entity|lower}}.get{{property.name|capitalize}}()   
			{% endfor %}{% endif %}
				+"')");
			{% endif %}
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
            	organizer.setTypeOrganizer(TypeOrganizer.valueOf(resultset.getString("typeOrganizer")));
            	organizer.setPassword(resultset.getString("password"));
            	organizer.setNameUser(resultset.getString("nameUser"));       	
            	organizer.setTypeUser(TypeUser.valueOf(resultset.getString("typeUser")));
            	organizer.setEmail(resultset.getString("email"));
            	organizer.setFiliation(resultset.getString("filiation"));
            {% if data.option.categories|length > 0 %}
				{{data.option.entity|lower}}.setType{{data.option.entity}}(resultset.getString("type{{data.option.entity}}"));
        	{% endif %}
           	{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.getString("{{property.name}}"));
			{% endfor %}{% endif %}			
        
        	{% if extraData.option.categories|length > 0 %}
				{{data.option.entity|lower}}.setType{{extraData.option.entity}}(resultset.getString("type{{extraData.option.entity}}"));
        	{% endif %}
           	{% if extraData.option.properties|length > 0 %}{% for property in extraData.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.getString("{{property.name}}"));
			{% endfor %}{% endif %}			

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
	public List<Organizer> getOrganizers() throws RepositoryException {
		Organizer organizer = null;
		ArrayList<Organizer> list = new ArrayList<Organizer>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select Organizer.idUser, password, nameUser, typeUser, email, filiation, typeOrganizer from Organizer inner join User on Organizer.idUser = User.idUser;");
            while (resultset.next()) {
            	organizer = new Organizer();
            	organizer.setIdUser(resultset.getInt("idUser"));
            	organizer.setPassword(resultset.getString("password"));
            	organizer.setNameUser(resultset.getString("nameUser"));
            	organizer.setTypeUser(TypeUser.valueOf(resultset.getString("typeUser")));
            	organizer.setEmail(resultset.getString("email"));
            	organizer.setFiliation(resultset.getString("filiation"));
            	organizer.setTypeOrganizer(TypeOrganizer.valueOf(resultset.getString("typeOrganizer")));
			{% if data.option.categories|length > 0 %}
				{{data.option.entity|lower}}.setType{{data.option.entity}}(Type{{data.option.entity}}(resultset.getString("type{{data.option.entity}}")));
        	{% endif %}
           	{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.getString("{{property.name}}"));
			{% endfor %}{% endif %}			
        
        	{% if extraData.option.categories|length > 0 %}
				{{data.option.entity|lower}}.setType{{extraData.option.entity}}(resultset.getString("type{{extraData.option.entity}}"));
        	{% endif %}
           	{% if extraData.option.properties|length > 0 %}{% for property in extraData.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.getString("{{property.name}}"));
			{% endfor %}{% endif %}			

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

			{% if extraData.option.categories|length > 0 %}
				"', type{{extraData.option.entity}} = '"+ {{extraData.option.entity|lower}}.getType{{extraData.option.entity}}() + 
			{% endif %}
			{% if extraData.option.properties|length > 0 %}{% for property in extraData.option.properties %}
				"', {{property.name}} = '"+ {{data.option.entity|lower}}.get{{property.name|capitalize}}() + 
			{% endfor %}{% endif %}
                "' WHERE idUser = '"+ organizer.getIdUser()+"'");

            {% if data.option.properties|length > 0 or data.option.categories|length > 0 %}
            	statement.executeUpdate("UPDATE organizer SET biography = '"+ 
            	organizer.getBiography() +
            	{% if data.option.categories|length > 0 %}
					"', type{{data.option.entity}} = '"+ {{data.option.entity|lower}}.getType{{data.option.entity}}() + 
				{% endif %}
				{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
					"', {{property.name}} = '"+ {{data.option.entity|lower}}.get{{property.name|capitalize}}() + 
				{% endfor %}{% endif %}
            	"' WHERE idUser = '"+ organizer.getIdUser()+"'");   
			{% endif %}
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