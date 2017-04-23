//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.SubmissionUser;
import riseevents.ev.exception.SubmissionUserAlreadyInsertedException;
import riseevents.ev.exception.SubmissionUserNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.SubmissionUserRepository;

public class SubmissionUserControl {

	private SubmissionUserRepository submissionUsers;
	
	public SubmissionUserControl(SubmissionUserRepository repository){
		this.submissionUsers = repository;
	}
	
	public void insert(SubmissionUser submissionUser) throws SubmissionUserAlreadyInsertedException, RepositoryException{
		if (submissionUser != null) {
            if (submissionUsers.isThere(submissionUser) == false) {
            	submissionUsers.insert(submissionUser);
            } else {
                throw new SubmissionUserAlreadyInsertedException(submissionUser.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(SubmissionUser submissionUser) throws SubmissionUserAlreadyInsertedException, RepositoryException, SubmissionUserNotFoundException{		
		submissionUsers.remove(submissionUser);
	}
	
	public void update(SubmissionUser submissionUser) throws SubmissionUserAlreadyInsertedException, RepositoryException, SubmissionUserNotFoundException{
		submissionUsers.update(submissionUser);
	}
	
	public SubmissionUser search(SubmissionUser submissionUser) throws SubmissionUserAlreadyInsertedException, RepositoryException, SubmissionUserNotFoundException{
		return submissionUsers.search(submissionUser);
	}

	public boolean isThere(SubmissionUser submissionUser) throws RepositoryException {
		return submissionUsers.isThere(submissionUser);
	}

	public List<SubmissionUser> getSubmissionUsers() throws RepositoryException {
		return submissionUsers.getSubmissionUsers();  
	}

}
//#endif