
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
	{% if 'Insert' in data.functions %}
	public void insert(User user) throws UserAlreadyInsertedException, RepositoryException{
		if (user != null) {
			if (!users.isThere(user.getIdUser())) 
				users.insert(user);
			else
				throw new UserAlreadyInsertedException(user.getIdUser());
		} else {
            throw new IllegalArgumentException();
        }
	}
	{% endif %}

	{% if 'Remove' in data.functions %}
	public void remove(int idUser) throws UserAlreadyInsertedException, RepositoryException, UserNotFoundException{
		if (users.isThere(idUser)) 
			users.remove(idUser);
		else
			throw new UserNotFoundException(idUser);
	}
	{% endif %}

	{% if 'Update' in data.functions %}
	public void update(User user) throws UserAlreadyInsertedException, RepositoryException, UserNotFoundException{
		if (users.isThere(user.getIdUser())) 
			users.update(user);
		else
			throw new UserNotFoundException(user.getIdUser());
	}
	{% endif %}

	{% if 'Search' in data.functions %}
	public User search(int idUser) throws UserAlreadyInsertedException, RepositoryException, UserNotFoundException{
		return users.search(idUser);
	}
	{% endif %}

	{% if 'isThere' in data.functions %}
	public boolean isThere(int idUser) throws RepositoryException {
		return users.isThere(idUser);
	}
	{% endif %}
	
	public List<User> getUsers() throws RepositoryException { 
		return users.getUsers();  
	}
	
	public int getUserIdByName(String userName) throws RepositoryException{
		return users.getUserIdByName(userName);
	}

	{% if 'Bugs' in data.actions %}
	//#if ${Bugs} == "T"
	public String sendBug(String nome, String assunto, String mensagem, Email email) throws EmailException{
		return email.sendBugtrackEmail(nome, assunto, mensagem);
	}
	{% endif %}

	//#endif
}
