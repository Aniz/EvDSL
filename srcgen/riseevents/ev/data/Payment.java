package riseevents.ev.data;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.itextpdf.text.Element;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class Payment {
	
	public enum StatusPayment{
		Parcial, Completo, Incompleto
	}

	private int idPayment;
	private int idRegistration;
	private StatusPayment status;
	private String date;
	private float value;
	private Blob attachment;
	private String barcode;
	//generated By DSL
	private int aaaa;
	private String bbbb;
	//
	//generated By DSL
	private TypePayment typePayment;
	public enum TypePayment{
			Avista,
			Debito,
			Credito
	
	}
	//

	//generated By DSL
	public TypePayment getTypePayment(){
		return this.typePayment;
	}
	public void setTypePayment(TypePayment new_value){
		this.typePayment = new_value;
	}
	//
	
	//generated By DSL
  	public int getAaaa(){
    	return this.aaaa;
  	}
	public void setAaaa(int new_value){
    	this.aaaa = new_value;
  	}
  	public String getBbbb(){
    	return this.bbbb;
  	}
	public void setBbbb(String new_value){
    	this.bbbb = new_value;
  	}
	//

	public int getIdPayment() {
		return idPayment;
	}
	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}
	public int getIdRegistration() {
		return idRegistration;
	}
	public void setIdRegistration(int idRegistration) {
		this.idRegistration = idRegistration;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Blob getAttachment() {
		return attachment;
	}
	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public StatusPayment getStatus() {
		return status;
	}
	public void setStatus(StatusPayment status) {
		this.status = status;
	}
	public float  getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}

	public String toString(){
		return "Payment Id:"+ idPayment + "\nRegistration Id:" + idRegistration + "\nStatus:" + status.toString() + "\nDate:" + date + "\nValue:" + value 
    	+ "\nPayment:" + aaaa.toString() 
    	+ "\nPayment:" + bbbb.toString() 
		+ "\nPayment:" + typePayment.toString() 
 
		+ "\nBarcode:" + barcode;
	}
	public void startarAcaoTypePayment(Payment payment) throws DocumentException, IOException{
			//generated By DSL
			if(payment.typePayment.equals("Avista")){
		 		JFileChooser local = new JFileChooser();  
				local.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
		        local.setDialogTitle("Escolha um local para salvar o Boleto");  
		        
		        local.setFileHidingEnabled(false);
		        int res = local.showSaveDialog(null);  
		        
		        if (res == JFileChooser.APPROVE_OPTION) {
					String caminho = String.valueOf(local.getSelectedFile());
					gerarBoleto(payment);
		        }
			}
			//
	}
	
	//generated By DSL
	public boolean gerarBoleto(Payment payment) throws DocumentException, IOException {
        	Document documento = new Document();
     
            OutputStream outputStream = new FileOutputStream(payment.idPayment + "_Boleto", true);
            PdfWriter.getInstance(documento, outputStream);
            documento.open();//abre o arquivo
            //adiciona o texto
           
            Font font = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, new Color(120, 200, 200)); //classe lowagie
            Paragraph paragrafo = new Paragraph("Boleto", font);
            paragrafo.setAlignment(Element.ALIGN_CENTER);
            paragrafo.setSpacingAfter(30);
            documento.add(paragrafo);
            font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
            Paragraph paragrafo2 = new Paragraph("Declaramos para os devidos fins de direito, que o pagamento de numero"
                    + payment.idPayment + " codigo de barra " + payment.barcode + " valor " + payment.value + "relativo ao numero de registro em evento "
                    + payment.idRegistration + " foi pago na data " + payment.date, font);
            paragrafo2.setAlignment(Element.ALIGN_JUSTIFIED);
            paragrafo2.setSpacingAfter(20);
            documento.add(paragrafo2);
            
            System.out.println("Boleto criado com sucesso!");
            JOptionPane.showMessageDialog(null, "Boleto criado com sucesso!", null,
					JOptionPane.INFORMATION_MESSAGE);
            
            documento.close();
            outputStream.close();
            outputStream.flush();
            return true;
    }
    //
	
}