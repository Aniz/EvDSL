//#if ${Speaker} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class SpeakerNotFoundException extends Exception {

    private int idSpeaker;

    public SpeakerNotFoundException(int idSpeaker){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idSpeaker = idSpeaker;
    }

    public int getidSpeaker(){
        return idSpeaker;
    }

}
//#endif