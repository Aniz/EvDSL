//#if ${Speaker} == "T"
package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

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
		return ExceptionMessages,EXC_ALREADY_EXISTS;
	}
}
//#endif