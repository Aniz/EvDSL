package riseevents.ev.util;

import riseevents.ev.data.User;
import riseevents.ev.business.UserControl;
import riseevents.ev.exception.UserAlreadyInsertedException;
import riseevents.ev.exception.UserNotFoundException;
import riseevents.ev.repository.UserRepository;
import riseevents.ev.repository.UserRepositoryBDR;
import riseevents.ev.data.Organizer;
import riseevents.ev.business.OrganizerControl;
import riseevents.ev.exception.OrganizerAlreadyInsertedException;
import riseevents.ev.exception.OrganizerNotFoundException;
import riseevents.ev.repository.OrganizerRepository;
import riseevents.ev.repository.OrganizerRepositoryBDR;
import riseevents.ev.data.Reviewer;
import riseevents.ev.business.ReviewerControl;
import riseevents.ev.exception.ReviewerAlreadyInsertedException;
import riseevents.ev.exception.ReviewerNotFoundException;
import riseevents.ev.repository.ReviewerRepository;
import riseevents.ev.repository.ReviewerRepositoryBDR;
import riseevents.ev.data.Speaker;
import riseevents.ev.business.SpeakerControl;
import riseevents.ev.exception.SpeakerAlreadyInsertedException;
import riseevents.ev.exception.SpeakerNotFoundException;
import riseevents.ev.repository.SpeakerRepository;
import riseevents.ev.repository.SpeakerRepositoryBDR;
import riseevents.ev.data.Event;
import riseevents.ev.business.EventControl;
import riseevents.ev.exception.EventAlreadyInsertedException;
import riseevents.ev.exception.EventNotFoundException;
import riseevents.ev.repository.EventRepository;
import riseevents.ev.repository.EventRepositoryBDR;
import riseevents.ev.data.Payment;
import riseevents.ev.business.PaymentControl;
import riseevents.ev.exception.PaymentAlreadyInsertedException;
import riseevents.ev.exception.PaymentNotFoundException;
import riseevents.ev.repository.PaymentRepository;
import riseevents.ev.repository.PaymentRepositoryBDR;
import riseevents.ev.data.Activity;
import riseevents.ev.business.ActivityControl;
import riseevents.ev.exception.ActivityAlreadyInsertedException;
import riseevents.ev.exception.ActivityNotFoundException;
import riseevents.ev.repository.ActivityRepository;
import riseevents.ev.repository.ActivityRepositoryBDR;
import riseevents.ev.data.Submission;
import riseevents.ev.business.SubmissionControl;
import riseevents.ev.exception.SubmissionAlreadyInsertedException;
import riseevents.ev.exception.SubmissionNotFoundException;
import riseevents.ev.repository.SubmissionRepository;
import riseevents.ev.repository.SubmissionRepositoryBDR;
import riseevents.ev.data.Author;
import riseevents.ev.business.AuthorControl;
import riseevents.ev.exception.AuthorAlreadyInsertedException;
import riseevents.ev.exception.AuthorNotFoundException;
import riseevents.ev.repository.AuthorRepository;
import riseevents.ev.repository.AuthorRepositoryBDR;
import riseevents.ev.data.CheckingCopy;
import riseevents.ev.business.CheckingCopyControl;
import riseevents.ev.exception.CheckingCopyAlreadyInsertedException;
import riseevents.ev.exception.CheckingCopyNotFoundException;
import riseevents.ev.repository.CheckingCopyRepository;
import riseevents.ev.repository.CheckingCopyRepositoryBDR;
import riseevents.ev.data.NewOption;
import riseevents.ev.business.NewOptionControl;
import riseevents.ev.exception.NewOptionAlreadyInsertedException;
import riseevents.ev.exception.NewOptionNotFoundException;
import riseevents.ev.repository.NewOptionRepository;
import riseevents.ev.repository.NewOptionRepositoryBDR;
import riseevents.ev.data.Review;
import riseevents.ev.business.ReviewControl;
import riseevents.ev.exception.ReviewAlreadyInsertedException;
import riseevents.ev.exception.ReviewNotFoundException;
import riseevents.ev.repository.ReviewRepository;
import riseevents.ev.repository.ReviewRepositoryBDR;
import riseevents.ev.data.ActivityUser;
import riseevents.ev.business.ActivityUserControl;
import riseevents.ev.exception.ActivityUserAlreadyInsertedException;
import riseevents.ev.exception.ActivityUserNotFoundException;
import riseevents.ev.repository.ActivityUserRepository;
import riseevents.ev.repository.ActivityUserRepositoryBDR;
import riseevents.ev.data.ActivitySpeaker;
import riseevents.ev.business.ActivitySpeakerControl;
import riseevents.ev.exception.ActivitySpeakerAlreadyInsertedException;
import riseevents.ev.exception.ActivitySpeakerNotFoundException;
import riseevents.ev.repository.ActivitySpeakerRepository;
import riseevents.ev.repository.ActivitySpeakerRepositoryBDR;
import riseevents.ev.data.ActivityOrganizer;
import riseevents.ev.business.ActivityOrganizerControl;
import riseevents.ev.exception.ActivityOrganizerAlreadyInsertedException;
import riseevents.ev.exception.ActivityOrganizerNotFoundException;
import riseevents.ev.repository.ActivityOrganizerRepository;
import riseevents.ev.repository.ActivityOrganizerRepositoryBDR;
import riseevents.ev.data.SubmissionAuthor;
import riseevents.ev.business.SubmissionAuthorControl;
import riseevents.ev.exception.SubmissionAuthorAlreadyInsertedException;
import riseevents.ev.exception.SubmissionAuthorNotFoundException;
import riseevents.ev.repository.SubmissionAuthorRepository;
import riseevents.ev.repository.SubmissionAuthorRepositoryBDR;
import riseevents.ev.data.SubmissionUser;
import riseevents.ev.business.SubmissionUserControl;
import riseevents.ev.exception.SubmissionUserAlreadyInsertedException;
import riseevents.ev.exception.SubmissionUserNotFoundException;
import riseevents.ev.repository.SubmissionUserRepository;
import riseevents.ev.repository.SubmissionUserRepositoryBDR;
import riseevents.ev.data.Registration;
import riseevents.ev.business.RegistrationControl;
import riseevents.ev.exception.RegistrationAlreadyInsertedException;
import riseevents.ev.exception.RegistrationNotFoundException;
import riseevents.ev.repository.RegistrationRepository;
import riseevents.ev.repository.RegistrationRepositoryBDR;
import riseevents.ev.data.Assignment;
import riseevents.ev.business.AssignmentControl;
import riseevents.ev.exception.AssignmentAlreadyInsertedException;
import riseevents.ev.exception.AssignmentNotFoundException;
import riseevents.ev.repository.AssignmentRepository;
import riseevents.ev.repository.AssignmentRepositoryBDR;

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
	
	public static void sendNotification(User user, Review review) throws EmailException{

		// esta classe eh chamada logo apos o insert do assignment
		String assunto = "Prazo de entrega de Rivisao";
		String mensagem = "O prazo de entrega é ate 15 dias antes da data oficial de Inicio do evento. Seu ID para cadastrar a revisao é:" + review.getIdReview() + " Favor usar este ID!" ;
		String emailDestino = user.getEmail();
	
		String assunto2 = "Resultado Revisao Papper!";
		String mensagem2 = "Seu Papper esta sendo revisado. O resultado sera encaminhado via email.";
		String emailDestino2 = user.getEmail();
		
		String assunto3 = "Pappers para revisao";
		String mensagem3 = "Seguem em anexos pappers para revisao!";
		String emailDestino3 = user.getEmail();
	
		
		SimpleEmail email = new SimpleEmail(); 
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email.addTo(emailDestino, user.getNameUser()); //destinat�rio 
		email.setFrom("rise.gmail.com", "Gerenciador de Eventos Rise"); // remetente 
		email.setSubject(assunto); // assunto do e-mail 
		email.setMsg(mensagem); //conteudo do e-mail
		
		email.setAuthentication("rise.gmail.com", "password");
		email.setSslSmtpPort( "465" ); //578 ou 465
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		email.setStartTLSRequired(true);
		
		
		email.send(); //envia o e-mail

		// AcceptReject email
		SimpleEmail email2 = new SimpleEmail(); 
		email2.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email2.addTo(emailDestino2, user.getNameUser()); //destinat�rio 
		email2.setFrom("rise.gmail.com", "Gerenciador de Eventos Rise"); // remetente 
		email2.setSubject(assunto2); // assunto do e-mail 
		email2.setMsg(mensagem2); //conteudo do e-mail
		
		email2.setAuthentication("rise.gmail.com", "password");
		email2.setSslSmtpPort( "465" ); //578 ou 465
		email2.setSSLOnConnect(true);
		email2.setStartTLSEnabled(true);
		email2.setStartTLSRequired(true);
		
		email2.send(); //envia o e-mail

		// assignment email
		HtmlEmail email3 = new HtmlEmail(); 
		email3.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email3.addTo(emailDestino3, user.getNameUser()); //destinat�rio 
		email3.setFrom("rise.gmail.com", "Gerenciador de Eventos Rise"); // remetente 
		email3.setSubject(assunto3); // assunto do e-mail 
		
		//ESTOU ENVIADO UMA IMAGEM EM ANEXO POIS AINDA NAO CONSEGUI PEGAR DO BANCO E INSERIR AQUI
		//O ID DO SIBMISSION PARA PEGAR PDF DO BANCO ESTA NO AUTHOR PASSADO AQUI
		EmailAttachment anexo = new EmailAttachment();
	    anexo.setPath("/images/riseLabs.png");
	    anexo.setDisposition(EmailAttachment.ATTACHMENT);
	    email3.attach(anexo); 
	    
		email3.setMsg(mensagem3); //conteudo do e-mail
		
		email3.setAuthentication("rise.gmail.com", "password");
		email3.setSslSmtpPort( "465" ); //578 ou 465
		email3.setSSLOnConnect(true);
		email3.setStartTLSEnabled(true);
		email3.setStartTLSRequired(true);
	
		email3.send(); //envia o e-mail
	}
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
		email.setFrom("rise.gmail.com", "Gerenciador de Eventos Rise"); // remetente 
		email.setSubject(assunto); // assunto do e-mail 
		email.setMsg(mensagem); //conteudo do e-mail
		
		email.setAuthentication("rise.gmail.com", "systemPassword");
		email.setSslSmtpPort( "465" ); //578 ou 465
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		email.setStartTLSRequired(true);
		
		
		email.send(); //envia o e-mail
		
	}
	public static String sendBugtrackEmail(String nome, String assunto, String mensagem) throws EmailException{
		SimpleEmail email = new SimpleEmail();
		String msg;
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email.addTo("rise.gmail.com", "Bugtrack Event"); //destinat�rio 
		email.setFrom("rise.gmail.com", nome); // remetente 
		email.setSubject(assunto); // assunto do e-mail 
		email.setMsg(mensagem); //conteudo do e-mail
		
		email.setAuthentication("rise.gmail.com", "systemPassword");
		email.setSslSmtpPort( "465" ); //578 ou 465
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		email.setStartTLSRequired(true);
		
		email.send(); //envia o e-mail
		msg = "Email enviado com Sucesso";
		return msg;
	}
	public static List<String> quebrarKeywords(Submission submission){
		List<String> palavrasDaKeyword = new ArrayList<String>();
		String [] array = submission.getKeywords().split("[,] *");
		
		for(int i=0; i< array.length ; i++){
			palavrasDaKeyword.add(array[i]);
		}
		return palavrasDaKeyword;
	}
}