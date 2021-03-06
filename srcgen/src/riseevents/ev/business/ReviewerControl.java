//#if ${Reviewer} == "T"
package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.Reviewer;
import riseevents.ev.data.User;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.ReviewerAlreadyInsertedException;
import riseevents.ev.exception.ReviewerNotFoundException;
import riseevents.ev.repository.ReviewerRepository;

public class ReviewerControl {

	private ReviewerRepository reviewerList;
	
	public ReviewerControl(ReviewerRepository repository){
		this.reviewerList = repository;
	}
	
	//usar instance of para saber qual o objeto dependendo disso direciona para o respectivo repositorio.
	public void insert(Reviewer reviewer) throws ReviewerAlreadyInsertedException, RepositoryException{
		if (reviewer != null) {
			if (!reviewerList.isThere(reviewer.getIdUser())) 
				reviewerList.insert(reviewer);
			else
				throw new ReviewerAlreadyInsertedException(reviewer.getIdUser());
		} else {
            throw new IllegalArgumentException();
        }
	}  
	public void remove(int idUser) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		if(reviewerList.isThere(idUser))
			reviewerList.remove(idUser);
		else
			throw new ReviewerNotFoundException(idUser);
	}
	public void update(Reviewer reviewer) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		if(reviewerList.isThere(reviewer.getIdUser()))
			reviewerList.update(reviewer);
		else
			throw new ReviewerNotFoundException(reviewer.getIdUser());
	}
	public Reviewer search(int idUser) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		return reviewerList.search(idUser);
	}
	
	public boolean isThere(int idUser) throws RepositoryException {
		return reviewerList.isThere(idUser);
	}

	public List<Reviewer> getReviewerList() throws RepositoryException { 
		return reviewerList.getReviewerList();  
	}
	
	public Reviewer getReviewerByknowledgeArea(String knowledgearea) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		return reviewerList.getReviewerByknowledgeArea(knowledgearea);
	}

}
//#endif