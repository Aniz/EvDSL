
package rise.splcc.exception;

public class UserAlreadyInsertedException extends Exception {

	private int idUser;

	public UserAlreadyInsertedException(int idUser) {
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