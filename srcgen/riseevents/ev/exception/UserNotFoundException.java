
package riseevents.ev.exception;

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