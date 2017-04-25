//#if ${RegistrationOrganizerActivity} == "T"
package riseevents.ev.exception;

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