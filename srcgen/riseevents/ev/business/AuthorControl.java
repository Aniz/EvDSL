//#if ${InsertAuthors} == "T"
package riseevents.ev.business;

import java.util.List;

import riseevents.ev.data.Author;
import riseevents.ev.exception.AuthorAlreadyInsertedException;
import riseevents.ev.exception.AuthorNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.repository.AuthorRepository;

public class AuthorControl {
	 private AuthorRepository authorList;
		
		public AuthorControl(AuthorRepository repository){
			this.authorList = repository;
		}
		public boolean isThere(int idAuthor) throws RepositoryException {
			return authorList.isThere(idAuthor);
		}

		public List<Author> getAuthorList() throws RepositoryException {
			return authorList.getAuthorList();  
		}
		public int getAuthorLastId() throws RepositoryException{
			return authorList.getAuthorLastId();
		}
	}
//#endif