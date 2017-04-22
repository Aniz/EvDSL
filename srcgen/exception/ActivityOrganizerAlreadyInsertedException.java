//#if ${RegistrationOrganizerActivity} == "T"
package rise.splcc.exception;

public class ActivityOrganizerAlreadyInsertedException extends Exception {

	private int idActivity;

	public ActivityOrganizerAlreadyInsertedException(int idActivity) {
		super(EXC_ALREADY_EXISTS);
		this.idActivity = idActivity;
	}

	public int getidActivity() {
		return idActivity;
	}
}
//#endif