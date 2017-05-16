//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package riseevents.ev.business;

import java.util.List;

import org.apache.commons.mail.EmailException;

import riseevents.ev.data.Review;
import riseevents.ev.data.User;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.ReviewAlreadyInsertedException;
import riseevents.ev.exception.ReviewNotFoundException;
import riseevents.ev.repository.ReviewRepository;
import riseevents.ev.util.LibraryOfDSL;


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
	
	public void remove(Review review) throws ReviewAlreadyInsertedException, RepositoryException, ReviewNotFoundException{
		reviewList.remove(review);
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
	
	public Review search(Review review) throws ReviewAlreadyInsertedException, RepositoryException, ReviewNotFoundException{
		return reviewList.search(review);
	}

	public boolean isThere(int idReview) throws RepositoryException {
		return reviewList.isThere(idReview);
	}
	
	public List<String> getReviewsBySubmission(int idSubmission) throws RepositoryException{
		return reviewList.getReviewsBySubmission(idSubmission);
	}
}
//#endif