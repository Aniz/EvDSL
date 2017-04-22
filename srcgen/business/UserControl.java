
package rise.splcc.business;

import java.util.List;

import org.apache.commons.mail.EmailException;

import rise.splcc.data.User;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;
//#if ${Organizer} == "T"
import rise.splcc.repository.OrganizerRepository;
//#endif
//#if ${Reviewer} == "T"
import rise.splcc.repository.ReviewerRepository;
//#endif
//#if ${Speaker} == "T"
import rise.splcc.repository.SpeakerRepository;
//#endif
import rise.splcc.repository.UserRepository;
import rise.splcc.util.Email;

public class UserControl {

	private UserRepository users;
	//#if ${Speaker} == "T"
	private SpeakerRepository speakers;
	//#endif
	//#if ${Organizer} == "T"
	private OrganizerRepository organizers;
	//#endif
	//#if ${Reviewer} == "T"
	private ReviewerRepository reviewers;
	//#endif
	
	public UserControl(UserRepository repository){
		this.users = repository;
	}

	//usar instance of para saber qual o objeto dependendo disso direciona para o respectivo repositorio.




	
	public List<User> getUsers() throws RepositoryException { 
		return users.getUsers();  
	}
	
	public int getUserIdByName(String userName) throws RepositoryException{
		return users.getUserIdByName(userName);
	}

}