package evco.ev.util;

import evco.ev.data.User;
import evco.ev.business.UserControl;
import evco.ev.exception.UserAlreadyInsertedException;
import evco.ev.exception.UserNotFoundException;
import evco.ev.repository.UserRepository;
import evco.ev.repository.UserRepositoryBDR;
import evco.ev.data.Reviewer;
import evco.ev.business.ReviewerControl;
import evco.ev.exception.ReviewerAlreadyInsertedException;
import evco.ev.exception.ReviewerNotFoundException;
import evco.ev.repository.ReviewerRepository;
import evco.ev.repository.ReviewerRepositoryBDR;
import evco.ev.data.Speaker;
import evco.ev.business.SpeakerControl;
import evco.ev.exception.SpeakerAlreadyInsertedException;
import evco.ev.exception.SpeakerNotFoundException;
import evco.ev.repository.SpeakerRepository;
import evco.ev.repository.SpeakerRepositoryBDR;
import evco.ev.data.Event;
import evco.ev.business.EventControl;
import evco.ev.exception.EventAlreadyInsertedException;
import evco.ev.exception.EventNotFoundException;
import evco.ev.repository.EventRepository;
import evco.ev.repository.EventRepositoryBDR;
import evco.ev.data.Payment;
import evco.ev.business.PaymentControl;
import evco.ev.exception.PaymentAlreadyInsertedException;
import evco.ev.exception.PaymentNotFoundException;
import evco.ev.repository.PaymentRepository;
import evco.ev.repository.PaymentRepositoryBDR;
import evco.ev.data.Activity;
import evco.ev.business.ActivityControl;
import evco.ev.exception.ActivityAlreadyInsertedException;
import evco.ev.exception.ActivityNotFoundException;
import evco.ev.repository.ActivityRepository;
import evco.ev.repository.ActivityRepositoryBDR;
import evco.ev.data.Submission;
import evco.ev.business.SubmissionControl;
import evco.ev.exception.SubmissionAlreadyInsertedException;
import evco.ev.exception.SubmissionNotFoundException;
import evco.ev.repository.SubmissionRepository;
import evco.ev.repository.SubmissionRepositoryBDR;
import evco.ev.data.Author;
import evco.ev.business.AuthorControl;
import evco.ev.exception.AuthorAlreadyInsertedException;
import evco.ev.exception.AuthorNotFoundException;
import evco.ev.repository.AuthorRepository;
import evco.ev.repository.AuthorRepositoryBDR;
import evco.ev.data.CheckingCopy;
import evco.ev.business.CheckingCopyControl;
import evco.ev.exception.CheckingCopyAlreadyInsertedException;
import evco.ev.exception.CheckingCopyNotFoundException;
import evco.ev.repository.CheckingCopyRepository;
import evco.ev.repository.CheckingCopyRepositoryBDR;
import evco.ev.data.Review;
import evco.ev.business.ReviewControl;
import evco.ev.exception.ReviewAlreadyInsertedException;
import evco.ev.exception.ReviewNotFoundException;
import evco.ev.repository.ReviewRepository;
import evco.ev.repository.ReviewRepositoryBDR;
import evco.ev.data.ActivityUser;
import evco.ev.business.ActivityUserControl;
import evco.ev.exception.ActivityUserAlreadyInsertedException;
import evco.ev.exception.ActivityUserNotFoundException;
import evco.ev.repository.ActivityUserRepository;
import evco.ev.repository.ActivityUserRepositoryBDR;
import evco.ev.data.ActivitySpeaker;
import evco.ev.business.ActivitySpeakerControl;
import evco.ev.exception.ActivitySpeakerAlreadyInsertedException;
import evco.ev.exception.ActivitySpeakerNotFoundException;
import evco.ev.repository.ActivitySpeakerRepository;
import evco.ev.repository.ActivitySpeakerRepositoryBDR;
import evco.ev.data.SubmissionAuthor;
import evco.ev.business.SubmissionAuthorControl;
import evco.ev.exception.SubmissionAuthorAlreadyInsertedException;
import evco.ev.exception.SubmissionAuthorNotFoundException;
import evco.ev.repository.SubmissionAuthorRepository;
import evco.ev.repository.SubmissionAuthorRepositoryBDR;
import evco.ev.data.SubmissionUser;
import evco.ev.business.SubmissionUserControl;
import evco.ev.exception.SubmissionUserAlreadyInsertedException;
import evco.ev.exception.SubmissionUserNotFoundException;
import evco.ev.repository.SubmissionUserRepository;
import evco.ev.repository.SubmissionUserRepositoryBDR;
import evco.ev.data.Registration;
import evco.ev.business.RegistrationControl;
import evco.ev.exception.RegistrationAlreadyInsertedException;
import evco.ev.exception.RegistrationNotFoundException;
import evco.ev.repository.RegistrationRepository;
import evco.ev.repository.RegistrationRepositoryBDR;
import evco.ev.data.Assignment;
import evco.ev.business.AssignmentControl;
import evco.ev.exception.AssignmentAlreadyInsertedException;
import evco.ev.exception.AssignmentNotFoundException;
import evco.ev.repository.AssignmentRepository;
import evco.ev.repository.AssignmentRepositoryBDR;

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