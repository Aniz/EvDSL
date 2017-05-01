package riseevents.ev.util;

import riseevents.ev.data.User;
import riseevents.ev.business.UserControl;
import riseevents.ev.exception.UserAlreadyInsertedException;
import riseevents.ev.exception.UserNotFoundException;
import riseevents.ev.repository.UserRepository;
import riseevents.ev.repository.UserRepositoryBDR;
import riseevents.ev.data.Speaker;
import riseevents.ev.business.SpeakerControl;
import riseevents.ev.exception.SpeakerAlreadyInsertedException;
import riseevents.ev.exception.SpeakerNotFoundException;
import riseevents.ev.repository.SpeakerRepository;
import riseevents.ev.repository.SpeakerRepositoryBDR;
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

public class LibraryOfDSL {

	public Boolean returnValue(Boolean value){
		if(value){
			return true;
		}
		else {
			return false;
		}	
	}	
}