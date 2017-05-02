package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.Registration;
import {{systemName|lower}}.ev.exception.RegistrationNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;

public interface RegistrationRepository {

	public List<Registration> getRegistrationList() throws RepositoryException;

	public Registration search(int idRegistration) throws RegistrationNotFoundException, RepositoryException;
	
	public void insert(Registration registration) throws RepositoryException;

	public void remove(Registration registration) throws RegistrationNotFoundException, RepositoryException;
	
	public void update(Registration registration) throws RegistrationNotFoundException, RepositoryException;

	public boolean isThere(Registration registration) throws RepositoryException;
	
	public int getRegistrationLastId() throws RepositoryException;
	
	public void removeValue(float value, int idRegistration) throws RepositoryException;
	
	public void addValue(float value, int idRegistration) throws RepositoryException;
	
	public int search(int idUser, int idEvent) throws RegistrationNotFoundException, RepositoryException;

}
