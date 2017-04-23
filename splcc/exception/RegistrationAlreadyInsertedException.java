package {{systemName|lower}}.ev.exception;

public class RegistrationAlreadyInsertedException extends Exception {

	private int idRegistration;

	public RegistrationAlreadyInsertedException(int idRegistration) {
		super(EXC_ALREADY_EXISTS);
		this.idRegistration = idRegistration;
	}

	public int getidRegistration() {
		return idRegistration;
	}
}