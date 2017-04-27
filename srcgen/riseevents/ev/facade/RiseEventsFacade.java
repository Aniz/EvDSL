package riseevents.ev.facade;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.mail.EmailException;
import riseevents.ev.util.Email;
import riseevents.ev.data.Review;
import riseevents.ev.business.UserControl;
import riseevents.ev.exception.UserAlreadyInsertedException;
import riseevents.ev.exception.UserNotFoundException;
import riseevents.ev.exception.UserException;
import riseevents.ev.repository.UserRepository;
import riseevents.ev.repository.UserRepositoryBDR;
import riseevents.ev.business.SpeakerControl;
import riseevents.ev.exception.SpeakerAlreadyInsertedException;
import riseevents.ev.exception.SpeakerNotFoundException;
import riseevents.ev.exception.SpeakerException;
import riseevents.ev.repository.SpeakerRepository;
import riseevents.ev.repository.SpeakerRepositoryBDR;
import riseevents.ev.business.OrganizerControl;
import riseevents.ev.exception.OrganizerAlreadyInsertedException;
import riseevents.ev.exception.OrganizerNotFoundException;
import riseevents.ev.exception.OrganizerException;
import riseevents.ev.repository.OrganizerRepository;
import riseevents.ev.repository.OrganizerRepositoryBDR;
import riseevents.ev.business.ReviewerControl;
import riseevents.ev.exception.ReviewerAlreadyInsertedException;
import riseevents.ev.exception.ReviewerNotFoundException;
import riseevents.ev.exception.ReviewerException;
import riseevents.ev.repository.ReviewerRepository;
import riseevents.ev.repository.ReviewerRepositoryBDR;
import riseevents.ev.business.EventControl;
import riseevents.ev.exception.EventAlreadyInsertedException;
import riseevents.ev.exception.EventNotFoundException;
import riseevents.ev.exception.EventException;
import riseevents.ev.repository.EventRepository;
import riseevents.ev.repository.EventRepositoryBDR;
import riseevents.ev.business.PaymentControl;
import riseevents.ev.exception.PaymentAlreadyInsertedException;
import riseevents.ev.exception.PaymentNotFoundException;
import riseevents.ev.exception.PaymentException;
import riseevents.ev.repository.PaymentRepository;
import riseevents.ev.repository.PaymentRepositoryBDR;
import riseevents.ev.business.ActivityControl;
import riseevents.ev.exception.ActivityAlreadyInsertedException;
import riseevents.ev.exception.ActivityNotFoundException;
import riseevents.ev.exception.ActivityException;
import riseevents.ev.repository.ActivityRepository;
import riseevents.ev.repository.ActivityRepositoryBDR;
import riseevents.ev.business.AssignmentControl;
import riseevents.ev.exception.AssignmentAlreadyInsertedException;
import riseevents.ev.exception.AssignmentNotFoundException;
import riseevents.ev.exception.AssignmentException;
import riseevents.ev.repository.AssignmentRepository;
import riseevents.ev.repository.AssignmentRepositoryBDR;
import riseevents.ev.business.SubmissionControl;
import riseevents.ev.exception.SubmissionAlreadyInsertedException;
import riseevents.ev.exception.SubmissionNotFoundException;
import riseevents.ev.exception.SubmissionException;
import riseevents.ev.repository.SubmissionRepository;
import riseevents.ev.repository.SubmissionRepositoryBDR;
import riseevents.ev.business.AuthorControl;
import riseevents.ev.exception.AuthorAlreadyInsertedException;
import riseevents.ev.exception.AuthorNotFoundException;
import riseevents.ev.exception.AuthorException;
import riseevents.ev.repository.AuthorRepository;
import riseevents.ev.repository.AuthorRepositoryBDR;
import riseevents.ev.business.CheckingCopyControl;
import riseevents.ev.exception.CheckingCopyAlreadyInsertedException;
import riseevents.ev.exception.CheckingCopyNotFoundException;
import riseevents.ev.exception.CheckingCopyException;
import riseevents.ev.repository.CheckingCopyRepository;
import riseevents.ev.repository.CheckingCopyRepositoryBDR;
import com.lowagie.text.DocumentException;


public class RiseEventsFacade {

	private UserControl userList;
	private SpeakerControl speakerList;
	private OrganizerControl organizerList;
	private ReviewerControl reviewerList;
	private EventControl eventList;
	private PaymentControl paymentList;
	private ActivityControl activityList;
	private AssignmentControl assignmentList;
	private SubmissionControl submissionList;
	private AuthorControl authorList;
	private CheckingCopyControl checkingcopyList;

	protected static RiseEventsFacade instance;
	
	public RiseEventsFacade(){
		UserRepository userRepository = UserRepositoryBDR.getInstance();
		SpeakerRepository speakerRepository = SpeakerRepositoryBDR.getInstance();
		OrganizerRepository organizerRepository = OrganizerRepositoryBDR.getInstance();
		ReviewerRepository reviewerRepository = ReviewerRepositoryBDR.getInstance();
		EventRepository eventRepository = EventRepositoryBDR.getInstance();
		PaymentRepository paymentRepository = PaymentRepositoryBDR.getInstance();
		ActivityRepository activityRepository = ActivityRepositoryBDR.getInstance();
		AssignmentRepository assignmentRepository = AssignmentRepositoryBDR.getInstance();
		SubmissionRepository submissionRepository = SubmissionRepositoryBDR.getInstance();
		AuthorRepository authorRepository = AuthorRepositoryBDR.getInstance();
		CheckingCopyRepository checkingcopyRepository = CheckingCopyRepositoryBDR.getInstance();
		
		userList = new UserControl(userRepository); 
		speakerList = new SpeakerControl(speakerRepository); 
		organizerList = new OrganizerControl(organizerRepository); 
		reviewerList = new ReviewerControl(reviewerRepository); 
		eventList = new EventControl(eventRepository); 
		paymentList = new PaymentControl(paymentRepository); 
		activityList = new ActivityControl(activityRepository); 
		assignmentList = new AssignmentControl(assignmentRepository); 
		submissionList = new SubmissionControl(submissionRepository); 
		authorList = new AuthorControl(authorRepository); 
		checkingcopyList = new CheckingCopyControl(checkingcopyRepository); 
	
	}
	
	public static RiseEventsFacade getInstance()  {
		if (RiseEventsFacade.instance == null) {
			RiseEventsFacade.instance = new RiseEventsFacade();
		}
		return RiseEventsFacade.instance;
	}
	
	public void insertUser(User entity) throws UserAlreadyInsertedException, RepositoryException{
		this.userList.insert(entity);
	}
	public void removeUser(int idEntity) throws UserNotFoundException, RepositoryException, UserAlreadyInsertedException{
		userList.remove(idEntity);  
	}
	public void updateUser(User Entity) throws UserNotFoundException, Exception, UserAlreadyInsertedException{
		userList.update(Entity);
	}
	public List<User> getUser() throws RepositoryException{
		return Users.getUser();
	}
	public User searchUser(int idEntity) throws UserNotFoundException, RepositoryException, UserAlreadyInsertedException{
		return userList.search(idEntity);
	}
	public int getUserLastId() throws RepositoryException{
		return userList.getUserLastId();
	}
	public int getUserIdByName(String entityName) throws RepositoryException{
		return userList.getUserIdByName(entityName);
	}
	public boolean isThereUser(int idEntity) throws RepositoryException{
		return userList.isThere(idEntity);
	}
	public void insertSpeaker(Speaker entity) throws SpeakerAlreadyInsertedException, RepositoryException{
		this.speakerList.insert(entity);
	}
	public void removeSpeaker(int idEntity) throws SpeakerNotFoundException, RepositoryException, SpeakerAlreadyInsertedException{
		speakerList.remove(idEntity);  
	}
	public void updateSpeaker(Speaker Entity) throws SpeakerNotFoundException, Exception, SpeakerAlreadyInsertedException{
		speakerList.update(Entity);
	}
	public List<Speaker> getSpeaker() throws RepositoryException{
		return Speakers.getSpeaker();
	}
	public Speaker searchSpeaker(int idEntity) throws SpeakerNotFoundException, RepositoryException, SpeakerAlreadyInsertedException{
		return speakerList.search(idEntity);
	}
	public int getSpeakerLastId() throws RepositoryException{
		return speakerList.getSpeakerLastId();
	}
	public int getSpeakerIdByName(String entityName) throws RepositoryException{
		return speakerList.getSpeakerIdByName(entityName);
	}
	public boolean isThereSpeaker(int idEntity) throws RepositoryException{
		return speakerList.isThere(idEntity);
	}
	public void insertOrganizer(Organizer entity) throws OrganizerAlreadyInsertedException, RepositoryException{
		this.organizerList.insert(entity);
	}
	public void removeOrganizer(int idEntity) throws OrganizerNotFoundException, RepositoryException, OrganizerAlreadyInsertedException{
		organizerList.remove(idEntity);  
	}
	public void updateOrganizer(Organizer Entity) throws OrganizerNotFoundException, Exception, OrganizerAlreadyInsertedException{
		organizerList.update(Entity);
	}
	public List<Organizer> getOrganizer() throws RepositoryException{
		return Organizers.getOrganizer();
	}
	public Organizer searchOrganizer(int idEntity) throws OrganizerNotFoundException, RepositoryException, OrganizerAlreadyInsertedException{
		return organizerList.search(idEntity);
	}
	public int getOrganizerLastId() throws RepositoryException{
		return organizerList.getOrganizerLastId();
	}
	public int getOrganizerIdByName(String entityName) throws RepositoryException{
		return organizerList.getOrganizerIdByName(entityName);
	}
	public boolean isThereOrganizer(int idEntity) throws RepositoryException{
		return organizerList.isThere(idEntity);
	}
	public void insertReviewer(Reviewer entity) throws ReviewerAlreadyInsertedException, RepositoryException{
		this.reviewerList.insert(entity);
	}
	public void removeReviewer(int idEntity) throws ReviewerNotFoundException, RepositoryException, ReviewerAlreadyInsertedException{
		reviewerList.remove(idEntity);  
	}
	public void updateReviewer(Reviewer Entity) throws ReviewerNotFoundException, Exception, ReviewerAlreadyInsertedException{
		reviewerList.update(Entity);
	}
	public List<Reviewer> getReviewer() throws RepositoryException{
		return Reviewers.getReviewer();
	}
	public Reviewer searchReviewer(int idEntity) throws ReviewerNotFoundException, RepositoryException, ReviewerAlreadyInsertedException{
		return reviewerList.search(idEntity);
	}
	public int getReviewerLastId() throws RepositoryException{
		return reviewerList.getReviewerLastId();
	}
	public int getReviewerIdByName(String entityName) throws RepositoryException{
		return reviewerList.getReviewerIdByName(entityName);
	}
	public boolean isThereReviewer(int idEntity) throws RepositoryException{
		return reviewerList.isThere(idEntity);
	}
	public void insertEvent(Event entity) throws EventAlreadyInsertedException, RepositoryException{
		this.eventList.insert(entity);
	}
	public void removeEvent(int idEntity) throws EventNotFoundException, RepositoryException, EventAlreadyInsertedException{
		eventList.remove(idEntity);  
	}
	public void updateEvent(Event Entity) throws EventNotFoundException, Exception, EventAlreadyInsertedException{
		eventList.update(Entity);
	}
	public List<Event> getEvent() throws RepositoryException{
		return Events.getEvent();
	}
	public Event searchEvent(int idEntity) throws EventNotFoundException, RepositoryException, EventAlreadyInsertedException{
		return eventList.search(idEntity);
	}
	public int getEventLastId() throws RepositoryException{
		return eventList.getEventLastId();
	}
	public int getEventIdByName(String entityName) throws RepositoryException{
		return eventList.getEventIdByName(entityName);
	}
	public boolean isThereEvent(int idEntity) throws RepositoryException{
		return eventList.isThere(idEntity);
	}
	public void insertPayment(Payment entity) throws PaymentAlreadyInsertedException, RepositoryException{
		this.paymentList.insert(entity);
	}
	public void removePayment(int idEntity) throws PaymentNotFoundException, RepositoryException, PaymentAlreadyInsertedException{
		paymentList.remove(idEntity);  
	}
	public void updatePayment(Payment Entity) throws PaymentNotFoundException, Exception, PaymentAlreadyInsertedException{
		paymentList.update(Entity);
	}
	public List<Payment> getPayment() throws RepositoryException{
		return Payments.getPayment();
	}
	public Payment searchPayment(int idEntity) throws PaymentNotFoundException, RepositoryException, PaymentAlreadyInsertedException{
		return paymentList.search(idEntity);
	}
	public int getPaymentLastId() throws RepositoryException{
		return paymentList.getPaymentLastId();
	}
	public int getPaymentIdByName(String entityName) throws RepositoryException{
		return paymentList.getPaymentIdByName(entityName);
	}
	public boolean isTherePayment(int idEntity) throws RepositoryException{
		return paymentList.isThere(idEntity);
	}
	public void insertActivity(Activity entity) throws ActivityAlreadyInsertedException, RepositoryException{
		this.activityList.insert(entity);
	}
	public void removeActivity(int idEntity) throws ActivityNotFoundException, RepositoryException, ActivityAlreadyInsertedException{
		activityList.remove(idEntity);  
	}
	public void updateActivity(Activity Entity) throws ActivityNotFoundException, Exception, ActivityAlreadyInsertedException{
		activityList.update(Entity);
	}
	public List<Activity> getActivity() throws RepositoryException{
		return Activitys.getActivity();
	}
	public Activity searchActivity(int idEntity) throws ActivityNotFoundException, RepositoryException, ActivityAlreadyInsertedException{
		return activityList.search(idEntity);
	}
	public int getActivityLastId() throws RepositoryException{
		return activityList.getActivityLastId();
	}
	public int getActivityIdByName(String entityName) throws RepositoryException{
		return activityList.getActivityIdByName(entityName);
	}
	public boolean isThereActivity(int idEntity) throws RepositoryException{
		return activityList.isThere(idEntity);
	}
	public void insertAssignment(Assignment entity) throws AssignmentAlreadyInsertedException, RepositoryException{
		this.assignmentList.insert(entity);
	}
	public void removeAssignment(int idEntity) throws AssignmentNotFoundException, RepositoryException, AssignmentAlreadyInsertedException{
		assignmentList.remove(idEntity);  
	}
	public List<Assignment> getAssignment() throws RepositoryException{
		return Assignments.getAssignment();
	}
	public Assignment searchAssignment(int idEntity) throws AssignmentNotFoundException, RepositoryException, AssignmentAlreadyInsertedException{
		return assignmentList.search(idEntity);
	}
	public int getAssignmentLastId() throws RepositoryException{
		return assignmentList.getAssignmentLastId();
	}
	public int getAssignmentIdByName(String entityName) throws RepositoryException{
		return assignmentList.getAssignmentIdByName(entityName);
	}
	public boolean isThereAssignment(int idEntity) throws RepositoryException{
		return assignmentList.isThere(idEntity);
	}
	public void removeSubmission(int idEntity) throws SubmissionNotFoundException, RepositoryException, SubmissionAlreadyInsertedException{
		submissionList.remove(idEntity);  
	}
	public List<Submission> getSubmission() throws RepositoryException{
		return Submissions.getSubmission();
	}
	public Submission searchSubmission(int idEntity) throws SubmissionNotFoundException, RepositoryException, SubmissionAlreadyInsertedException{
		return submissionList.search(idEntity);
	}
	public int getSubmissionLastId() throws RepositoryException{
		return submissionList.getSubmissionLastId();
	}
	public int getSubmissionIdByName(String entityName) throws RepositoryException{
		return submissionList.getSubmissionIdByName(entityName);
	}
	public boolean isThereSubmission(int idEntity) throws RepositoryException{
		return submissionList.isThere(idEntity);
	}
	public List<Author> getAuthor() throws RepositoryException{
		return Authors.getAuthor();
	}
	public int getAuthorLastId() throws RepositoryException{
		return authorList.getAuthorLastId();
	}
	public int getAuthorIdByName(String entityName) throws RepositoryException{
		return authorList.getAuthorIdByName(entityName);
	}
	public boolean isThereAuthor(int idEntity) throws RepositoryException{
		return authorList.isThere(idEntity);
	}
	public void insertCheckingCopy(CheckingCopy entity) throws CheckingCopyAlreadyInsertedException, RepositoryException{
		this.checkingcopyList.insert(entity);
	}
	public void removeCheckingCopy(int idEntity) throws CheckingCopyNotFoundException, RepositoryException, CheckingCopyAlreadyInsertedException{
		checkingcopyList.remove(idEntity);  
	}
	public void updateCheckingCopy(CheckingCopy Entity) throws CheckingCopyNotFoundException, Exception, CheckingCopyAlreadyInsertedException{
		checkingcopyList.update(Entity);
	}
	public List<CheckingCopy> getCheckingCopy() throws RepositoryException{
		return CheckingCopys.getCheckingCopy();
	}
	public CheckingCopy searchCheckingCopy(int idEntity) throws CheckingCopyNotFoundException, RepositoryException, CheckingCopyAlreadyInsertedException{
		return checkingcopyList.search(idEntity);
	}
	public int getCheckingCopyLastId() throws RepositoryException{
		return checkingcopyList.getCheckingCopyLastId();
	}
	public int getCheckingCopyIdByName(String entityName) throws RepositoryException{
		return checkingcopyList.getCheckingCopyIdByName(entityName);
	}
	public boolean isThereCheckingCopy(int idEntity) throws RepositoryException{
		return checkingcopyList.isThere(idEntity);
	}
	
	
	
	
	//ACTIVITY
	public List<Activity> getActivitiesByEvent(int idEvent) throws RepositoryException{
		return activities.getActivitiesByEvent(idEvent);
	}
	
	public float getEventMainTrackValue(int idEvent) throws RepositoryException{
		return activities.getEventMainTrackValue(idEvent);
	}
	
	public int getActivityMainTrackId(int idEvent) throws RepositoryException{
		return activities.getActivityMainTrackId(idEvent);
	}
	
	public int getEventbyActivity(int idActivity) throws RepositoryException{
		return activities.getEventbyActivity(idActivity);
	}
	

	public List<Activity> getActivities() throws RepositoryException{
		return activities.getActivities();
	}
	
	public void removeValue(float value, int idRegistration) throws RepositoryException{
		registrations.removeValue(value, idRegistration);
	}
	public void addValue(float value, int idRegistration) throws RepositoryException{
		registrations.addValue(value, idRegistration);
	}
	public void typePayment(Payment payment, Payment paymentout) throws DocumentException, IOException {
		this.payments.type(payment, paymentout);
	}
	
	
	public void pdfRecovey(int idSubmission) throws RepositoryException{
		this.submissions.pdfRecover(idSubmission);
	}
	public List<Submission> getSubmissionsByUser(int idUser) throws RepositoryException{
		return submissions.getSubmissionsByUser(idUser);
	}
	
	
	//if Reviewer and Submission -> Review is enable 
	//Review Feature
			
	public void emailRoundNotification (Review review, User user, Email email) throws EmailException{
		reviews.emailRoundNotification(review, user, email);
	}
	public List<String> getReviewsBySubmission(int idSubmission) throws RepositoryException{
		return reviews.getReviewsBySubmission(idSubmission);
	}

	public Reviewer getReviewerByknowledgeArea(String knowledgearea) throws ReviewerNotFoundException, RepositoryException, ReviewerAlreadyInsertedException{
		return reviewers.getReviewerByknowledgeArea(knowledgearea);
	}

	
}