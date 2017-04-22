
package rise.splcc.exception;

public class UserNotFoundException extends Exception {

    private int idUser;

    public UserNotFoundException(int idUser){
        super(EXC_NOT_FOUND);
        this.idUser = idUser;
    }

    public int getidUser(){
        return idUser;
    }

}