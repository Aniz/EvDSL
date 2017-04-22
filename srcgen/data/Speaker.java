//#if ${Speaker} == "T"
package rise.splcc.data;

public class Speaker extends User{
	private String biography;
	//generated By DSL
	private int nacionalidade;
	//
	//generated By DSL
	private TypeSpeaker typeSpeaker;
	public enum TypeSpeaker{
			GeneralChair
	
	}
	//

	//generated By DSL
	public TypeSpeaker getTypeSpeaker(){
		return this.typeSpeaker;
	}
	public void setTypeSpeaker(TypeSpeaker new_value){
		this.typeSpeaker = new_value;
	}
	//
	
	//generated By DSL
  	public int getNacionalidade(){
    	return this.nacionalidade;
  	}
	public void setNacionalidade(int new_value){
    	this.nacionalidade = new_value;
  	}
	//

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	@Override
	public boolean equals(Object obj) { 
		if (obj == this) { 
			return true; 
		} 
		if (obj == null || obj.getClass() != this.getClass()) { 
			return false; 
		} 
		
		Speaker speaker = (Speaker) obj; 
		
		return (this.getIdUser() == ((Speaker)obj).getIdUser() && 
				   this.getPassword().equals(((Speaker)obj).getPassword()) && 
				   this.getNameUser().equals(((Speaker)obj).getNameUser()) &&
				   this.getTypeUser() == ((Speaker)obj).getTypeUser() &&
				   this.getEmail().equals(((Speaker)obj).getEmail())&&
				   this.getBiography().equals(((Speaker)obj).getBiography()));
	}
	
	public String toString(){
		//return "User Id:"+ getIdUser() + "\nName:" + getNameUser() + "\nType:" + getTypeUser().toString() + "\nEmail:" + getEmail()  + "\nBiography:" + biography;
		//return "\nBiography:" + biography;
		return super.toString() + "\nBiography:" + biography;
	}
}
//#endif