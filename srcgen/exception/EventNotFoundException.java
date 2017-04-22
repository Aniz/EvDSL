package rise.splcc.exception;

public class EventNotFoundException extends Exception {

    private int idEvent;

    public EventNotFoundException(int idEvent){
        super(EXC_NOT_FOUND);
        this.idEvent = idEvent;
    }

    public int getidEvent(){
        return idEvent;
    }

}