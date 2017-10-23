//#if ${RegistrationUserActivity} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

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