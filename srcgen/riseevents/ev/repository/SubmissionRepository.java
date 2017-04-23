//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package riseevents.ev.repository;

import java.io.File;
import java.util.List;

import riseevents.ev.data.Submission;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.SubmissionNotFoundException;
import riseevents.ev.data.Submission.TypeSubmission;

public interface SubmissionRepository {

	public void insert(Submission submission) throws RepositoryException;
	
	public List<Submission> getSubmissions() throws RepositoryException;
	
	public boolean isThere(int idSubmission) throws RepositoryException;
	
	public int getSubmissionLastId() throws RepositoryException;
	
	public void remove(int idSubmission) throws SubmissionNotFoundException, RepositoryException;
	
	public void update(Submission submission) throws SubmissionNotFoundException, RepositoryException;

	public int getSubmissionIdByTitle(String submissionTitle) throws RepositoryException;
	
	public Submission search(int idSubmission) throws SubmissionNotFoundException, RepositoryException;
	
	
	
	public void pdfRecover(int idSubmission) throws RepositoryException;
	
	public List<Submission> getSubmissionsByUser(int idUser) throws RepositoryException ;
	
}
//#endif