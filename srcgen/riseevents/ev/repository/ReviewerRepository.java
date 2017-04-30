//#if ${Reviewer} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.Reviewer;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.ReviewerNotFoundException;

public interface ReviewerRepository {
	
	public void insert(Reviewer reviewer) throws RepositoryException;

	// retirado pq o delete on cascade ja sera usado. motivo: carantia da consistencia do banco
	public void remove(int idSpeaker) throws ReviewerNotFoundException, RepositoryException;

	public Reviewer search(int idUser) throws ReviewerNotFoundException, RepositoryException;
	
	public List<Reviewer> getReviewerList() throws RepositoryException;

	public void update(Reviewer reviewer) throws ReviewerNotFoundException, RepositoryException;

	public boolean isThere(int idUser) throws RepositoryException;

	public Reviewer getReviewerByknowledgeArea(String knowledgearea) throws ReviewerNotFoundException, RepositoryException;
	
}
//#endif