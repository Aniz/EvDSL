
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class UserAlreadyInsertedException extends Exception {

	private int idUser;

	public UserAlreadyInsertedException(int idUser) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idUser = idUser;
	}

	public int getidUser() {
		return idUser;
	}
	
	public String toString(){
		return ExceptionMessages.EXC_ALREADY_EXISTS;
	}
}