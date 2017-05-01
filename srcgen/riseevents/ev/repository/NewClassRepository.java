// Autogenerated by EvDSL
package riseevents.ev.repository;
import riseevents.ev.util.LibraryOfDSL;
import java.util.List;
import riseevents.ev.data.Newclass;
import riseevents.ev.exception.NewclassNotFoundException;
import riseevents.ev.exception.RepositoryException;

public interface NewclassRepository {

	public void insert(Newclass newclass) throws RepositoryException;

	public void remove(Newclass newclass) throws NewclassNotFoundException, RepositoryException;

	public Newclass search(int idEntity) throws NewclassNotFoundException, RepositoryException;
	
	public List<Newclass> getNewclassList() throws RepositoryException;

	public void update(Newclass newclass) throws NewclassNotFoundException, RepositoryException;

	public boolean isThere(int idEntity) throws RepositoryException;
	
	public int getNewclassLastId() throws RepositoryException;
	
	public List<Newclass> getNewclassById(int idActivity) throws RepositoryException;

}