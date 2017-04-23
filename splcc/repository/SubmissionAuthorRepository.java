//#if ${InsertAuthors} == "T"
package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.SubmissionAuthor;
import {{systemName|lower}}.ev.data.SubmissionUser;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.SubmissionAuthorNotFoundException;
import {{systemName|lower}}.ev.exception.SubmissionUserNotFoundException;

public interface SubmissionAuthorRepository {
	public void insert(SubmissionAuthor submissionAuthor) throws RepositoryException;

	public void remove(SubmissionAuthor submissionAuthor) throws SubmissionAuthorNotFoundException, RepositoryException;

	public SubmissionAuthor search(SubmissionAuthor submissionAuthor) throws SubmissionAuthorNotFoundException, RepositoryException;
	
	public List<SubmissionAuthor> getSubmissionAuthors() throws RepositoryException;

	public void update(SubmissionAuthor submissionAuthor) throws SubmissionAuthorNotFoundException, RepositoryException;

	public boolean isThere(SubmissionAuthor submissionAuthor) throws RepositoryException;
}
//#endif