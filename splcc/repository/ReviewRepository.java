//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.Review;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.ReviewNotFoundException;


public interface ReviewRepository {

	public void insert(Review review) throws RepositoryException;
	
	public List<Review> getReviews() throws RepositoryException;
	
	public boolean isThere(int idReview) throws RepositoryException;
	
	public int getReviewLastId() throws RepositoryException;
	
	public void remove(int idReview) throws ReviewNotFoundException, RepositoryException;
	
	public void update(Review review) throws ReviewNotFoundException, RepositoryException;
	
	public Review search(int idReview) throws ReviewNotFoundException, RepositoryException;
	
	public List<String> getReviewsBySubmission(int idSubmission) throws RepositoryException;
	
}
//#endif