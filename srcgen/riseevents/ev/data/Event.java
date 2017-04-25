package riseevents.ev.data;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Element;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class Event {
	private int idEvent;
	private String eventName;
	private String period;
	private String place;
	private String institution;
	private String sponsors;
    //generated By DSL
    private String link;
    //
    //generated By DSL
    private TypeEvent typeEvent;
    public enum TypeEvent{
            Associado,
            Profissional,
            Estudante
    
    }
    //

    //generated By DSL
    public TypeEvent getTypeEvent(){
        return this.typeEvent;
    }
    public void setTypeEvent(TypeEvent new_value){
        this.typeEvent = new_value;
    }
    //
 
    //generated By DSL
    public String getLink(){
        return this.link;
    }
    public void setLink(String new_value){
        this.link = new_value;
    }
    //

	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getSponsors() {
		return sponsors;
	}
	public void setSponsors(String sponsors) {
		this.sponsors = sponsors;
	}

//	public String getEventNameAndId(){
//		return this.idEvent +" - "+ this.eventName;
//	}
	
	public String toString(){
		return "Event Name:"+ eventName + "\nPeriod:" + period + "\nPlace:" + place+ "\nInstitution:" + institution + "\nSponsors:" + sponsors
        //generated By DSL
        + "Link:" + link
        + "TypeEvent:" + typeEvent        
    ;
	}

    //generated By DSL    
	public void frequencyPerEvent(List<String> ParticipantsPerEvent) throws DocumentException, IOException{
        Document documento = new Document();
        String nomePasta = "doc_frequencia";
        if (!new File(nomePasta).exists()) { // Verifica se o diretório existe.   
            (new File(nomePasta)).mkdir();   // Cria o diretório   
        }
        File arquivo = new File(nomePasta + "\\" + ".Frequencia_" + eventName +".pdf");
        String nomeArquivoCertificado = arquivo.getPath();
       
            OutputStream outputStream;
			outputStream = new FileOutputStream(nomeArquivoCertificado, true);
			
            PdfWriter.getInstance(documento, outputStream);
            //documento.setPageSize(PageSize.A4.rotate());
            documento.open();//abre o arquivo
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            Date date = new Date();;

            //adiciona o texto
            Font font = new Font(Font.TIMES_ROMAN, 36, Font.BOLD, new Color(0, 0, 255)); //classe lowagie
            Paragraph cabecalho = new Paragraph(eventName + " \ndia - " + dateFormat.format(date), font);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            cabecalho.setSpacingAfter(50);
            documento.add(cabecalho);

            for (int i = 0; i < ParticipantsPerEvent.size(); i++) {
                font = new Font(Font.TIMES_ROMAN, 18, Font.NORMAL); //classe lowagie
                int tamString = ParticipantsPerEvent.get(i).length();
                int qntUnsderLine = 50 - tamString;
                String underLi = "_";
                for (int j = 1; j < qntUnsderLine; j++) {
                    underLi = underLi + "_";
                }
                Paragraph paragrafo2 = new Paragraph(ParticipantsPerEvent.get(i) + underLi, font);
                paragrafo2.setAlignment(Element.ALIGN_LEFT);
                paragrafo2.setSpacingAfter(20);
                documento.add(paragrafo2);
            }
            documento.close();
            outputStream.close();
            outputStream.flush();

        
	}
    //
}