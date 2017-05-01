
package riseevents.ev.business;

import java.util.List;

import org.apache.commons.mail.EmailException;

import riseevents.ev.data.User;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.UserAlreadyInsertedException;
import riseevents.ev.exception.UserNotFoundException;
import riseevents.ev.repository.UserRepository;
import riseevents.ev.util.Email;

public class UserControl {

	public UserControl(UserRepository repository){
		this.userList = repository;
	}

	//usar instance of para saber qual o objeto dependendo disso direciona para o respectivo repositorio.
	public void insert(User user) throws UserAlreadyInsertedException, RepositoryException{
		if (user != null) {
			if (!userList.isThere(user.getIdUser())) 
				userList.insert(user);
			else
				throw new UserAlreadyInsertedException(user.getIdUser());
		} else {
            throw new IllegalArgumentException();
        }
	}
	public void remove(int idUser) throws UserAlreadyInsertedException, RepositoryException, UserNotFoundException{
		if (userList.isThere(idUser)) 
			userList.remove(idUser);
		else
			throw new UserNotFoundException(idUser);
	}
	public void update(User user) throws UserAlreadyInsertedException, RepositoryException, UserNotFoundException{
		if (userList.isThere(user.getIdUser())) 
			userList.update(user);
		else
			throw new UserNotFoundException(user.getIdUser());
	}
	public User search(int idUser) throws UserAlreadyInsertedException, RepositoryException, UserNotFoundException{
		return userList.search(idUser);
	}

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