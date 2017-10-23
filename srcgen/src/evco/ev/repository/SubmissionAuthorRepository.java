//#if ${InsertAuthors} == "T"
package evco.ev.repository;

import java.util.List;

import evco.ev.data.SubmissionAuthor;
import evco.ev.data.SubmissionUser;
import evco.ev.exception.RepositoryException;
import evco.ev.exception.SubmissionAuthorNotFoundException;
import evco.ev.exception.SubmissionUserNotFoundException;

public interface SubmissionAuthorRepository {
	public void insert(SubmissionAuthor submissionAuthor) throws RepositoryException;

	public void remove(SubmissionAuthor submissionAuthor) throws SubmissionAuthorNotFoundException, RepositoryException;

	public SubmissionAuthor search(SubmissionAuthor submissionAuthor) throws SubmissionAuthorNotFoundException, RepositoryException;
	
	public List<SubmissionAuthor> getSubmissionAuthorList() throws RepositoryException;

	public void update(SubmissionAuthor submissionAuthor) throws SubmissionAuthorNotFoundException, RepositoryException;

	public boolean isThere(SubmissionAuthor submissionAuthor) throws RepositoryException;
}
//#endif