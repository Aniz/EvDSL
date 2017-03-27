package rise.splcc.exception;

public class RegistrationNotFoundException extends Exception {

    private int idRegistration;

    public RegistrationNotFoundException(int idRegistration){
        super(EXC_NOT_FOUND);
        this.idRegistration = idRegistration;
    }

    public int getidRegistration(){
        return idRegistration;
    }

}
