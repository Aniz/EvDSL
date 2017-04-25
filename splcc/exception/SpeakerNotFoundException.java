//#if ${Speaker} == "T"
package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

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