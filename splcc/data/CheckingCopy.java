//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package {{systemName|lower}}.ev.data;

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
{% if data.option.properties|length > 0 %}
	//generated By DSL
{% for property in data.option.properties %}
	private {{property.type|javatype}} {{property.name}};
{% endfor %}
	//
{% endif %}
{% if data.option.categories|length > 0 %}
	//generated By DSL
	private Type{{data.option.entity}} type{{data.option.entity}};
	private enum Type{{data.option.entity}}{
	{% for category in data.option.categories %}
		{% if loop.last %}
			{{category.name}}
		{% else %}
			{{category.name}},
		{% endif %}
	{% endfor %}	
	}
	//

	//generated By DSL
	public Type{{data.option.entity}} getType{{data.option.entity}}(){
		return this.type{{data.option.entity}};
	}
	public void setType{{data.option.entity|capitalize}}(Type{{data.option.entity}} new_value){
		this.type{{data.option.entity}} = new_value;
	}
	//
{% endif %}	
{% if data.option.properties|length > 0 %}
	//generated By DSL
{% for property in data.option.properties %}
  	public {{property.type|javatype}} get{{property.name|capitalize}}(){
    	return this.{{property.name}};
  	}
	public void set{{property.name|capitalize}}({{property.type|javatype}} new_value){
    	this.{{property.name}} = new_value;
  	}
{% endfor %}
	//
{% endif %}

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
		{% if data.option.properties|length > 0 %}
			//generated By DSL
			{% for property in data.option.properties %}
			+ "{{property.name|capitalize}}:" + {{property.name}}
			{% endfor %}
		{% endif %}
		{% if data.option.categories|length > 0 %}
			+ "Type{{data.option.entity}}:" + type{{data.option.entity}} 		
		{% endif %}
		;
	}
	
	{% if 'reportsListofAuthors' in data.statments %}
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
	{% endif %}
	
	{% if 'checkingCopyCertificado' in data.statments %}
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
	{% endif %}

	private String getTexto(String evento, String periodo, String atividade) {
		 String lista_minicursos = "\n\n";
		 String texto = "";
		 {% for category in extraData.option.categories %}
			 {% if category.alias != 'hidden' %}
			//generated By DSL
			if (atividade.equals("{{category.name}}")){
				 texto = "Participou de um {% if category.alias %}{{category.alias}}{% else %}{{category.name}}{% endif %} " + " no(a) " + evento + ", realizado(a) no periodo de " + periodo;
		            texto += lista_minicursos;
			}
			//
			{% endif %}
		{% endfor %}
		return texto;
	}	 
}
//#endif