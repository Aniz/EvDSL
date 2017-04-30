//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package {{systemName|lower}}.ev.business;

import java.io.File;
import java.util.List;

import {{systemName|lower}}.ev.data.Submission;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.SubmissionAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.SubmissionNotFoundException;
import {{systemName|lower}}.ev.repository.SubmissionRepository;


public class SubmissionControl {
	
    private SubmissionRepository submissionList;
	
	public SubmissionControl(SubmissionRepository repository){
		this.submissionList = repository;
	}
	{% if 'Insert' in data.commands %}	
	public void insert(Submission submission) throws SubmissionAlreadyInsertedException, RepositoryException{
		if (submission != null) {
			if (!submissionList.isThere(submission.getIdSubmission())) 
				submissionList.insert(submission);
			else
				throw new SubmissionAlreadyInsertedException(submission.getIdSubmission());
		} else {
            throw new IllegalArgumentException();
        }
	}
	{% endif %}
	{% if 'Remove' in data.commands %}
	public void remove(int idSubmission) throws SubmissionAlreadyInsertedException, RepositoryException, SubmissionNotFoundException{
		submissionList.remove(idSubmission);
	}
	{% endif %}
	{% if 'Update' in data.commands %}
	public void update(Submission submission) throws SubmissionAlreadyInsertedException, RepositoryException, SubmissionNotFoundException{
		submissionList.update(submission);
	}
	{% endif %}
	
	public List<Submission> getSubmissionList() throws RepositoryException {
		return submissionList.getSubmissionList();  
	}
	
	public int getSubmissionLastId() throws RepositoryException{
		return submissionList.getSubmissionLastId();
	}

	public int getSubmissionIdByTitle(String submissionTitle) throws RepositoryException{
		return submissionList.getSubmissionIdByTitle(submissionTitle);
	}

	public Submission search(int idSubmission) throws SubmissionAlreadyInsertedException, RepositoryException, SubmissionNotFoundException{
		return submissionList.search(idSubmission);
	}

	{% if 'insertAttachment' in data.statments %}
	// Generated by DSL
	public void inserAttachmanet(File attachment, int idActivity) throws SubmissionAlreadyInsertedException, RepositoryException{
		submissionList.insert(attachment, idActivity);
	}
	//
	{% endif %}
	public void pdfRecover(int idSubmission) throws RepositoryException {
		submissionList.pdfRecover(idSubmission);
	}
	
	public List<Submission> getSubmissionsByUser(int idUser) throws RepositoryException{
		return submissionList.getSubmissionsByUser(idUser);
	}
}
//#endif