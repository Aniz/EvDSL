package riseevents.ev.exception;

public class EventAlreadyInsertedException extends Exception {

	private int idEvent;

	public EventAlreadyInsertedException(int idEvent) {
		super(ExceptionMessages.EXC_ALREADY_EXISTS);
		this.idEvent = idEvent;
	}

	public int getidEvent() {
		return idEvent;
	}
}