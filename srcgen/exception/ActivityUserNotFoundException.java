//#if ${RegistrationUserActivity} == "T"
package rise.splcc.exception;

public class ActivityUserNotFoundException extends Exception {

    private int idUser;

    public ActivityUserNotFoundException(int idUser){
        super (EXC_NOT_FOUND);
        this.idUser = idUser;
    }

    public int getidUser(){
        return idUser;
    }

}
//#endif