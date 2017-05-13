package riseevents.ev.util;

import riseevents.ev.data.User;
import riseevents.ev.business.UserControl;
import riseevents.ev.exception.UserAlreadyInsertedException;
import riseevents.ev.exception.UserNotFoundException;
import riseevents.ev.repository.UserRepository;
import riseevents.ev.repository.UserRepositoryBDR;
import riseevents.ev.data.Activity;
import riseevents.ev.business.ActivityControl;
import riseevents.ev.exception.ActivityAlreadyInsertedException;
import riseevents.ev.exception.ActivityNotFoundException;
import riseevents.ev.repository.ActivityRepository;
import riseevents.ev.repository.ActivityRepositoryBDR;
import riseevents.ev.data.Event;
import riseevents.ev.business.EventControl;
import riseevents.ev.exception.EventAlreadyInsertedException;
import riseevents.ev.exception.EventNotFoundException;
import riseevents.ev.repository.EventRepository;
import riseevents.ev.repository.EventRepositoryBDR;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class LibraryOfDSL {

	public Boolean returnValue(Boolean value){
		if(value){
			return true;
		}
		else {
			return false;
		}	
	}	

	
	//#if ${Bugs} == "T"
	public String sendBugtrackEmail(String nome, String assunto, String mensagem) throws EmailException{
		SimpleEmail email = new SimpleEmail();
		String msg;
		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
		
		email.addTo("riseeventemail@gmail.com", "Bugtrack Event"); //destinat�rio 
		email.setFrom("riseeventemail@gmail.com", nome); // remetente 
		email.setSubject(assunto); // assunto do e-mail 
		email.setMsg(mensagem); //conteudo do e-mail
		
		email.setAuthentication("riseeventemail@gmail.com", "senhasecreta");
		email.setSslSmtpPort( "465" ); //578 ou 465
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		email.setStartTLSRequired(true);
		
		email.send(); //envia o e-mail
		msg = "Email enviado com Sucesso";
		return msg;
	}
	//#endif	
}