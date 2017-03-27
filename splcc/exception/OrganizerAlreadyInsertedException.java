//#if ${Organizer} == "T"
package rise.splcc.exception;

public class OrganizerAlreadyInsertedException extends Exception {

	private int idUser;

	public OrganizerAlreadyInsertedException(int idUser) {
		super(EXC_ALREADY_EXISTS);
		this.idUser = idUser;
	}

	public int getidUser() {
		return idUser;
	}
}
//#endif