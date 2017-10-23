//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package evco.ev.repository;

import java.util.List;

import evco.ev.data.Review;
import evco.ev.exception.RepositoryException;
import evco.ev.exception.ReviewNotFoundException;


public interface ReviewRepository {

	public void insert(Review review) throws RepositoryException;
	
	public List<Review> getReviewList() throws RepositoryException;
	
	public boolean isThere(int idReview) throws RepositoryException;
	
	public int getReviewLastId() throws RepositoryException;
	
	public void remove(int idReview) throws ReviewNotFoundException, RepositoryException;
	
	public void update(Review review) throws ReviewNotFoundException, RepositoryException;
	
	public Review search(int idReview) throws ReviewNotFoundException, RepositoryException;
	
	public List<String> getReviewsBySubmission(int idSubmission) throws RepositoryException;
	
}
//#endif