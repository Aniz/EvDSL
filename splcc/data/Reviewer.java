//#if ${Reviewer} == "T"
package rise.splcc.data;

public class Reviewer extends User{

	private String knowledgeArea;
{% if data.option.properties|length > 0 %}
	//generated By DSL
{% for property in data.option.properties %}
	private {{property.type|javatype}} {{property.name}};
{% endfor %}
	//
{% endif %}
{% if data.option.categories|length > 0 %}
	//generated By DSL
	private Type{{data.option.entity}} type{{data.option.entity}};
	public enum Type{{data.option.entity}}{
	{% for category in data.option.categories %}
		{% if loop.last %}
			{{category.name}}
		{% else %}
			{{category.name}},
		{% endif %}
	{% endfor %}	
	}
	//

	//generated By DSL
	public getType{{data.option.entity}}(){
		return this.type{{data.option.entity}};
	}
	public void setType{{data.option.entity|capitalize}}(Type{{data.option.entity}} new_value){
		this.type{{data.option.entity}} = new_value;
	}
	//
{% endif %}	
{% if data.option.properties|length > 0 %}
	//generated By DSL
{% for property in data.option.properties %}
  	public {{property.type|javatype}} get{{property.name|capitalize}}(){
    	return this.{{property.name}};
  	}
	public void set{{property.name|capitalize}}({{property.type|javatype}} new_value){
    	this.{{property.name}} = new_value;
  	}
{% endfor %}
	//
{% endif %}

	public String getKnowledgeArea() {
		return knowledgeArea;
	}

	public void setKnowledgeArea(String knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
	}

	@Override
	public boolean equals(Object obj) { 
		if (obj == this) { 
			return true; 
		} 
		if (obj == null || obj.getClass() != this.getClass()) { 
			return false; 
		} 
		
		Reviewer reviewer = (Reviewer) obj; 
		
		return (this.getIdUser() == ((Reviewer)obj).getIdUser() && 
				   this.getPassword().equals(((Reviewer)obj).getPassword()) && 
				   this.getNameUser().equals(((Reviewer)obj).getNameUser()) &&
				   this.getTypeUser() == ((Reviewer)obj).getTypeUser() &&
				   this.getEmail().equals(((Reviewer)obj).getEmail())&&
				   this.getKnowledgeArea().equals(((Reviewer)obj).getKnowledgeArea()));
	}
	
	public String toString(){
		//return "User Id:"+ getIdUser() + "\nName:" + getNameUser() + "\nType:" + getTypeUser().toString() + "\nEmail:" + getEmail()   + "\nKnowledgeArea:" + knowledgeArea + "\nFiliation:" + getFiliation() ;
		//return "\nKnowledge Area:" + knowledgeArea;
		return super.toString()  + "\nKnowledgeArea:" + knowledgeArea;
	}

}
//#endif