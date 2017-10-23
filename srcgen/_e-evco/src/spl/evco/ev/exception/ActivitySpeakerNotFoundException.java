//#if ${RegistrationSpeakerActivity} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class ActivitySpeakerNotFoundException extends Exception {

    private int idActivity;

    public ActivitySpeakerNotFoundException(int idActivity){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idActivity = idActivity;
    }

    public int getidActivity(){
        return idActivity;
    }

}
//#endif