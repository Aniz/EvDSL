//#if ${RegistrationUserActivity} == "T"
package riseevents.ev.exception;
import riseevents.ev.util.ExceptionMessages;

public class ActivityUserNotFoundException extends Exception {

    private int idUser;

    public ActivityUserNotFoundException(int idUser){
        super (ExceptionMessages.EXC_NOT_FOUND);
        this.idUser = idUser;
    }

    public int getidUser(){
        return idUser;
    }

}
//#endif