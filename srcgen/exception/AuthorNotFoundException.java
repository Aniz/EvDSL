//#if ${InsertAuthors} == "T"
package rise.splcc.exception;

public class AuthorNotFoundException extends Exception {

    private int idAuthor;

    public AuthorNotFoundException(int idAuthor){
        super(EXC_NOT_FOUND);
        this.idAuthor = idAuthor;
    }

    public int getidAuthor(){
        return idAuthor;
    }

}
//#endif