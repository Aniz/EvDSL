//#if ${Organizer} == "T"
package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

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