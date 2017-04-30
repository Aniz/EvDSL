
package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.User;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.UserNotFoundException;



public interface UserRepository {
	
	public void insert(User user) throws RepositoryException;
	
	public void remove(int idUser) throws UserNotFoundException, RepositoryException;
	
	public User search(int idUser) throws UserNotFoundException, RepositoryException;
		
	public List<User> getUserList() throws RepositoryException;
	
	public void update(User user) throws UserNotFoundException, RepositoryException;
	
	public boolean isThere(int idUser) throws RepositoryException;
	
	//retirado uma vez o idUser nao é autoincremento
	//public int getUserLastId() throws RepositoryException;
	
	public int getUserIdByName(String userName) throws RepositoryException;
	
}
