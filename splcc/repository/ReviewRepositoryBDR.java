//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package {{systemName|lower}}.ev.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import {{systemName|lower}}.ev.data.Review;
import {{systemName|lower}}.ev.data.Review.StatusReview;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.exception.ReviewNotFoundException;
import {{systemName|lower}}.ev.util.PersistenceMechanismException;
import {{systemName|lower}}.ev.util.PersistenceMechanismRDBMS;

public class ReviewRepositoryBDR implements ReviewRepository {
	
	private static ReviewRepositoryBDR instance;
	private PersistenceMechanismRDBMS pm;
	
	public ReviewRepositoryBDR(){
		try{
			pm = PersistenceMechanismRDBMS.getInstance();
			pm.connect();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public synchronized static ReviewRepositoryBDR getInstance(){
		if(instance == null){
			instance = new ReviewRepositoryBDR();
		}
		return instance;
	}
	
	
	@Override
	public void insert(Review review) throws RepositoryException {
		try {
			Statement statement = (Statement) pm.getCommunicationChannel();
			statement.executeUpdate("INSERT INTO review (idSubmission, date, status, description1, round, result) Values('"+review.getIdSubmission()+"', '"+review.getDate()+"', '"+ review.getStatus() +"', '" + review.getDescription()+ "', '" + review.getRound()+ "', '" + review.getResult()+ "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceMechanismException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
	}
	


	@Override
	public List<Review> getReviewList() throws RepositoryException {
		Review review = null;
		ArrayList<Review> list = new ArrayList<Review>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select * from review");
            while (resultset.next()) {
                review = new Review();
                review.setIdReview(resultset.getInt("idReview"));
                review.setIdSubmission(resultset.getInt("idSubmission"));
                review.setDate(resultset.getString("date"));
                review.setStatus(StatusReview.valueOf(resultset.getString("status")));
                review.setDescription(resultset.getString("description1"));
                review.setRound(resultset.getInt("round"));
                review.setResult(resultset.getString("result"));
            
				list.add(review);
            } 
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return list;
	}

	@Override
	public boolean isThere(int idReview) throws RepositoryException {
		boolean answer = false;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT * FROM review WHERE idReview = '" + idReview + "'");
            answer = resultset.next();
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
        return answer;
	}
	
	@Override
	public int getReviewLastId() throws RepositoryException {
		int answer=-1;
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("SELECT AUTO_INCREMENT as proximo_valor FROM information_schema.tables WHERE TABLE_SCHEMA= 'EeventDB' AND TABLE_NAME= 'review'");
            resultset.first();
            answer = resultset.getInt("proximo_valor");
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
        return answer;
	}
	
	@Override
	public void remove(int idReview) throws ReviewNotFoundException,
			RepositoryException {
		try{
            Statement statement = (Statement) pm.getCommunicationChannel();
		    int i = statement.executeUpdate("DELETE FROM review WHERE idReview = '"+ idReview +"'");
            if (i == 0) {
            	throw new ReviewNotFoundException(idReview);
            }
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
		} catch(SQLException e){
            throw new RepositoryException(e);            
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
	}
	
	@Override
	public void update(Review review) throws ReviewNotFoundException,
			RepositoryException {
		try {
    	    Statement statement = (Statement) pm.getCommunicationChannel();

            		statement.executeUpdate("UPDATE review SET idSubmission = '"+ review.getIdSubmission() +"', status = '"+ review.getStatus() +"', date = '"+ review.getDate() +"', description1 = '"+ review.getDescription()+"', result = '"+ review.getResult()+"', round = '"+ review.getRound()+"'  WHERE idReview = '"+ review.getIdReview()+"'");

		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
		} catch (SQLException e) {
		    throw new RepositoryException(e);
	    } finally {
	    	try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		
	}
	
	@Override
	public Review search(int idReview) throws ReviewNotFoundException,
			RepositoryException {
		Review reviewFounded = new Review();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("Select * from review WHERE idReview =" + idReview);
            if (resultset.next()) {   
            	reviewFounded.setIdReview(resultset.getInt("idReview"));
            	reviewFounded.setIdSubmission(resultset.getInt("idSubmission"));
            	reviewFounded.setStatus(StatusReview.valueOf(resultset.getString("status")));
            	reviewFounded.setDate(resultset.getString("date"));
            	reviewFounded.setDescription(resultset.getString("description1"));
            	reviewFounded.setRound(resultset.getInt("round"));
                reviewFounded.setResult(resultset.getString("result"));
                resultset.close();
            } else {
            	throw new ReviewNotFoundException(idReview);
            }
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return reviewFounded;
	}

	public List<String> getReviewsBySubmission(int idSubmission) throws RepositoryException{
		ArrayList<String> list = new ArrayList<String>();
        try {
            Statement statement = (Statement) pm.getCommunicationChannel();
            ResultSet resultset = statement.executeQuery("select Review.description1 from Review where idSubmission = " + idSubmission);
            while (resultset.next()) {            
				list.add(resultset.getString("description1"));
            } 
			resultset.close();
		} catch(PersistenceMechanismException e){
            throw new RepositoryException(e);
        } catch (SQLException e) {
			throw new RepositoryException(e);
		} finally {
			try {
				pm.releaseCommunicationChannel();
			} catch (PersistenceMechanismException ex) {
				throw new RepositoryException(ex);
			}
		}
		return list;
	}

}
//#endif
