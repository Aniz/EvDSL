//#if ${InsertAuthors} == "T"
package evco.ev.repository;

import java.util.List;

import evco.ev.data.Author;
import evco.ev.exception.AuthorNotFoundException;
import evco.ev.exception.RepositoryException;

public interface AuthorRepository {
	
	public void insert(Author author) throws RepositoryException;

	public void remove(int idAuthor) throws AuthorNotFoundException, RepositoryException;

	public Author search(int idAuthor) throws AuthorNotFoundException, RepositoryException;
	
	public List<Author> getAuthorList() throws RepositoryException;

	public void update(Author author) throws AuthorNotFoundException, RepositoryException;

	public boolean isThere(int idAuthor) throws RepositoryException;
	
	public int getAuthorLastId() throws RepositoryException;


}
//#endif