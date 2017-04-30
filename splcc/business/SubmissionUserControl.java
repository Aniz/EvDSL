//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.SubmissionUser;
import {{systemName|lower}}.ev.exception.SubmissionUserAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.SubmissionUserNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.SubmissionUserRepository;

public class SubmissionUserControl {

	private SubmissionUserRepository submissionUserList;
	
	public SubmissionUserControl(SubmissionUserRepository repository){
		this.submissionUserList = repository;
	}
	
	public void insert(SubmissionUser submissionUser) throws SubmissionUserAlreadyInsertedException, RepositoryException{
		if (submissionUser != null) {
            if (submissionUserList.isThere(submissionUser) == false) {
            	submissionUserList.insert(submissionUser);
            } else {
                throw new SubmissionUserAlreadyInsertedException(submissionUser.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(SubmissionUser submissionUser) throws SubmissionUserAlreadyInsertedException, RepositoryException, SubmissionUserNotFoundException{		
		submissionUserList.remove(submissionUser);
	}
	
	public void update(SubmissionUser submissionUser) throws SubmissionUserAlreadyInsertedException, RepositoryException, SubmissionUserNotFoundException{
		submissionUserList.update(submissionUser);
	}
	
	public SubmissionUser search(SubmissionUser submissionUser) throws SubmissionUserAlreadyInsertedException, RepositoryException, SubmissionUserNotFoundException{
		return submissionUserList.search(submissionUser);
	}

	public boolean isThere(SubmissionUser submissionUser) throws RepositoryException {
		return submissionUserList.isThere(submissionUser);
	}

	public List<SubmissionUser> getSubmissionUserList() throws RepositoryException {
		return submissionUserList.getSubmissionUserList();  
	}

}
//#endif