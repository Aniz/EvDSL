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

	public Boolean returnValue(Boolean value){
		if(value){
			return true;
		}
		else {
			return false;
		}	
	}	
	public Boolean automaticInterestConflict(Author authorSubmission,  User usersub, User user){
		
		String authorFiliation = null;
		String reviewerFiliation = null;
		String userFiliation = null;
				
		authorFiliation = authorSubmission.getFiliation();
		reviewerFiliation = user.getFiliation();
		userFiliation = user.getFiliation();
		
		if(authorFiliation.equals(reviewerFiliation)){
			return true;
		}
		if(usersub.equals(userFiliation)){
			return true;
		}
		else {
			return false;
		}
		
	}
}
