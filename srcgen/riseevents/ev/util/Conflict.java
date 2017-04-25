//#if ${ConflictofinterestAutomatic} == "T"
package riseevents.ev.util;

import riseevents.ev.data.Author;
import riseevents.ev.data.User;

public class Conflict {

	//usuario que a funcao recebe vem do reviewer(iduser)
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
//#endif
