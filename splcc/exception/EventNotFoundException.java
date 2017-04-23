package {{systemName|lower}}.ev.exception;

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
