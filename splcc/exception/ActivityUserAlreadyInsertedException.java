//#if ${RegistrationUserActivity} == "T"
package {{systemName|lower}}.ev.exception;

public class ActivityUserAlreadyInsertedException extends Exception {

	private int idActivity;

	public ActivityUserAlreadyInsertedException(int idActivity) {
		super(EXC_ALREADY_EXISTS);
		this.idActivity = idActivity;
	}

	public int getidActivity() {
		return idActivity;
	}
}
//#endif