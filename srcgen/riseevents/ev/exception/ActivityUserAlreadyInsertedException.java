//#if ${RegistrationUserActivity} == "T"
package riseevents.ev.exception;

public class ActivityUserAlreadyInsertedException extends Exception {

	private int idActivity;

	public ActivityUserAlreadyInsertedException(int idActivity) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idActivity = idActivity;
	}

	public int getidActivity() {
		return idActivity;
	}
}
//#endif