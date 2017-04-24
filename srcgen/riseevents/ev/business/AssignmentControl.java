//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package riseevents.ev.business;

import java.util.List;

import org.apache.commons.mail.EmailException;


import riseevents.ev.data.Assignment;


import riseevents.ev.data.Review;
import riseevents.ev.data.User;
import riseevents.ev.exception.AssignmentAlreadyInsertedException;
import riseevents.ev.exception.AssignmentNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.AssignmentRepository;
import riseevents.ev.util.Email;

public class AssignmentControl {
	
    private AssignmentRepository assignments;
	
	public AssignmentControl(AssignmentRepository repository){
		this.assignments = repository;
	}
	
	public void insert(Assignment assignment) throws AssignmentAlreadyInsertedException, RepositoryException{
		if (assignment != null) {
            if (!assignments.isThere(assignment)) {
                assignments.insert(assignment);
            } else {
                throw new AssignmentAlreadyInsertedException(assignment.getIdReview());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}

	public void remove(Assignment assignment) throws AssignmentAlreadyInsertedException, RepositoryException, AssignmentNotFoundException{
		assignments.remove(assignment);
	}
	
	public void update(Assignment assignment) throws AssignmentAlreadyInsertedException, RepositoryException, AssignmentNotFoundException{
		assignments.update(assignment);
	}
	
	public Assignment search(Assignment assignment) throws AssignmentAlreadyInsertedException, RepositoryException, AssignmentNotFoundException{
		return assignments.search(assignment);
	}

	public boolean isThere(Assignment assignment) throws RepositoryException {
		return assignments.isThere(assignment);
	}

	public List<Assignment> getAssignments() throws RepositoryException {
		return assignments.getAssignments();  
	}
	
}
//#endif