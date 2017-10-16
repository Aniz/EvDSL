//#if ${RegistrationSpeakerActivity} == "T"
package riseevents.ev.exception;
import riseevents.ev.util.ExceptionMessages;

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