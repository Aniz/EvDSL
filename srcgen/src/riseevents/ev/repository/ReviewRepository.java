//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.Review;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.ReviewNotFoundException;


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