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

	private ReviewerRepository reviewers;
	
	public ReviewerControl(ReviewerRepository repository){
		this.reviewers = repository;
	}
	
	//usar instance of para saber qual o objeto dependendo disso direciona para o respectivo repositorio.
	{% if 'Insert' in data.commands %}
	public void insert(Reviewer reviewer) throws ReviewerAlreadyInsertedException, RepositoryException{
		if (reviewer != null) {
			if (!reviewers.isThere(reviewer.getIdUser())) 
				reviewers.insert(reviewer);
			else
				throw new ReviewerAlreadyInsertedException(reviewer.getIdUser());
		} else {
            throw new IllegalArgumentException();
        }
	}  
	{% endif %}
	{% if 'Remove' in data.commands %}
	public void remove(int idUser) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		if(reviewers.isThere(idUser))
			reviewers.remove(idUser);
		else
			throw new ReviewerNotFoundException(idUser);
	}
	{% endif %}
	{% if 'Update' in data.commands %}
	public void update(Reviewer reviewer) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		if(reviewers.isThere(reviewer.getIdUser()))
			reviewers.update(reviewer);
		else
			throw new ReviewerNotFoundException(reviewer.getIdUser());
	}
	{% endif %}
	{% if 'Search' in data.commands %}
	public Reviewer search(int idUser) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		return reviewers.search(idUser);
	}
	{% endif %}
	
	public boolean isThere(int idUser) throws RepositoryException {
		return reviewers.isThere(idUser);
	}

	public List<Reviewer> getReviewers() throws RepositoryException { 
		return reviewers.getReviewers();  
	}
	
	public Reviewer getReviewerByknowledgeArea(String knowledgearea) throws ReviewerAlreadyInsertedException, RepositoryException, ReviewerNotFoundException{
		return reviewers.getReviewerByknowledgeArea(knowledgearea);
	}

}
//#endif