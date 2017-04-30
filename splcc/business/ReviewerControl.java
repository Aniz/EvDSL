//#if ${Reviewer} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.Reviewer;
import {{systemName|lower}}.ev.data.User;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.ReviewerAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.ReviewerNotFoundException;
import {{systemName|lower}}.ev.repository.ReviewerRepository;

public class ReviewerControl {

	private ReviewerRepository reviewerList;
	
	public ReviewerControl(ReviewerRepository repository){
		this.reviewerList = repository;
	}
	
	//usar instance of para saber qual o objeto dependendo disso direciona para o respectivo repositorio.
	{% if 'Insert' in data.commands %}
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
	{% endif %}
	{% if 'Remove' in data.commands %}
	public void remove(int idUser) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		if(reviewerList.isThere(idUser))
			reviewerList.remove(idUser);
		else
			throw new ReviewerNotFoundException(idUser);
	}
	{% endif %}
	{% if 'Update' in data.commands %}
	public void update(Reviewer reviewer) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		if(reviewerList.isThere(reviewer.getIdUser()))
			reviewerList.update(reviewer);
		else
			throw new ReviewerNotFoundException(reviewer.getIdUser());
	}
	{% endif %}
	{% if 'Search' in data.commands %}
	public Reviewer search(int idUser) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		return reviewerList.search(idUser);
	}
	{% endif %}
	
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