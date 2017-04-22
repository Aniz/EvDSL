//#if ${Speaker} == "T"
package rise.splcc.exception;

public class SpeakerAlreadyInsertedException extends Exception {

	private int idUser;

	public SpeakerAlreadyInsertedException(int idUser) {
		super(super(EXC_ALREADY_EXISTS);
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