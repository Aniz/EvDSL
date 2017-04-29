//#if ${InsertAuthors} == "T"
package {{systemName|lower}}.ev.business;

import java.util.List;

import {{systemName|lower}}.ev.data.Author;
import {{systemName|lower}}.ev.exception.AuthorAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.AuthorNotFoundException;
import {{systemName|lower}}.ev.exception.RepositoryException;
import {{systemName|lower}}.ev.repository.AuthorRepository;

public class AuthorControl {
	 private AuthorRepository authors;
		
		public AuthorControl(AuthorRepository repository){
			this.authors = repository;
		}
		{% if 'Insert' in data.commands %}
		public void insert(Author author) throws AuthorAlreadyInsertedException, RepositoryException{
			if (author != null) {
	            if (!authors.isThere(author.getIdAuthor())) {
	                authors.insert(author);
	            } else {
	                throw new AuthorAlreadyInsertedException(author.getIdAuthor());
	            }
	        } else {
	            throw new IllegalArgumentException();
	        }
		}
		{% endif %}
		{% if 'Remove' in data.commands %}
		public void remove(int idAuthor) throws AuthorAlreadyInsertedException, RepositoryException, AuthorNotFoundException{
			authors.remove(idAuthor);
		}
		{% endif %}
		{% if 'Update' in data.commands %}
		public void update(Author author) throws AuthorAlreadyInsertedException, RepositoryException, AuthorNotFoundException{
			authors.update(author);
		}
		{% endif %}
		{% if 'Search' in data.commands %}
		public Author search(int idAuthor) throws AuthorAlreadyInsertedException, RepositoryException, AuthorNotFoundException{
			return authors.search(idAuthor);
		}
		{% endif %}
		public boolean isThere(int idAuthor) throws RepositoryException {
			return authors.isThere(idAuthor);
		}

		public List<Author> getAuthors() throws RepositoryException {
			return authors.getAuthors();  
		}
		public int getAuthorLastId() throws RepositoryException{
			return authors.getAuthorLastId();
		}
	}
//#endif