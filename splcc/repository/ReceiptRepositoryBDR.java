//#if ${Receipt} == "T"
package {{systemName|lower}}.ev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import {{systemName|lower}}.ev.data.Receipt;
import {{systemName|lower}}.ev.exception.ReceiptNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.util.PersistenceMechanismException;
import {{systemName|lower}}.ev.util.PersistenceMechanismRDBMS;

public class ReceiptRepositoryBDR implements ReceiptRepository {
	
	private static ReceiptRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public ReceiptRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static ReceiptRepositoryBDR getInstance(){
		if(instance == null){
			instance = new ReceiptRepositoryBDR();
		}
		return instance;
	}
	
	
	@Override
	public void insert({{data.option.entity}} {{data.option.entity|lower}}) throws RepositoryException {
		try {
		Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO {{data.option.entity}} (id{{data.option.entity}}{% if data.option.categories|length > 0 %},type{{data.option.entity}}{% endif %}{% if data.option.properties|length > 0 %}{% for property in data.option.properties %},{{property.name}}{% endfor %}{% endif %}) Values('"
				+{{data.option.entity|lower}}.getId{{data.option.entity}}()
				+ "', '"+{{data.option.entity|lower}}.getIdRegistration()
				+ "', '"+{{data.option.entity|lower}}.getBarcode()
				+ "', '"+{{data.option.entity|lower}}.getDate()
				+ "', '"+{{data.option.entity|lower}}.getValue() 
				+ "', '"+{{data.option.entity|lower}}.getStatus() 
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
	public List<{{data.option.entity}}> get{{data.option.entity|lower}}List() throws RepositoryException {
		{{data.option.entity}} {{data.option.entity|lower}} = null;
		ArrayList<{{{data.option.entity}}}> list = new ArrayList<{{data.option.entity}}>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from {{data.option.entity}}");
            while (resultset.next()) {
	            {{data.option.entity|lower}} = new {{data.option.entity}}();
		        {{data.option.entity|lower}}.setId{{data.option.entity}}(resultset.getInt("id{{data.option.entity}}"));         
            {% if data.option.categories|length > 0 %}
            	{{data.option.entity|lower}}.setType{{data.option.entity}}(Type{{data.option.entity}}.valueOf(resultset.getString("type{{data.option.entity}}")));
            {% endif %}
            {% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.get{% if property.type|javatype == 'int' %}Int{% else %}{{property.type|javatype}}{% endif %}("{{property.name}}"));
			{% endfor %}{% endif %}
				list.add({{data.option.entity|lower}});
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
	public boolean isThere(int id{{data.option.entity}}) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM {{data.option.entity}} WHERE id{{data.option.entity}} = '" + idEntity + "'");
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
	public int get{{data.option.entity}}LastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EeventDB' AND TABLE_NAME= '{{data.option.entity}}'");
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
	public void remove(int idReceipt) throws ReceiptNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM {{data.option.entity}} WHERE id{{data.option.entity}} = '"+ id{{data.option.entity}}+"'");
            if (i == 0) {
            	throw new ReceiptNotFoundException(idReceipt);
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
	public void update(Receipt review) throws ReceiptNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

            		statement.executeUpdate("UPDATE {{data.option.entity}} SET 
            									{% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
											        {% if loop.first %}
											         {{property.name}} = '"+ {{data.option.entity|lower}}.get{{property.name|capitalize}}() +
													{% else %}
												     "', {{property.name}} = '"+ {{data.option.entity|lower}}.get{{property.name|capitalize}}() +
													{% endif %}
												{% endfor %}{% endif %}
            									{% if data.option.categories|length > 0 %}
									                 type{{data.option.entity}} = "'+ {{data.option.entity|lower}}.getType{{data.option.entity}}() +						
									            {% endif %}    
    	    		                                 ' WHERE id{{data.option.entity}} = '"+ review.getId{{data.option.entity}}()+"'");

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
	public Receipt search(int id{{data.option.entity}}) throws {{data.option.entity}}NotFoundException,
			RepositoryException {
		{{data.option.entity}} {{data.option.entity|lower}} = null;
		{{data.option.entity|lower}} = new {{data.option.entity}}();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from {{data.option.entity}} WHERE id{{data.option.entity}} =" + id{{data.option.entity}});
            if (resultset.next()) {   
                {{data.option.entity|lower}}.setId{{data.option.entity}}(resultset.getInt("id{{data.option.entity}}"));         
            {% if data.option.categories|length > 0 %}
            	{{data.option.entity|lower}}.setType{{data.option.entity}}(Type{{data.option.entity}}.valueOf(resultset.getString("type{{data.option.entity}}")));
            {% endif %}
            {% if data.option.properties|length > 0 %}{% for property in data.option.properties %}
				{{data.option.entity|lower}}.set{{property.name|capitalize}}(resultset.get{% if property.type|javatype == 'int' %}Int{% else %}{{property.type|javatype}}{% endif %}("{{property.name}}"));
			{% endfor %}{% endif %}
		    } else {
            	throw new ReceiptNotFoundException(idReceipt);
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
		return {{data.option.entity|lower}};
	}


}
//#endif