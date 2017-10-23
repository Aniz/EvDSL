//#if ${InsertAuthors} == "T"
package evco.ev.business;

import java.util.List;

import evco.ev.data.Author;
import evco.ev.exception.AuthorAlreadyInsertedException;
import evco.ev.exception.AuthorNotFoundException;
import evco.ev.exception.RepositoryException;
import evco.ev.repository.AuthorRepository;

public class AuthorControl {
	 private AuthorRepository authorList;
		
		public AuthorControl(AuthorRepository repository){
			this.authorList = repository;
		}
		public void insert(Author author) throws AuthorAlreadyInsertedException, RepositoryException{
			if (author != null) {
	            if (!authorList.isThere(author.getIdAuthor())) {
	                authorList.insert(author);
	            } else {
	                throw new AuthorAlreadyInsertedException(author.getIdAuthor());
	            }
	        } else {
	            throw new IllegalArgumentException();
	        }
		}
		public void remove(int idAuthor) throws AuthorAlreadyInsertedException, RepositoryException, AuthorNotFoundException{
			authorList.remove(idAuthor);
		}
		public void update(Author author) throws AuthorAlreadyInsertedException, RepositoryException, AuthorNotFoundException{
			authorList.update(author);
		}
		public Author search(int idAuthor) throws AuthorAlreadyInsertedException, RepositoryException, AuthorNotFoundException{
			return authorList.search(idAuthor);
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