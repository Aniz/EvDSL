//#if ${RegistrationSpeakerActivity} == "T"
package evco.ev.exception;
import evco.ev.util.ExceptionMessages;

public class ActivitySpeakerAlreadyInsertedException extends Exception {

	private int idActivity;

	public ActivitySpeakerAlreadyInsertedException(int idActivity) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idActivity = idActivity;
	}

	public int getidActivity() {
		return idActivity;
	}
}
//#endif