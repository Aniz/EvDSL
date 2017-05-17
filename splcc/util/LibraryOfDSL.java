package {{systemName|lower}}.ev.util;

{% for key,value in data.items() %}
import {{systemName|lower}}.ev.data.{{key}};
import {{systemName|lower}}.ev.business.{{key}}Control;
import {{systemName|lower}}.ev.exception.{{key}}AlreadyInsertedException;
import {{systemName|lower}}.ev.exception.{{key}}NotFoundException;
import {{systemName|lower}}.ev.repository.{{key}}Repository;
import {{systemName|lower}}.ev.repository.{{key}}RepositoryBDR;
{% endfor %}
{% for key in extraData %}
import {{systemName|lower}}.ev.data.{{key}};
import {{systemName|lower}}.ev.business.{{key}}Control;
import {{systemName|lower}}.ev.exception.{{key}}AlreadyInsertedException;
import {{systemName|lower}}.ev.exception.{{key}}NotFoundException;
import {{systemName|lower}}.ev.repository.{{key}}Repository;
import {{systemName|lower}}.ev.repository.{{key}}RepositoryBDR;
{% endfor %}

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import java.util.ArrayList;
import java.util.List;

public class LibraryOfDSL {

	public Boolean returnValue(Boolean value){
		if(value){
			return true;
		}
		else {
			return false;
		}	
	}	

	{% if "Author" in data and 'Assignment' in extraData and 'interestConflict' in statments  %}
	public static Boolean automaticInterestConflict(Author authorSubmission,  User usersub, User user){
		
		String authorFiliation = null;
		String reviewerFiliation = null;
		String userFiliation = null;
				
		authorFiliation = authorSubmission.getFiliation();
		reviewerFiliation = user.getFiliation();
		userFiliation = user.getFiliation();
		
		if(authorFiliation.equals(reviewerFiliation)){
			return true;
		}
		if(usersub.equals(userFiliation)){
			return true;
		}
		else {
			return false;
		}
	}
	{% endif %}
	
	{% if "Review" in extraData and "User" in data %}
	{% if 'notificationsDeadline' in statments or 'notificationsPaperAssignemnt' in statments or 'notificationsAceptanceRejection' in statments %}
	public static void sendNotification(User user, Review review) throws EmailException{

		{% if 'notificationsDeadline' in statments %}
		// esta classe eh chamada logo apos o insert do assignment
		String assunto = "Prazo de entrega de Rivisao";
		String mensagem = "O prazo de entrega é ate 15 dias antes da data oficial de Inicio do evento. Seu ID para cadastrar a revisao é:" + review.getIdReview() + " Favor usar este ID!" ;
		String emailDestino = user.getEmail();
		{% endif %}
	
		{% if 'notificationsAceptanceRejection' in statments %}
		String assunto2 = "Resultado Revisao Papper!";
		String mensagem2 = "Seu Papper esta sendo revisado. O resultado sera encaminhado via email.";
		String emailDestino2 = user.getEmail();
		{% endif %}
		
		{% if 'notificationsPaperAssignemnt' in statments %}
		String assunto3 = "Pappers para revisao";
		String mensagem3 = "Seguem em anexos pappers para revisao!";
		String emailDestino3 = user.getEmail();
		{% endif %}
	
		
		{% if 'notificationsDeadline' in statments %}
		SimpleEmail email = new SimpleEmail(); 
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email.addTo(emailDestino, user.getNameUser()); //destinat�rio 
		email.setFrom("{{systemEmail}}.gmail.com", "Gerenciador de Eventos Rise"); // remetente 
		email.setSubject(assunto); // assunto do e-mail 
		email.setMsg(mensagem); //conteudo do e-mail
		
		email.setAuthentication("{{systemEmail}}.gmail.com", "{{systemPassword}}");
		email.setSslSmtpPort( "465" ); //578 ou 465
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		email.setStartTLSRequired(true);
		
		
		email.send(); //envia o e-mail
		{% endif %}

		{% if 'notificationsAceptanceRejection' in statments %}
		// AcceptReject email
		SimpleEmail email2 = new SimpleEmail(); 
		email2.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email2.addTo(emailDestino2, user.getNameUser()); //destinat�rio 
		email2.setFrom("{{systemEmail}}.gmail.com", "Gerenciador de Eventos Rise"); // remetente 
		email2.setSubject(assunto2); // assunto do e-mail 
		email2.setMsg(mensagem2); //conteudo do e-mail
		
		email2.setAuthentication("{{systemEmail}}.gmail.com", "{{systemPassword}}");
		email2.setSslSmtpPort( "465" ); //578 ou 465
		email2.setSSLOnConnect(true);
		email2.setStartTLSEnabled(true);
		email2.setStartTLSRequired(true);
		
		email2.send(); //envia o e-mail
		{% endif %}

		{% if 'notificationsPaperAssignemnt' in statments %}
		// assignment email
		HtmlEmail email3 = new HtmlEmail(); 
		email3.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email3.addTo(emailDestino3, user.getNameUser()); //destinat�rio 
		email3.setFrom("{{systemEmail}}.gmail.com", "Gerenciador de Eventos Rise"); // remetente 
		email3.setSubject(assunto3); // assunto do e-mail 
		
		//ESTOU ENVIADO UMA IMAGEM EM ANEXO POIS AINDA NAO CONSEGUI PEGAR DO BANCO E INSERIR AQUI
		//O ID DO SIBMISSION PARA PEGAR PDF DO BANCO ESTA NO AUTHOR PASSADO AQUI
		EmailAttachment anexo = new EmailAttachment();
	    anexo.setPath("/images/riseLabs.png");
	    anexo.setDisposition(EmailAttachment.ATTACHMENT);
	    email3.attach(anexo); 
	    
		email3.setMsg(mensagem3); //conteudo do e-mail
		
		email3.setAuthentication("{{systemEmail}}.gmail.com", "{{systemPassword}}");
		email3.setSslSmtpPort( "465" ); //578 ou 465
		email3.setSSLOnConnect(true);
		email3.setStartTLSEnabled(true);
		email3.setStartTLSRequired(true);
	
		email3.send(); //envia o e-mail
		{% endif %}
	}
	{% endif %}
	//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
	public static void sendRoundNotification(Review review, User user) throws EmailException{
		
		String assunto = null;
		String mensagem = null;
		String emailDestino = user.getEmail();
		
		if(review.getResult().equals("Aceito")){
			assunto = "Resultado: Papper Aceito";
			mensagem = "Parabens Seu papper foi Aceito no evento";
		}
		
		if(review.getResult().equals("Rejeitado")){
			assunto = "Resultado Papper";
			mensagem = "Infelizmente Seu papper nao foi Aceito no evento.";
		}
		
		//#if ${ReviewRoundofReview} == "T" and ${NotificationsAceptanceRejection} == "T" 
		if(review.getResult().equals("Em Analise")){
			assunto = "Resultado Round Review Papper!";
			mensagem = "O round de revisão atual de seu papper é" + review.getRound() + "como resultado deste round seu papper esta em analise seguem observacoes a serem corrigidas: " + review.getDescription() + "  Use este numero de identificacao para atualizar correcoes solicitadas na revisao" + review.getIdReview();
		}
		//#endif
		
		SimpleEmail email = new SimpleEmail(); 
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email.addTo(emailDestino, user.getNameUser()); //destinat�rio 
		email.setFrom("{{systemEmail}}.gmail.com", "Gerenciador de Eventos Rise"); // remetente 
		email.setSubject(assunto); // assunto do e-mail 
		email.setMsg(mensagem); //conteudo do e-mail
		
		email.setAuthentication("{{systemEmail}}.gmail.com", "systemPassword");
		email.setSslSmtpPort( "465" ); //578 ou 465
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		email.setStartTLSRequired(true);
		
		
		email.send(); //envia o e-mail
		
	}
	{% endif %}
	{% if 'sendBugTrackEmail' in statments %}
	public static String sendBugtrackEmail(String nome, String assunto, String mensagem) throws EmailException{
		SimpleEmail email = new SimpleEmail();
		String msg;
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email.addTo("{{systemEmail}}.gmail.com", "Bugtrack Event"); //destinat�rio 
		email.setFrom("{{systemEmail}}.gmail.com", nome); // remetente 
		email.setSubject(assunto); // assunto do e-mail 
		email.setMsg(mensagem); //conteudo do e-mail
		
		email.setAuthentication("{{systemEmail}}.gmail.com", "systemPassword");
		email.setSslSmtpPort( "465" ); //578 ou 465
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		email.setStartTLSRequired(true);
		
		email.send(); //envia o e-mail
		msg = "Email enviado com Sucesso";
		return msg;
	}
	{% endif %}
	public static List<String> quebrarKeywords(Submission submission){
		List<String> palavrasDaKeyword = new ArrayList<String>();
		String [] array = submission.getKeywords().split("[,] *");
		
		for(int i=0; i< array.length ; i++){
			palavrasDaKeyword.add(array[i]);
		}
		return palavrasDaKeyword;
	}
}
