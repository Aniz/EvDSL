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