//#if ${InsertAuthors} == "T"
package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.SubmissionAuthor;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.SubmissionAuthorAlreadyInsertedException;
import riseevents.ev.exception.SubmissionAuthorNotFoundException;
import riseevents.ev.repository.SubmissionAuthorRepository;

public class SubmissionAuthorControl {

	
	private SubmissionAuthorRepository submissionAuthorList;
	
	public SubmissionAuthorControl(SubmissionAuthorRepository repository){
		this.submissionAuthorList = repository;
	}
	
	public void insert(SubmissionAuthor submissionAuthor) throws SubmissionAuthorAlreadyInsertedException, RepositoryException{
		if (submissionAuthor != null) {
            if (submissionAuthorList.isThere(submissionAuthor) == false) {
            	submissionAuthorList.insert(submissionAuthor);
            } else {
                throw new SubmissionAuthorAlreadyInsertedException(submissionAuthor.getIdActivity());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(SubmissionAuthor submissionAuthor) throws SubmissionAuthorAlreadyInsertedException, RepositoryException, SubmissionAuthorNotFoundException{		
		submissionAuthorList.remove(submissionAuthor);
	}
	
	public void update(SubmissionAuthor submissionAuthor) throws SubmissionAuthorAlreadyInsertedException, RepositoryException, SubmissionAuthorNotFoundException{
		submissionAuthorList.update(submissionAuthor);
	}
	
	public SubmissionAuthor search(SubmissionAuthor submissionAuthor) throws SubmissionAuthorAlreadyInsertedException, RepositoryException, SubmissionAuthorNotFoundException{
		return submissionAuthorList.search(submissionAuthor);
	}

	public boolean isThere(SubmissionAuthor submissionAuthor) throws RepositoryException {
		return submissionAuthorList.isThere(submissionAuthor);
	}

	public List<SubmissionAuthor> getSubmissionAuthorList() throws RepositoryException {
		return submissionAuthorList.getSubmissionAuthorList();  
	}

}
//#endif