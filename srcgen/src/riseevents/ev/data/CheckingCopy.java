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
			+ "TypeCheckingCopy:" + typeCheckingCopy 		
		;
	}
	
	
	public boolean emitirCertificado(String nome, String evento, String periodo, String atividade) throws DocumentException, IOException {
        Document documento = new Document();
       
            OutputStream outputStream = new FileOutputStream(nome + "_Atestado", true);
            PdfWriter.getInstance(documento, outputStream);
            documento.setPageSize(PageSize.A4.rotate());
            documento.open();//abre o arquivo

            //adiciona o texto
            Font font = new Font(Font.TIMES_ROMAN, 36, Font.BOLD, new Color(120, 100, 200)); //classe lowagie
            Paragraph cabecalho = new Paragraph(evento, font);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(50);
            documento.add(cabecalho);

            font = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, new Color(120, 200, 200)); //classe lowagie
            Paragraph paragrafo = new Paragraph("Certificado", font);
            paragrafo.setAlignment(Element.ALIGN_CENTER);
            paragrafo.setSpacingAfter(30);
            documento.add(paragrafo);

            font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
            Paragraph paragrafo2 = new Paragraph("Certificamos que", font);
            paragrafo2.setAlignment(Element.ALIGN_CENTER);
            paragrafo2.setSpacingAfter(20);
            documento.add(paragrafo2);

            font = new Font(Font.TIMES_ROMAN, 22, Font.BOLD); //classe lowagie
            Paragraph paragrafo3 = new Paragraph(nome, font);
            paragrafo3.setAlignment(Element.ALIGN_CENTER);
            paragrafo3.setSpacingAfter(20);
            documento.add(paragrafo3);

            font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
            Paragraph paragrafo4 = new Paragraph(getTexto(evento, periodo, atividade), font);
            paragrafo4.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(paragrafo4);
            
            System.out.println("Certificado criado com sucesso!");
            JOptionPane.showMessageDialog(null, "Certificado criado com sucesso!", null,
					JOptionPane.INFORMATION_MESSAGE);
            
            documento.close();
            outputStream.close();
            outputStream.flush();           
            return true;
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