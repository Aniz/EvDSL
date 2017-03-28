//#if ${Organizer} == "T"
package rise.splcc.data;

public class Organizer extends User{

	private int idActivity;
	private TypeOrganizer typeOrganizer;
	public enum TypeOrganizer{
	{% for category in data.option.categories %}
		//generated By DSL
		{% if loop.last %}
			{{category.name}}
		{% else %}
			{{category.name}},
		{% endif %}
		//
	{% endfor %}	
	}

	{% for property in data.option.properties %}
	//generated By DSL
	private {{property.type|javatype}} {{property.name}};
	//
	{% endfor %}
	
	public TypeOrganizer getTypeOrganizer() {
		return typeOrganizer;
	}

	public void setTypeOrganizer(TypeOrganizer typeOrganizer) {
		this.typeOrganizer = typeOrganizer;
	}

	public int getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}

	public String toString(){
		//return "\nIdUser:" + getIdUser() + "\nName:" + getNameUser() + "\nEmail:" + getEmail() + "\nType:" + typeOrganizer.name() + "\nFiliation:" + getFiliation();
		return super.toString() + "\nType:" + typeOrganizer.name();
	}

	{% for property in data.option.properties %}
	//generated By DSL
  	public {{property.type|javatype}} get{{property.name|capitalize}}(){
    	return this.{{property.name}};
  	}
	public void set{{property.name|capitalize}}({{property.type|javatype}} new_value){
    	this.{{property.name}} = new_value;
  	}
  	//
  	{% endfor %}
	
}
//#endif