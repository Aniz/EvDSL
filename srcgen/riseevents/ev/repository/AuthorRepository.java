//#if ${InsertAuthors} == "T"
package riseevents.ev.repository;

import java.util.List;

import riseevents.ev.data.Author;
import riseevents.ev.exception.AuthorNotFoundException;
import riseevents.ev.exception.RepositoryException;

public interface AuthorRepository {
	
	public void insert(Author author) throws RepositoryException;

	public void remove(int idAuthor) throws AuthorNotFoundException, RepositoryException;

	public Author search(int idAuthor) throws AuthorNotFoundException, RepositoryException;
	
	public List<Author> getAuthors() throws RepositoryException;

	public void update(Author author) throws AuthorNotFoundException, RepositoryException;

	public boolean isThere(int idAuthor) throws RepositoryException;
	
	public int getAuthorLastId() throws RepositoryException;


}
//#endif