//#if ${RegistrationSpeakerActivity} == "T"
package rise.splcc.exception;

public class ActivitySpeakerNotFoundException extends Exception {

    private int idActivity;

    public ActivitySpeakerNotFoundException(int idActivity){
        super(EXC_NOT_FOUND);
        this.idActivity = idActivity;
    }

    public int getidActivity(){
        return idActivity;
    }

}
//#endif