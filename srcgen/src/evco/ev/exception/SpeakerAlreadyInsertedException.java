//#if ${Speaker} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class SpeakerAlreadyInsertedException extends Exception {

	private int idUser;

	public SpeakerAlreadyInsertedException(int idUser) {
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
//#endif