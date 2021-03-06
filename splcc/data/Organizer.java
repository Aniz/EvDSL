//#if ${Organizer} == "T"
package {{systemName|lower}}.ev.data;

public class Organizer extends User{

	private int idActivity;
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
	public Type{{data.option.entity}} getType{{data.option.entity}}(){
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

	public int getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}

	public String toString(){
		return super.toString()
	{% if data.option.properties|length > 0 %}
		//generated By DSL
		{% for property in data.option.properties %}
		+ "{{property.name|capitalize}}:" + {{property.name}}
		{% endfor %}
	{% endif %}
	{% if data.option.categories|length > 0 %}
		+ "Type{{data.option.entity}}:" + type{{data.option.entity}} 		
	{% endif %}
	;
	}

}
//#endif