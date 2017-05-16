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

	
	
	private List<String> quebrarKeywords(Submission submission){
		List<String> palavrasDaKeyword = new ArrayList<String>();
		String [] array = submission.getKeywords().split("[,] *");
		
		for(int i=0; i< array.length ; i++){
			palavrasDaKeyword.add(array[i]);
		}
		return palavrasDaKeyword;
	}
	
}