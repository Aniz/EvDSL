//#if ${Organizer} == "T"
package riseevents.ev.exception;

public class OrganizerNotFoundException extends Exception {

    private int idUser;

    public OrganizerNotFoundException(int idUser){
        super(EXC_NOT_FOUND);
        this.idUser = idUser;
    }

    public int getidUser(){
        return idUser;
    }

}
//#endif