//#if ${Speaker} == "T"
package rise.splcc.exception;

public class SpeakerNotFoundException extends Exception {

    private int idSpeaker;

    public SpeakerNotFoundException(int idSpeaker){
        super(EXC_NOT_FOUND);
        this.idSpeaker = idSpeaker;
    }

    public int getidSpeaker(){
        return idSpeaker;
    }

}
//#endif