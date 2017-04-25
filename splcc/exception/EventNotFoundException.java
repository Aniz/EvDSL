package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

public class EventNotFoundException extends Exception {

    private int idEvent;

    public EventNotFoundException(int idEvent){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idEvent = idEvent;
    }

    public int getidEvent(){
        return idEvent;
    }

}
