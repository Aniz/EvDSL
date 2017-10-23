//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package evco.ev.business;

import java.util.List;

import org.apache.commons.mail.EmailException;

import evco.ev.data.Review;
import evco.ev.data.User;
import evco.ev.exception.RepositoryException;
import evco.ev.exception.ReviewAlreadyInsertedException;
import evco.ev.exception.ReviewNotFoundException;
import evco.ev.repository.ReviewRepository;
import evco.ev.util.LibraryOfDSL;


public class ReviewControl {
	
    private ReviewRepository reviewList;
	
	public ReviewControl(ReviewRepository repository){
		this.reviewList = repository;
	}

	public void insert(Review review) throws ReviewAlreadyInsertedException, RepositoryException{
		if (review != null) {
			if (!reviewList.isThere(review.getIdReview())) 
				reviewList.insert(review);
			else
				throw new ReviewAlreadyInsertedException(review.getIdReview());
		} else {
            throw new IllegalArgumentException();
        }
	}
	
	public void remove(int idReview) throws ReviewAlreadyInsertedException, RepositoryException, ReviewNotFoundException{
		reviewList.remove(idReview);
	}
	
	public void update(Review review) throws ReviewAlreadyInsertedException, RepositoryException, ReviewNotFoundException{
		reviewList.update(review);
	}
	
	public List<Review> getReviewList() throws RepositoryException {
		return reviewList.getReviewList();  
	}
	
	public int getReviewLastId() throws RepositoryException{
		return reviewList.getReviewLastId();
	}
	
	public Review search(int idReview) throws ReviewAlreadyInsertedException, RepositoryException, ReviewNotFoundException{
		return reviewList.search(idReview);
	}

	public boolean isThere(int idReview) throws RepositoryException {
		return reviewList.isThere(idReview);
	}
	
	public List<String> getReviewsBySubmission(int idSubmission) throws RepositoryException{
		return reviewList.getReviewsBySubmission(idSubmission);
	}
}
//#endif