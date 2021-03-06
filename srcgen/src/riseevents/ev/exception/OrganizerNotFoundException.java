//#if ${Organizer} == "T"
package riseevents.ev.exception;
import riseevents.ev.util.ExceptionMessages;

public class OrganizerNotFoundException extends Exception {

    private int idUser;

    public OrganizerNotFoundException(int idUser){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idUser = idUser;
    }

    public int getidUser(){
        return idUser;
    }

}
//#endif