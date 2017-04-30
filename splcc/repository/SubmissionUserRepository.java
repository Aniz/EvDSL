//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.SubmissionUser;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.SubmissionUserNotFoundException;

public interface SubmissionUserRepository {
	
	public void insert(SubmissionUser submissionUser) throws RepositoryException;

	public void remove(SubmissionUser submissionUser) throws SubmissionUserNotFoundException, RepositoryException;

	public SubmissionUser search(SubmissionUser submissionUser) throws SubmissionUserNotFoundException, RepositoryException;
	
	public List<SubmissionUser> getSubmissionUserList() throws RepositoryException;

	public void update(SubmissionUser submissionUser) throws SubmissionUserNotFoundException, RepositoryException;

	public boolean isThere(SubmissionUser submissionUser) throws RepositoryException;
	
}
//#endif