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
import riseevents.ev.util.LibraryOfDSL;

public class AssignmentControl {
	
    private AssignmentRepository assignmentList;
	
	public AssignmentControl(AssignmentRepository repository){
		this.assignmentList = repository;
	}
	public boolean isThere(Assignment assignment) throws RepositoryException {
		return assignmentList.isThere(assignment);
	}

	public List<Assignment> getAssignmentList() throws RepositoryException {
		return assignmentList.getAssignmentList();  
	}
}
//#endif