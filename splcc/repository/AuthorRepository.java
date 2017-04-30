//#if ${InsertAuthors} == "T"
package {{systemName|lower}}.ev.repository;

import java.util.List;

import {{systemName|lower}}.ev.data.Author;
import {{systemName|lower}}.ev.exception.AuthorNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;

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