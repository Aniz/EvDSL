package evco.ev.repository;

import java.util.List;

import evco.ev.data.Registration;
import evco.ev.exception.RegistrationNotFoundException;
import evco.ev.exception.RepositoryException;

public interface RegistrationRepository {

	public List<Registration> getRegistrationList() throws RepositoryException;

	public Registration search(int idRegistration) throws RegistrationNotFoundException, RepositoryException;
	
	public void insert(Registration registration) throws RepositoryException;

	public void remove(int idRegistration) throws RegistrationNotFoundException, RepositoryException;
	
	public void update(Registration registration) throws RegistrationNotFoundException, RepositoryException;

	public boolean isThere(Registration registration) throws RepositoryException;
	
	public int getRegistrationLastId() throws RepositoryException;
	
	public void removeValue(float value, int idRegistration) throws RepositoryException;
	
	public void addValue(float value, int idRegistration) throws RepositoryException;
	
	public int search(int idUser, int idEvent) throws RegistrationNotFoundException, RepositoryException;

}