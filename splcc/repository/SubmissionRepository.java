//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package {{systemName|lower}}.ev.repository;

import java.io.File;
import java.util.List;

import {{systemName|lower}}.ev.data.Submission;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.SubmissionNotFoundException;
{% if data.option.categories|length > 0 %}
import {{systemName|lower}}.ev.data.{{data.option.entity}}.Type{{data.option.entity}};
{% endif %}

public interface SubmissionRepository {

	public void insert(Submission submission) throws RepositoryException;
	
	public List<Submission> getSubmissionList() throws RepositoryException;
	
	public boolean isThere(int idSubmission) throws RepositoryException;
	
	public int getSubmissionLastId() throws RepositoryException;
	
	public void remove(int idSubmission) throws SubmissionNotFoundException, RepositoryException;
	
	public void update(Submission submission) throws SubmissionNotFoundException, RepositoryException;

	public int getSubmissionIdByTitle(String submissionTitle) throws RepositoryException;
	
	public Submission search(int idSubmission) throws SubmissionNotFoundException, RepositoryException;
	
{% if 'insertAttachment' in data.statments %}
	// Generated by DSL
	public void insert (File attachment, int idActivity) throws RepositoryException;
	//
{% endif %}	
	
	public void pdfRecover(int idSubmission) throws RepositoryException;
	
	public List<Submission> getSubmissionsByUser(int idUser) throws RepositoryException ;
	
}
//#endif