//#if ${InsertAuthors} == "T"
package {{systemName|lower}}.ev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import {{systemName|lower}}.ev.data.Author;
{% if data.option.categories|length > 0 %}
import {{systemName|lower}}.ev.data.{{data.option.entity}}.Type{{data.option.entity}};
{% endif %}
import {{systemName|lower}}.ev.exception.AuthorNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.util.PersistenceMechanismException;
import {{systemName|lower}}.ev.util.PersistenceMechanismRDBMS;

public class AuthorRepositoryBDR implements AuthorRepository {
	
	private static AuthorRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public AuthorRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static AuthorRepositoryBDR getInstance(){
		if(instance == null){
			instance = new AuthorRepositoryBDR();
		}
		return instance;
	}

	
	@Override
	public void insert(Author author) throws RepositoryException{
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO author ( nameAuthor, filiation, email{% if data.option.categories|length > 0 %},type{{data.option.entity}}{% endif %}{% if data.option.properties|length > 0 %}{% for property in data.option.properties %},{{property.name}}  {% endfor %}{% endif %}) Values('"+author.getName()+"', '"
				            +author.getEmail()
				            +"', '"+author.getFiliation()
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
	public void remove(int idAuthor) throws AuthorNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM author WHERE idAuthor = '"+ idAuthor+"'");
            if (i == 0) {
            	throw new AuthorNotFoundException(idAuthor);
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
	public Author search(int idAuthor) throws AuthorNotFoundException,
			RepositoryException {
		Author author = null;
		author = new Author();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from author WHERE idAuthor =" + idAuthor);
            if (resultset.next()) {   
            	author.setIdAuthor(resultset.getInt("idAuthor"));
            	author.setName(resultset.getString("nameAuthor"));
            	author.setEmail(resultset.getString("email"));
            	author.setFiliation(resultset.getString("filiation"));
      		{% if data.option.categories|length > 0 %}
				{{data.option.entity|lower}}.setType{{data.option.entity}}(Type{{data.option.entity}}.valueOf(resultset.getString("type{{data.option.entity}}")));
			{% endif %}
			{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.get{% if property.type|javatype == 'int' %}Int{% else %}{{property.type|javatype}}{% endif %}("{{property.name}}"));   
			{% endfor %}{% endif %}					        
				  } else {
            	throw new AuthorNotFoundException(idAuthor);
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
		return author;
	}

	@Override
	public List<Author> getAuthorList() throws RepositoryException {
		Author author=null;
		ArrayList<Author> list = new ArrayList<Author>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from author");
            while (resultset.next()) {
            	author = new Author();
            	author.setIdAuthor(resultset.getInt("idAuthor"));
            	author.setName(resultset.getString("nameAuthor"));
            	author.setEmail(resultset.getString("email"));
            	author.setFiliation(resultset.getString("filiation"));				
			{% if data.option.categories|length > 0 %}
				{{data.option.entity|lower}}.setType{{data.option.entity}}(Type{{data.option.entity}}.valueOf(resultset.getString("type{{data.option.entity}}")));
			{% endif %}
			{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.get{% if property.type|javatype == 'int' %}Int{% else %}{{property.type|javatype}}{% endif %}("{{property.name}}"));   
			{% endfor %}{% endif %}					        
				list.add(author);
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
	public void update(Author author) throws AuthorNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

    	    statement.executeUpdate("UPDATE author SET nameAuthor = '"+ author.getName() +
	                     "', filiation = '" + author.getFiliation() +
	                     "', email = '"+ author.getEmail() +
	               	{% if data.option.categories|length > 0 %}
						"', {{data.option.entity}} = '"+ author.getType{{data.option.entity}}() +
					{% endif %}
					{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
					     "', {{property.name}} = '"+ author.get{{property.name|capitalize}}() +
					{% endfor %}{% endif %}     
				      "' WHERE idAuthor = '"+ author.getIdAuthor()+"'");

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
	public boolean isThere(int idAuthor) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM author WHERE idAuthor = '" + idAuthor + "'");
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
	public int getAuthorLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EEventDB' AND TABLE_NAME= 'author'");
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

}
//#endif