//#if ${RegistrationOrganizerActivity} == "T"
package {{systemName|lower}}.ev.exception;
import {{systemName|lower}}.ev.util.ExceptionMessages;

public class ActivityOrganizerAlreadyInsertedException extends Exception {

	private int idActivity;

	public ActivityOrganizerAlreadyInsertedException(int idActivity) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idActivity = idActivity;
	}

	public int getidActivity() {
		return idActivity;
	}
}
//#endif