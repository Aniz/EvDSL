// Autogenerated by EvDSL
package riseevents.ev.data;
import riseevents.ev.util.LibraryOfDSL;

public class NewOption {
  private int idNewOption;


  private TypeNewOption typeNewOption;
  public enum TypeNewOption{
      Atestado,
      Certificado
  
  }

  public TypeNewOption getTypeNewOption(){
    return this.typeNewOption;
  }
  public void setTypeNewOption(TypeNewOption new_value){
    this.typeNewOption = new_value;
  }
 

  public int getIdNewOption(){
    return this.idNewOption;
  }
  public void setIdNewOption(int new_value){
    this.idNewOption = new_value;
  }
}