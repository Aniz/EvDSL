//#if ${RegistrationOrganizerActivity} == "T"
package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

public class ActivityOrganizerNotFoundException extends Exception {

    private int idActivity;

    public ActivityOrganizerNotFoundException(int idActivity){
        super (ExceptionMessages.EXC_NOT_FOUND);
        this.idActivity = idActivity;
    }

    public int getidActivity(){
        return idActivity;
    }

}
//#endif