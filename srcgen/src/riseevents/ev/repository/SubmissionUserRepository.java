//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.SubmissionUser;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.SubmissionUserNotFoundException;

public interface SubmissionUserRepository {
	
	public void insert(SubmissionUser submissionUser) throws RepositoryException;

	public void remove(SubmissionUser submissionUser) throws SubmissionUserNotFoundException, RepositoryException;

	public SubmissionUser search(SubmissionUser submissionUser) throws SubmissionUserNotFoundException, RepositoryException;
	
	public List<SubmissionUser> getSubmissionUserList() throws RepositoryException;

	public void update(SubmissionUser submissionUser) throws SubmissionUserNotFoundException, RepositoryException;

	public boolean isThere(SubmissionUser submissionUser) throws RepositoryException;
	
}
//#endif