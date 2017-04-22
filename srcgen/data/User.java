
package rise.splcc.data;

public class User {
	
	private int idUser;
	private String password;
	private String nameUser;
	private TypeUser typeUser;
	private String email;
	private String filiation;
	//generated By DSL
	private int contato;
	//
	//generated By DSL
	private TypeUser typeUser;
	public enum TypeUser{
			Associado,
			Profissional,
			Estudante
	
	}
	//

	//generated By DSL
	public TypeUser getTypeUser(){
		return this.typeUser;
	}
	public void setTypeUser(TypeUser new_value){
		this.typeUser = new_value;
	}
	//
	
	//generated By DSL
  	public int getContato(){
    	return this.contato;
  	}
	public void setContato(int new_value){
    	this.contato = new_value;
  	}
	//

	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFiliation() {
		return filiation;
	}
	public void setFiliation(String filiation) {
		this.filiation = filiation;
	}
	public String toString(){
		return "User Id:"+ idUser + "\nName:" + nameUser + "\nFiliation:" + filiation + "\nType:" + typeUser.toString() + "\nEmail:" + email;
	}
	
}
