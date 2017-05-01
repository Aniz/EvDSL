//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package riseevents.ev.data;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import com.itextpdf.text.Element;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class CheckingCopy {

	private int idCheckingCopy;
	private int idUser;
	private int idRegistration;
	private String dateOfIssue;
	//generated By DSL
	private String banana;
	private int anana;
	//
	//generated By DSL
	private TypeCheckingCopy typeCheckingCopy;
	public enum TypeCheckingCopy{
			Atestado,
			Certificado
	
	}
	//

	//generated By DSL
	public TypeCheckingCopy getTypeCheckingCopy(){
		return this.typeCheckingCopy;
	}
	public void setTypeCheckingCopy(TypeCheckingCopy new_value){
		this.typeCheckingCopy = new_value;
	}
	//
	
	//generated By DSL
  	public String getBanana(){
    	return this.banana;
  	}
	public void setBanana(String new_value){
    	this.banana = new_value;
  	}
  	public int getAnana(){
    	return this.anana;
  	}
	public void setAnana(int new_value){
    	this.anana = new_value;
  	}
	//

	public int getIdCheckingCopy() {
		return idCheckingCopy;
	}
	public void setIdCheckingCopy(int idCheckingCopy) {
		this.idCheckingCopy = idCheckingCopy;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdRegistration() {
		return idRegistration;
	}
	public void setIdRegistration(int idRegistration) {
		this.idRegistration = idRegistration;
	}
	public String getDateOfIssue() {
		return dateOfIssue;
	}
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	public String toString(){
		return "CheckingCopy Id:"+ idCheckingCopy + "\nRegistration Id:" + idRegistration + "\nUser Id:" + idUser + "\nDate of Issue:" + dateOfIssue
			//generated By DSL
			+ "Banana:" + banana
			+ "Anana:" + anana
			+ "TypeCheckingCopy:" + typeCheckingCopy 		
		;
	}
	
	public boolean emitirAtestado(String nome, String evento, String periodo) {
        Document documento = new Document();
     
        try {
            OutputStream outputStream = new FileOutputStream(nome + "_Atestado", true);
            PdfWriter.getInstance(documento, outputStream);
            documento.open();//abre o arquivo
            //adiciona o texto
            Font font = new Font(Font.TIMES_ROMAN, 36, Font.BOLD, new Color(120, 100, 200)); //classe lowagie
            Paragraph cabecalho = new Paragraph(evento, font);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(50);
            documento.add(cabecalho);
            font = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, new Color(120, 200, 200)); //classe lowagie
            Paragraph paragrafo = new Paragraph("Atestado", font);
            paragrafo.setAlignment(Element.ALIGN_CENTER);
            paragrafo.setSpacingAfter(30);
            documento.add(paragrafo);
            font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
            Paragraph paragrafo2 = new Paragraph("Declaramos para os devidos fins de direito, que "
                    + nome + " esteve presente nas atividades deste evento no dia "
                    + periodo + "", font);
            paragrafo2.setAlignment(Element.ALIGN_JUSTIFIED);
            paragrafo2.setSpacingAfter(20);
            documento.add(paragrafo2);
            
            System.out.println("Atestado criado com sucesso!");
            JOptionPane.showMessageDialog(null, "Atestado criado com sucesso!", null,
					JOptionPane.INFORMATION_MESSAGE);
            
            documento.close();
            outputStream.close();
            outputStream.flush();
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        } catch (com.lowagie.text.DocumentException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
    }
	

	private String getTexto(String evento, String periodo, String atividade) {
		 String lista_minicursos = "\n\n";
		 String texto = "";
			//generated By DSL
			if (atividade.equals("MiniCurso")){
				 texto = "Participou de um Mini " + " no(a) " + evento + ", realizado(a) no periodo de " + periodo;
		            texto += lista_minicursos;
			}
			//
			//generated By DSL
			if (atividade.equals("Tutorial")){
				 texto = "Participou de um Tutorial " + " no(a) " + evento + ", realizado(a) no periodo de " + periodo;
		            texto += lista_minicursos;
			}
			//
			//generated By DSL
			if (atividade.equals("Painel")){
				 texto = "Participou de um Painel " + " no(a) " + evento + ", realizado(a) no periodo de " + periodo;
		            texto += lista_minicursos;
			}
			//
			//generated By DSL
			if (atividade.equals("Workshop")){
				 texto = "Participou de um Workshop " + " no(a) " + evento + ", realizado(a) no periodo de " + periodo;
		            texto += lista_minicursos;
			}
			//
		return texto;
	}	 
}
//#endif