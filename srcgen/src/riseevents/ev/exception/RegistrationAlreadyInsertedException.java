package riseevents.ev.exception;
import riseevents.ev.util.ExceptionMessages;

public class RegistrationAlreadyInsertedException extends Exception {

	private int idRegistration;

	public RegistrationAlreadyInsertedException(int idRegistration) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idRegistration = idRegistration;
	}

	public int getidRegistration() {
		return idRegistration;
	}
}