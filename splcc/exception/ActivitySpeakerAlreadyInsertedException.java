//#if ${RegistrationSpeakerActivity} == "T"
package rise.splcc.exception;

public class ActivitySpeakerAlreadyInsertedException extends Exception {

	private int idActivity;

	public ActivitySpeakerAlreadyInsertedException(int idActivity) {
		super(EXC_ALREADY_EXISTS);
		this.idActivity = idActivity;
	}

	public int getidActivity() {
		return idActivity;
	}
}
//#endif