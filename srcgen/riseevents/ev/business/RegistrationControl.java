package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.Registration;
import riseevents.ev.exception.RegistrationAlreadyInsertedException;
import riseevents.ev.exception.RegistrationNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.RegistrationRepository;


public class RegistrationControl {
	
    private RegistrationRepository registrationList;
	
	public RegistrationControl(RegistrationRepository repository){
		this.registrationList = repository;
	}

	public List<Registration> getRegistrationList() throws RepositoryException {
		return registrationList.getRegistrationList();  
	}
	
	public Registration search(int idRegistration) throws RegistrationAlreadyInsertedException, RepositoryException, RegistrationNotFoundException{
		return registrationList.search(idRegistration);
	}
	
	public void insert(Registration registration) throws RegistrationAlreadyInsertedException, RepositoryException{
		if (registration != null) {
            if (!registrationList.isThere(registration)) {
                registrationList.insert(registration);
            } else {
                throw new RegistrationAlreadyInsertedException(registration.getIdRegistration());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}

	public void remove(Registration registration) throws RegistrationAlreadyInsertedException, RepositoryException, RegistrationNotFoundException{
		registrationList.remove(registration);
	}
	
	public void update(Registration registration) throws RegistrationAlreadyInsertedException, RepositoryException, RegistrationNotFoundException{
		registrationList.update(registration);
	}

	public boolean isThere(Registration registration) throws RepositoryException {
		return registrationList.isThere(registration);
	}
	
	public int getRegistrationLastId() throws RepositoryException{
		return registrationList.getRegistrationLastId();
	}
	
	public void removeValue(float value, int idRegistration) throws RepositoryException{
		registrationList.removeValue(value, idRegistration);
	}
	
	public void addValue(float value, int idRegistration) throws RepositoryException{
		registrationList.addValue(value, idRegistration);
	}
	
	public int search(int idUser, int idEvent) throws RegistrationNotFoundException, RepositoryException{
		return registrationList.search(idUser, idEvent);
	}

}