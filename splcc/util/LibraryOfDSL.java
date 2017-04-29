package {{systemName|lower}}.ev.util;

{% for key,value in data.items() %}
import {{systemName|lower}}.ev.data.{{key}};
import {{systemName|lower}}.ev.business.{{key}}Control;
import {{systemName|lower}}.ev.exception.{{key}}AlreadyInsertedException;
import {{systemName|lower}}.ev.exception.{{key}}NotFoundException;
import {{systemName|lower}}.ev.repository.{{key}}Repository;
import {{systemName|lower}}.ev.repository.{{key}}RepositoryBDR;
{% endfor %}

public class LibraryOfDSL {

	public Boolean returnValue(String value){
		if(value){
			return true;
		}
		else {
			return false;
		}	
	}	
}
