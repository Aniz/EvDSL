package rise.splcc.facade;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.mail.EmailException;
import rise.splcc.util.Email;

import rise.splcc.business.UserControl;
import rise.splcc.exception.UserException;
import rise.splcc.repository.UserRepository;
import rise.splcc.repository.UserRepositoryBDR;
import rise.splcc.business.SpeakerControl;
import rise.splcc.exception.SpeakerException;
import rise.splcc.repository.SpeakerRepository;
import rise.splcc.repository.SpeakerRepositoryBDR;
import rise.splcc.business.OrganizerControl;
import rise.splcc.exception.OrganizerException;
import rise.splcc.repository.OrganizerRepository;
import rise.splcc.repository.OrganizerRepositoryBDR;
import rise.splcc.business.ReviewerControl;
import rise.splcc.exception.ReviewerException;
import rise.splcc.repository.ReviewerRepository;
import rise.splcc.repository.ReviewerRepositoryBDR;
import rise.splcc.business.EventControl;
import rise.splcc.exception.EventException;
import rise.splcc.repository.EventRepository;
import rise.splcc.repository.EventRepositoryBDR;
import rise.splcc.business.PaymentControl;
import rise.splcc.exception.PaymentException;
import rise.splcc.repository.PaymentRepository;
import rise.splcc.repository.PaymentRepositoryBDR;
import rise.splcc.business.ActivityControl;
import rise.splcc.exception.ActivityException;
import rise.splcc.repository.ActivityRepository;
import rise.splcc.repository.ActivityRepositoryBDR;
import rise.splcc.business.AssignmentControl;
import rise.splcc.exception.AssignmentException;
import rise.splcc.repository.AssignmentRepository;
import rise.splcc.repository.AssignmentRepositoryBDR;
import rise.splcc.business.SubmissionControl;
import rise.splcc.exception.SubmissionException;
import rise.splcc.repository.SubmissionRepository;
import rise.splcc.repository.SubmissionRepositoryBDR;
import rise.splcc.business.AuthorControl;
import rise.splcc.exception.AuthorException;
import rise.splcc.repository.AuthorRepository;
import rise.splcc.repository.AuthorRepositoryBDR;
import rise.splcc.business.CheckingCopyControl;
import rise.splcc.exception.CheckingCopyException;
import rise.splcc.repository.CheckingCopyRepository;
import rise.splcc.repository.CheckingCopyRepositoryBDR;
import com.lowagie.text.DocumentException;


public class RiSEventFacade {

	private EventControl eventList;
	private UserControl userList;

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

	protected static RiSEventFacade instance;
	
	public RiSEventFacade(){
		EventRepository eventRepository = EventRepositoryBDR.getInstance();
		UserRepository userRepository = UserRepositoryBDR.getInstance();
		
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
		
		eventControlList = new EventControl(eventRepository);		
		userControlList = new UserControl(userRepository);
	
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
	
	public static RiSEventFacade getInstance()  {
		if (RiSEventFacade.instance == null) {
			RiSEventFacade.instance = new RiSEventFacade();
		}
		return RiSEventFacade.instance;
	}
	
	public void insertUser(User entity) throws UserException, RepositoryException{
		this.userList.insert(entity);
	}
	public void removeUser(int idEntity) throws UserException, RepositoryException, UserException{
		userList.remove(idEntity);  
	}
	
	public void updateUser(User Entity) throws UserException, Exception, UserException{
		userList.update(User);
	}
	
	public List<User> getUser() throws RepositoryException{
		return Users.getUser();
	}
	
	public User searchUser(int idEntity) throws UserException, RepositoryException, UserException{
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
	public void insertSpeaker(Speaker entity) throws SpeakerException, RepositoryException{
		this.speakerList.insert(entity);
	}
	public void removeSpeaker(int idEntity) throws SpeakerException, RepositoryException, SpeakerException{
		speakerList.remove(idEntity);  
	}
	
	public void updateSpeaker(Speaker Entity) throws SpeakerException, Exception, SpeakerException{
		speakerList.update(Speaker);
	}
	
	public List<Speaker> getSpeaker() throws RepositoryException{
		return Speakers.getSpeaker();
	}
	
	public Speaker searchSpeaker(int idEntity) throws SpeakerException, RepositoryException, SpeakerException{
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
	public void insertOrganizer(Organizer entity) throws OrganizerException, RepositoryException{
		this.organizerList.insert(entity);
	}
	public void removeOrganizer(int idEntity) throws OrganizerException, RepositoryException, OrganizerException{
		organizerList.remove(idEntity);  
	}
	
	public void updateOrganizer(Organizer Entity) throws OrganizerException, Exception, OrganizerException{
		organizerList.update(Organizer);
	}
	
	public List<Organizer> getOrganizer() throws RepositoryException{
		return Organizers.getOrganizer();
	}
	
	public Organizer searchOrganizer(int idEntity) throws OrganizerException, RepositoryException, OrganizerException{
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
	public void insertReviewer(Reviewer entity) throws ReviewerException, RepositoryException{
		this.reviewerList.insert(entity);
	}
	public void removeReviewer(int idEntity) throws ReviewerException, RepositoryException, ReviewerException{
		reviewerList.remove(idEntity);  
	}
	
	public void updateReviewer(Reviewer Entity) throws ReviewerException, Exception, ReviewerException{
		reviewerList.update(Reviewer);
	}
	
	public List<Reviewer> getReviewer() throws RepositoryException{
		return Reviewers.getReviewer();
	}
	
	public Reviewer searchReviewer(int idEntity) throws ReviewerException, RepositoryException, ReviewerException{
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
	public void insertEvent(Event entity) throws EventException, RepositoryException{
		this.eventList.insert(entity);
	}
	public void removeEvent(int idEntity) throws EventException, RepositoryException, EventException{
		eventList.remove(idEntity);  
	}
	
	public void updateEvent(Event Entity) throws EventException, Exception, EventException{
		eventList.update(Event);
	}
	
	public List<Event> getEvent() throws RepositoryException{
		return Events.getEvent();
	}
	
	public Event searchEvent(int idEntity) throws EventException, RepositoryException, EventException{
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
	public void insertPayment(Payment entity) throws PaymentException, RepositoryException{
		this.paymentList.insert(entity);
	}
	public void removePayment(int idEntity) throws PaymentException, RepositoryException, PaymentException{
		paymentList.remove(idEntity);  
	}
	
	public void updatePayment(Payment Entity) throws PaymentException, Exception, PaymentException{
		paymentList.update(Payment);
	}
	
	public List<Payment> getPayment() throws RepositoryException{
		return Payments.getPayment();
	}
	
	public Payment searchPayment(int idEntity) throws PaymentException, RepositoryException, PaymentException{
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
	public void insertActivity(Activity entity) throws ActivityException, RepositoryException{
		this.activityList.insert(entity);
	}
	public void removeActivity(int idEntity) throws ActivityException, RepositoryException, ActivityException{
		activityList.remove(idEntity);  
	}
	
	public void updateActivity(Activity Entity) throws ActivityException, Exception, ActivityException{
		activityList.update(Activity);
	}
	
	public List<Activity> getActivity() throws RepositoryException{
		return Activitys.getActivity();
	}
	
	public Activity searchActivity(int idEntity) throws ActivityException, RepositoryException, ActivityException{
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
	public void insertAssignment(Assignment entity) throws AssignmentException, RepositoryException{
		this.assignmentList.insert(entity);
	}
	public void removeAssignment(int idEntity) throws AssignmentException, RepositoryException, AssignmentException{
		assignmentList.remove(idEntity);  
	}
	
	public void updateAssignment(Assignment Entity) throws AssignmentException, Exception, AssignmentException{
		assignmentList.update(Assignment);
	}
	
	public List<Assignment> getAssignment() throws RepositoryException{
		return Assignments.getAssignment();
	}
	
	public Assignment searchAssignment(int idEntity) throws AssignmentException, RepositoryException, AssignmentException{
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
	public void insertSubmission(Submission entity) throws SubmissionException, RepositoryException{
		this.submissionList.insert(entity);
	}
	public void removeSubmission(int idEntity) throws SubmissionException, RepositoryException, SubmissionException{
		submissionList.remove(idEntity);  
	}
	
	public void updateSubmission(Submission Entity) throws SubmissionException, Exception, SubmissionException{
		submissionList.update(Submission);
	}
	
	public List<Submission> getSubmission() throws RepositoryException{
		return Submissions.getSubmission();
	}
	
	public Submission searchSubmission(int idEntity) throws SubmissionException, RepositoryException, SubmissionException{
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
	public void insertAuthor(Author entity) throws AuthorException, RepositoryException{
		this.authorList.insert(entity);
	}
	public void removeAuthor(int idEntity) throws AuthorException, RepositoryException, AuthorException{
		authorList.remove(idEntity);  
	}
	
	public void updateAuthor(Author Entity) throws AuthorException, Exception, AuthorException{
		authorList.update(Author);
	}
	
	public List<Author> getAuthor() throws RepositoryException{
		return Authors.getAuthor();
	}
	
	public Author searchAuthor(int idEntity) throws AuthorException, RepositoryException, AuthorException{
		return authorList.search(idEntity);
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
	public void insertCheckingCopy(CheckingCopy entity) throws CheckingCopyException, RepositoryException{
		this.checkingcopyList.insert(entity);
	}
	public void removeCheckingCopy(int idEntity) throws CheckingCopyException, RepositoryException, CheckingCopyException{
		checkingcopyList.remove(idEntity);  
	}
	
	public void updateCheckingCopy(CheckingCopy Entity) throws CheckingCopyException, Exception, CheckingCopyException{
		checkingcopyList.update(CheckingCopy);
	}
	
	public List<CheckingCopy> getCheckingCopy() throws RepositoryException{
		return CheckingCopys.getCheckingCopy();
	}
	
	public CheckingCopy searchCheckingCopy(int idEntity) throws CheckingCopyException, RepositoryException, CheckingCopyException{
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