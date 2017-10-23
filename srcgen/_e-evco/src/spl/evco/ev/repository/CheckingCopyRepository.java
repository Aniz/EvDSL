//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package evco.ev.repository;

import java.util.List;

import evco.ev.data.CheckingCopy;
import evco.ev.data.CheckingCopy;
import evco.ev.exception.CheckingCopyNotFoundException;
import evco.ev.exception.CheckingCopyNotFoundException;
import evco.ev.exception.RepositoryException;

public interface CheckingCopyRepository {

	public void insert(CheckingCopy checkingCopy) throws RepositoryException;
	
	public List<CheckingCopy> getCheckingCopyList() throws RepositoryException;
	
	public boolean isThere(int idCheckingCopy) throws RepositoryException;
	
	public int getCheckingCopyLastId() throws RepositoryException;
	
	public void remove(int idCheckingCopy) throws CheckingCopyNotFoundException, RepositoryException;
	
	public void update(CheckingCopy checkingCopy) throws CheckingCopyNotFoundException, RepositoryException;

	public CheckingCopy search(int idCheckingCopy) throws CheckingCopyNotFoundException, RepositoryException;
	
}
//#endif