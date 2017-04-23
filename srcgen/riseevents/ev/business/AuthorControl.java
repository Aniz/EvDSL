//#if ${InsertAuthors} == "T"
package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.Author;
import riseevents.ev.exception.AuthorAlreadyInsertedException;
import riseevents.ev.exception.AuthorNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.AuthorRepository;

public class AuthorControl {
	 private AuthorRepository authors;
		
		public AuthorControl(AuthorRepository repository){
			this.authors = repository;
		}
		
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

		public void remove(int idAuthor) throws AuthorAlreadyInsertedException, RepositoryException, AuthorNotFoundException{
			authors.remove(idAuthor);
		}
		
		public void update(Author author) throws AuthorAlreadyInsertedException, RepositoryException, AuthorNotFoundException{
			authors.update(author);
		}
		
		public Author search(int idAuthor) throws AuthorAlreadyInsertedException, RepositoryException, AuthorNotFoundException{
			return authors.search(idAuthor);
		}

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