//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class ActivityNotFoundException extends Exception {

    private int idActivity;

    public ActivityNotFoundException(int idActivity){
        super (ExceptionMessages.EXC_NOT_FOUND);
        this.idActivity = idActivity;
    }

    public int getidActivity(){
        return idActivity;
    }

}
//#endif