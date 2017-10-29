
package riseevents.ev.business;

import java.util.List;

import org.apache.commons.mail.EmailException;

import riseevents.ev.data.User;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.UserAlreadyInsertedException;
import riseevents.ev.exception.UserNotFoundException;
import riseevents.ev.repository.UserRepository;
import riseevents.ev.util.LibraryOfDSL;

public class UserControl {

	private UserRepository userList;
	
	public UserControl(UserRepository repository){
		this.userList = repository;
	}

	//usar instance of para saber qual o objeto dependendo disso direciona para o respectivo repositorio.

	public boolean isThere(int idUser) throws RepositoryException {
		return userList.isThere(idUser);
	}
	
	public List<User> getUserList() throws RepositoryException { 
		return userList.getUserList();  
	}
	
	public int getUserIdByName(String userName) throws RepositoryException{
		return userList.getUserIdByName(userName);
	}
}