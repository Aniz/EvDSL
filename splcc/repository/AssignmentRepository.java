//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.Assignment;
import {{systemName|lower}}.ev.exception.AssignmentNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;

public interface AssignmentRepository {

	public void insert(Assignment assignment) throws RepositoryException;

	public void remove(Assignment assignment) throws AssignmentNotFoundException, RepositoryException;
	
	public Assignment search(Assignment assignment) throws AssignmentNotFoundException, RepositoryException;
	
	public List<Assignment> getAssignments() throws RepositoryException;

	public void update(Assignment assignment) throws AssignmentNotFoundException, RepositoryException;	
	
	public boolean isThere(Assignment assignment) throws RepositoryException;
	
}
//#endif