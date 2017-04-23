//#if ${Speaker} == "T"
package riseevents.ev.exception;

public class SpeakerAlreadyInsertedException extends Exception {

	private int idUser;

	public SpeakerAlreadyInsertedException(int idUser) {
		super(EXC_ALREADY_EXISTS);
		this.idUser = idUser;
	}

	public int getidUser() {
		return idUser;
	}
	
	public String toString(){
		return EXC_ALREADY_EXISTS;
	}
}
//#endif