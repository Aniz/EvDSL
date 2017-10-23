
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class UserNotFoundException extends Exception {

    private int idUser;

    public UserNotFoundException(int idUser){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idUser = idUser;
    }

    public int getidUser(){
        return idUser;
    }

}