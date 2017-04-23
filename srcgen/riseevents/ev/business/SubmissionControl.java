//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package riseevents.ev.business;

import java.io.File;
import java.util.List;

import riseevents.ev.data.Submission;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.SubmissionAlreadyInsertedException;
import riseevents.ev.exception.SubmissionNotFoundException;
import riseevents.ev.repository.SubmissionRepository;


public class SubmissionControl {
	
    private SubmissionRepository submissions;
	
	public SubmissionControl(SubmissionRepository repository){
		this.submissions = repository;
	}

	public void insert(Submission submission) throws SubmissionAlreadyInsertedException, RepositoryException{
		if (submission != null) {
			if (!submissions.isThere(submission.getIdSubmission())) 
				submissions.insert(submission);
			else
				throw new SubmissionAlreadyInsertedException(submission.getIdSubmission());
		} else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(int idSubmission) throws SubmissionAlreadyInsertedException, RepositoryException, SubmissionNotFoundException{
		submissions.remove(idSubmission);
	}
	
	public void update(Submission submission) throws SubmissionAlreadyInsertedException, RepositoryException, SubmissionNotFoundException{
		submissions.update(submission);
	}
	
	
	public List<Submission> getSubmissions() throws RepositoryException {
		return submissions.getSubmissions();  
	}
	
	public int getSubmissionLastId() throws RepositoryException{
		return submissions.getSubmissionLastId();
	}


	public int getSubmissionIdByTitle(String submissionTitle) throws RepositoryException{
		return submissions.getSubmissionIdByTitle(submissionTitle);
	}

	public Submission search(int idSubmission) throws SubmissionAlreadyInsertedException, RepositoryException, SubmissionNotFoundException{
		return submissions.search(idSubmission);
	}

	// Generated by DSL
	public void inserAttachmanet(File attachment, int idActivity) throws SubmissionAlreadyInsertedException, RepositoryException{
		submissions.insert(attachment, idActivity);
	}
	//
	public void pdfRecover(int idSubmission) throws RepositoryException {
		submissions.pdfRecover(idSubmission);
	}
	
	public List<Submission> getSubmissionsByUser(int idUser) throws RepositoryException{
		return submissions.getSubmissionsByUser(idUser);
	}
}
//#endif