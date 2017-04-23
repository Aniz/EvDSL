
package riseevents.ev.business;

import java.util.List;

import org.apache.commons.mail.EmailException;

import riseevents.ev.data.User;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.UserAlreadyInsertedException;
import riseevents.ev.exception.UserNotFoundException;
//#if ${Organizer} == "T"
import riseevents.ev.repository.OrganizerRepository;
//#endif
//#if ${Reviewer} == "T"
import riseevents.ev.repository.ReviewerRepository;
//#endif
//#if ${Speaker} == "T"
import riseevents.ev.repository.SpeakerRepository;
//#endif
import riseevents.ev.repository.UserRepository;
import riseevents.ev.util.Email;

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