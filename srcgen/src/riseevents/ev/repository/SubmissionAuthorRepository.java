//#if ${InsertAuthors} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.SubmissionAuthor;
import riseevents.ev.data.SubmissionUser;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.SubmissionAuthorNotFoundException;
import riseevents.ev.exception.SubmissionUserNotFoundException;

public interface SubmissionAuthorRepository {
	public void insert(SubmissionAuthor submissionAuthor) throws RepositoryException;

	public void remove(SubmissionAuthor submissionAuthor) throws SubmissionAuthorNotFoundException, RepositoryException;

	public SubmissionAuthor search(SubmissionAuthor submissionAuthor) throws SubmissionAuthorNotFoundException, RepositoryException;
	
	public List<SubmissionAuthor> getSubmissionAuthorList() throws RepositoryException;

	public void update(SubmissionAuthor submissionAuthor) throws SubmissionAuthorNotFoundException, RepositoryException;

	public boolean isThere(SubmissionAuthor submissionAuthor) throws RepositoryException;
}
//#endif