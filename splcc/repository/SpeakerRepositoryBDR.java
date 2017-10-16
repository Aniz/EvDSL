//#if ${Speaker} == "T"
package {{systemName|lower}}.ev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import {{systemName|lower}}.ev.data.Speaker;
{% if extraData.option.categories|length > 0 %}
import {{systemName|lower}}.ev.data.{{extraData.option.entity}}.Type{{extraData.option.entity}};
{% endif%}
{% if data.option.categories|length > 0 %}
import {{systemName|lower}}.ev.data.{{data.option.entity}}.Type{{data.option.entity}};
{% endif %}
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.SpeakerNotFoundException;
import {{systemName|lower}}.ev.util.PersistenceMechanismException;
import {{systemName|lower}}.ev.util.PersistenceMechanismRDBMS;

public class SpeakerRepositoryBDR implements SpeakerRepository{
	
	private static SpeakerRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	
	public SpeakerRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static SpeakerRepositoryBDR getInstance(){
		if(instance == null){
			instance = new SpeakerRepositoryBDR();
		}
		return instance;
	}

	@Override
	public void insert(Speaker speaker) throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO User (idUser,password,nameUser,email,filiation{% if data.option.categories|length > 0 %},type{{extraData.option.entity}}{% endif %}{% for property in extraData.option.properties %},{{property.name}}{% endfor %}) Values('"+speaker.getIdUser()
			    +"','" + speaker.getPassword()
				+"', '"+speaker.getNameUser()
				+"', '"+speaker.getEmail() 
				+"', '"+speaker.getFiliation()
			{% if extraData.option.categories|length > 0 %}
				+"', '"+{{data.option.entity|lower}}.getType{{extraData.option.entity}}()
			{% endif %}
			{% if extraData.option.properties|length > 0 %}{% for property in extraData.option.properties %}
				+"', '"+{{data.option.entity|lower}}.get{{property.name|capitalize}}()   
			{% endfor %}{% endif %}
				+"')");

			statement.executeUpdate("INSERT INTO speaker (idUser,biography{% if data.option.categories|length > 0 %},type{{data.option.entity}}{% endif %}{% for property in data.option.properties %},{{property.name}}{% endfor %}) Values('"+speaker.getIdUser()
				+"','" + speaker.getBiography()
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
	public void remove(int idUser) throws SpeakerNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM speaker WHERE idUser = '"+ idUser+"'");
            if (i == 0) {
            	throw new SpeakerNotFoundException(idUser);
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
	public Speaker search(int idUser) throws SpeakerNotFoundException,
			RepositoryException {
		Speaker speaker = null;
		speaker = new Speaker();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from speaker as s inner join user on user.idUser = s.idUser and s.idUser = " + idUser);
            if (resultset.next()) {   
            	speaker.setIdUser(resultset.getInt("idUser"));
            	speaker.setBiography(resultset.getString("biography"));
            	speaker.setPassword(resultset.getString("password"));
            	speaker.setNameUser(resultset.getString("nameUser"));       	
            	speaker.setEmail(resultset.getString("email"));
            	speaker.setFiliation(resultset.getString("filiation"));

			{% if data.option.categories|length > 0 %}
				{{data.option.entity|lower}}.setType{{data.option.entity}}(Type{{data.option.entity}}.valueOf(resultset.getString("type{{data.option.entity}}")));
        	{% endif %}
           	{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.get{% if property.type|javatype == 'int' %}Int{% else %}{{property.type|javatype}}{% endif %}("{{property.name}}"));
			{% endfor %}{% endif %}			
        
        	{% if extraData.option.categories|length > 0 %}
				{{data.option.entity|lower}}.setType{{extraData.option.entity}}(Type{{extraData.option.entity}}.valueOf(resultset.getString("type{{extraData.option.entity}}")));
        	{% endif %}
           	{% if extraData.option.properties|length > 0 %}{% for property in extraData.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.get{% if property.type|javatype == 'int' %}Int{% else %}{{property.type|javatype}}{% endif %}("{{property.name}}"));
			{% endfor %}{% endif %}			

            } else {
            	throw new SpeakerNotFoundException(idUser);
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
		return speaker;
	}

	@Override
	public List<Speaker> getSpeakerList() throws RepositoryException {
		Speaker speaker = null;
		ArrayList<Speaker> list = new ArrayList<Speaker>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select speaker.idUser, password, nameUser, typeUser, email, filiation, biography from speaker inner join User on speaker.idUser = user.idUser;");
          
            while (resultset.next()) {
            	speaker = new Speaker();
            	speaker.setIdUser(resultset.getInt("idUser"));
            	speaker.setPassword(resultset.getString("password"));
            	speaker.setNameUser(resultset.getString("nameUser"));
            	speaker.setEmail(resultset.getString("email"));
            	speaker.setFiliation(resultset.getString("filiation"));
            	speaker.setBiography(resultset.getString("biography"));
    		{% if data.option.categories|length > 0 %}
				{{data.option.entity|lower}}.setType{{data.option.entity}}(Type{{data.option.entity}}.valueOf(resultset.getString("type{{data.option.entity}}")));
        	{% endif %}
           	{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.get{% if property.type|javatype == 'int' %}Int{% else %}{{property.type|javatype}}{% endif %}("{{property.name}}"));
			{% endfor %}{% endif %}			
        	
        	{% if extraData.option.categories|length > 0 %}
				{{data.option.entity|lower}}.setType{{extraData.option.entity}}(Type{{extraData.option.entity}}.valueOf(resultset.getString("type{{extraData.option.entity}}")));
        	{% endif %}
           	{% if extraData.option.properties|length > 0 %}{% for property in extraData.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.get{% if property.type|javatype == 'int' %}Int{% else %}{{property.type|javatype}}{% endif %}("{{property.name}}"));
			{% endfor %}{% endif %}			
        
				list.add(speaker);
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
	public void update(Speaker speaker) throws SpeakerNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();
    	    statement.executeUpdate("UPDATE User SET password = '"+ 
	    		speaker.getPassword() +
                "', nameUser = '"+ speaker.getNameUser() +
                "',email = '"+ speaker.getEmail() +
                "', filiation = '" + speaker.getFiliation() +

			{% if extraData.option.categories|length > 0 %}
				"', type{{extraData.option.entity}} = '"+ {{data.option.entity|lower}}.getType{{extraData.option.entity}}() + 
			{% endif %}
			{% if extraData.option.properties|length > 0 %}{% for property in extraData.option.properties %}
				"', {{property.name}} = '"+ {{data.option.entity|lower}}.get{{property.name|capitalize}}() + 
			{% endfor %}{% endif %}
                "' WHERE idUser = '"+ speaker.getIdUser()+"'");

            statement.executeUpdate("UPDATE speaker SET biography = '"+ 
            	speaker.getBiography() +
            	{% if data.option.categories|length > 0 %}
					"', type{{data.option.entity}} = '"+ {{data.option.entity|lower}}.getType{{data.option.entity}}() + 
				{% endif %}
				{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
					"', {{property.name}} = '"+ {{data.option.entity|lower}}.get{{property.name|capitalize}}() + 
				{% endfor %}{% endif %}
            	"' WHERE idUser = '"+ speaker.getIdUser()+"'");   
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
            ResultSet resultset = statement.executeQuery("SELECT * FROM speaker WHERE idUser = '" + idUser + "'");
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