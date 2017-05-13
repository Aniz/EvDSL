//#if ${Organizer} == "T"
package riseevents.ev.exception;
import riseevents.ev.util.ExceptionMessages;

public class OrganizerAlreadyInsertedException extends Exception {

	private int idUser;

	public OrganizerAlreadyInsertedException(int idUser) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idUser = idUser;
	}

	public int getidUser() {
		return idUser;
	}
}
//#endif