package riseevents.ev.exception;

public class RegistrationNotFoundException extends Exception {

    private int idRegistration;

    public RegistrationNotFoundException(int idRegistration){
        super(ExceptionMessages.EXC_NOT_FOUND);
        this.idRegistration = idRegistration;
    }

    public int getidRegistration(){
        return idRegistration;
    }

}