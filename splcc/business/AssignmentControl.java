//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import org.apache.commons.mail.EmailException;


import {{systemName|lower}}.ev.data.Assignment;


import {{systemName|lower}}.ev.data.Review;
import {{systemName|lower}}.ev.data.User;
import {{systemName|lower}}.ev.exception.AssignmentAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.AssignmentNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.AssignmentRepository;
import {{systemName|lower}}.ev.util.LibraryOfDSL;

public class AssignmentControl {
	
    private AssignmentRepository assignmentList;
	
	public AssignmentControl(AssignmentRepository repository){
		this.assignmentList = repository;
	}
	{% if 'Insert' in data.commands %}
	public void insert(Assignment assignment) throws AssignmentAlreadyInsertedException, RepositoryException{
		if (assignment != null) {
            if (!assignmentList.isThere(assignment)) {
                assignmentList.insert(assignment);
            } else {
                throw new AssignmentAlreadyInsertedException(assignment.getIdReview());
            }
        } else {
            throw new IllegalArgumentException();
        }
	}
	{% endif %}
	{% if 'Remove' in data.commands %}	
	public void remove(Assignment assignment) throws AssignmentAlreadyInsertedException, RepositoryException, AssignmentNotFoundException{
		assignmentList.remove(assignment);
	}
	{% endif %}
	{% if 'Update' in data.commands %}
	public void update(Assignment assignment) throws AssignmentAlreadyInsertedException, RepositoryException, AssignmentNotFoundException{
		assignmentList.update(assignment);
	}
	{% endif %}
	{% if 'Search' in data.commands %}
	public Assignment search(Assignment assignment) throws AssignmentAlreadyInsertedException, RepositoryException, AssignmentNotFoundException{
		return assignmentList.search(assignment);
	}
	{% endif %}
	public boolean isThere(Assignment assignment) throws RepositoryException {
		return assignmentList.isThere(assignment);
	}

	public List<Assignment> getAssignmentList() throws RepositoryException {
		return assignmentList.getAssignmentList();  
	}
}
//#endif