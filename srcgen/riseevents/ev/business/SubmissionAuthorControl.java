//#if ${InsertAuthors} == "T"
package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.SubmissionAuthor;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.SubmissionAuthorAlreadyInsertedException;
import riseevents.ev.exception.SubmissionAuthorNotFoundException;
import riseevents.ev.repository.SubmissionAuthorRepository;

public class SubmissionAuthorControl {

	
	private SubmissionAuthorRepository submissionAuthors;
	
	public SubmissionAuthorControl(SubmissionAuthorRepository repository){
		this.submissionAuthors = repository;
	}
	
	public void insert(SubmissionAuthor submissionAuthor) throws SubmissionAuthorAlreadyInsertedException, RepositoryException{
		if (submissionAuthor != null) {
            if (submissionAuthors.isThere(submissionAuthor) == false) {
            	submissionAuthors.insert(submissionAuthor);
            } else {
                throw new SubmissionAuthorAlreadyInsertedException(submissionAuthor.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(SubmissionAuthor submissionAuthor) throws SubmissionAuthorAlreadyInsertedException, RepositoryException, SubmissionAuthorNotFoundException{		
		submissionAuthors.remove(submissionAuthor);
	}
	
	public void update(SubmissionAuthor submissionAuthor) throws SubmissionAuthorAlreadyInsertedException, RepositoryException, SubmissionAuthorNotFoundException{
		submissionAuthors.update(submissionAuthor);
	}
	
	public SubmissionAuthor search(SubmissionAuthor submissionAuthor) throws SubmissionAuthorAlreadyInsertedException, RepositoryException, SubmissionAuthorNotFoundException{
		return submissionAuthors.search(submissionAuthor);
	}

	public boolean isThere(SubmissionAuthor submissionAuthor) throws RepositoryException {
		return submissionAuthors.isThere(submissionAuthor);
	}

	public List<SubmissionAuthor> getSubmissionAuthors() throws RepositoryException {
		return submissionAuthors.getSubmissionAuthors();  
	}

}
//#endif