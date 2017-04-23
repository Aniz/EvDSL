//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.CheckingCopy;
import riseevents.ev.data.CheckingCopy;
import riseevents.ev.exception.CheckingCopyNotFoundException;
import riseevents.ev.exception.CheckingCopyNotFoundException;
import riseevents.ev.exception.RepositoryException;

public interface CheckingCopyRepository {

	public void insert(CheckingCopy checkingCopy) throws RepositoryException;
	
	public List<CheckingCopy> getCheckingCopys() throws RepositoryException;
	
	public boolean isThere(int idCheckingCopy) throws RepositoryException;
	
	public int getCheckingCopyLastId() throws RepositoryException;
	
	public void remove(int idCheckingCopy) throws CheckingCopyNotFoundException, RepositoryException;
	
	public void update(CheckingCopy checkingCopy) throws CheckingCopyNotFoundException, RepositoryException;

	public CheckingCopy search(int idCheckingCopy) throws CheckingCopyNotFoundException, RepositoryException;
	
}
//#endif