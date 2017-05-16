
package {{systemName|lower}}.ev.business;

import java.util.List;

import org.apache.commons.mail.EmailException;

import {{systemName|lower}}.ev.data.User;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.UserAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.UserNotFoundException;
import {{systemName|lower}}.ev.repository.UserRepository;
import {{systemName|lower}}.ev.util.LibraryOfDSL;

public class UserControl {

	private UserRepository userList;
	
	public UserControl(UserRepository repository){
		this.userList = repository;
	}

	//usar instance of para saber qual o objeto dependendo disso direciona para o respectivo repositorio.
	{% if 'Insert' in data.commands %}
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
	{% endif %}
	{% if 'Remove' in data.commands %}
	public void remove(int idUser) throws UserAlreadyInsertedException, RepositoryException, UserNotFoundException{
		if (userList.isThere(idUser)) 
			userList.remove(idUser);
		else
			throw new UserNotFoundException(idUser);
	}
	{% endif %}
	{% if 'Update' in data.commands %}
	public void update(User user) throws UserAlreadyInsertedException, RepositoryException, UserNotFoundException{
		if (userList.isThere(user.getIdUser())) 
			userList.update(user);
		else
			throw new UserNotFoundException(user.getIdUser());
	}
	{% endif %}
	{% if 'Search' in data.commands %}
	public User search(int idUser) throws UserAlreadyInsertedException, RepositoryException, UserNotFoundException{
		return userList.search(idUser);
	}
	{% endif %}

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
