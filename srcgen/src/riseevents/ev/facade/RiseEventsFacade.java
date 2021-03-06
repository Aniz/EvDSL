// Autogenerated by EvDSL
package riseevents.ev.facade;
import riseevents.ev.util.LibraryOfDSL;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.mail.EmailException;
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
import riseevents.ev.data.Speaker;
import riseevents.ev.business.SpeakerControl;
import riseevents.ev.exception.SpeakerAlreadyInsertedException;
import riseevents.ev.exception.SpeakerNotFoundException;
import riseevents.ev.repository.SpeakerRepository;
import riseevents.ev.repository.SpeakerRepositoryBDR;
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
import riseevents.ev.data.Activity;
import riseevents.ev.business.ActivityControl;
import riseevents.ev.exception.ActivityAlreadyInsertedException;
import riseevents.ev.exception.ActivityNotFoundException;
import riseevents.ev.repository.ActivityRepository;
import riseevents.ev.repository.ActivityRepositoryBDR;
import riseevents.ev.data.Payment;
import riseevents.ev.business.PaymentControl;
import riseevents.ev.exception.PaymentAlreadyInsertedException;
import riseevents.ev.exception.PaymentNotFoundException;
import riseevents.ev.repository.PaymentRepository;
import riseevents.ev.repository.PaymentRepositoryBDR;
import riseevents.ev.data.Submission;
import riseevents.ev.business.SubmissionControl;
import riseevents.ev.exception.SubmissionAlreadyInsertedException;
import riseevents.ev.exception.SubmissionNotFoundException;
import riseevents.ev.repository.SubmissionRepository;
import riseevents.ev.repository.SubmissionRepositoryBDR;
import riseevents.ev.data.CheckingCopy;
import riseevents.ev.business.CheckingCopyControl;
import riseevents.ev.exception.CheckingCopyAlreadyInsertedException;
import riseevents.ev.exception.CheckingCopyNotFoundException;
import riseevents.ev.repository.CheckingCopyRepository;
import riseevents.ev.repository.CheckingCopyRepositoryBDR;
import riseevents.ev.data.Author;
import riseevents.ev.business.AuthorControl;
import riseevents.ev.exception.AuthorAlreadyInsertedException;
import riseevents.ev.exception.AuthorNotFoundException;
import riseevents.ev.repository.AuthorRepository;
import riseevents.ev.repository.AuthorRepositoryBDR;
import riseevents.ev.data.Assignment;
import riseevents.ev.business.AssignmentControl;
import riseevents.ev.exception.AssignmentAlreadyInsertedException;
import riseevents.ev.exception.AssignmentNotFoundException;
import riseevents.ev.repository.AssignmentRepository;
import riseevents.ev.repository.AssignmentRepositoryBDR;
import riseevents.ev.data.Receipt;
import riseevents.ev.business.ReceiptControl;
import riseevents.ev.exception.ReceiptAlreadyInsertedException;
import riseevents.ev.exception.ReceiptNotFoundException;
import riseevents.ev.repository.ReceiptRepository;
import riseevents.ev.repository.ReceiptRepositoryBDR;
import riseevents.ev.exception.RepositoryException;
import com.lowagie.text.DocumentException;

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

public class RiseEventsFacade {

	private UserControl userList;
	private OrganizerControl organizerList;
	private SpeakerControl speakerList;
	private ReviewerControl reviewerList;
	private EventControl eventList;
	private ActivityControl activityList;
	private PaymentControl paymentList;
	private SubmissionControl submissionList;
	private CheckingCopyControl checkingcopyList;
	private AuthorControl authorList;
	private AssignmentControl assignmentList;
	private ReceiptControl receiptList;
	private ReviewControl reviewList;
	private ActivityUserControl activityuserList;
	private ActivitySpeakerControl activityspeakerList;
	private ActivityOrganizerControl activityorganizerList;
	private SubmissionAuthorControl submissionauthorList;
	private SubmissionUserControl submissionuserList;
	private RegistrationControl registrationList;

	protected static RiseEventsFacade instance;
	
	public RiseEventsFacade(){
		UserRepository userRepository = UserRepositoryBDR.getInstance();
		OrganizerRepository organizerRepository = OrganizerRepositoryBDR.getInstance();
		SpeakerRepository speakerRepository = SpeakerRepositoryBDR.getInstance();
		ReviewerRepository reviewerRepository = ReviewerRepositoryBDR.getInstance();
		EventRepository eventRepository = EventRepositoryBDR.getInstance();
		ActivityRepository activityRepository = ActivityRepositoryBDR.getInstance();
		PaymentRepository paymentRepository = PaymentRepositoryBDR.getInstance();
		SubmissionRepository submissionRepository = SubmissionRepositoryBDR.getInstance();
		CheckingCopyRepository checkingcopyRepository = CheckingCopyRepositoryBDR.getInstance();
		AuthorRepository authorRepository = AuthorRepositoryBDR.getInstance();
		AssignmentRepository assignmentRepository = AssignmentRepositoryBDR.getInstance();
		ReceiptRepository receiptRepository = ReceiptRepositoryBDR.getInstance();
		
		userList = new UserControl(userRepository); 
		organizerList = new OrganizerControl(organizerRepository); 
		speakerList = new SpeakerControl(speakerRepository); 
		reviewerList = new ReviewerControl(reviewerRepository); 
		eventList = new EventControl(eventRepository); 
		activityList = new ActivityControl(activityRepository); 
		paymentList = new PaymentControl(paymentRepository); 
		submissionList = new SubmissionControl(submissionRepository); 
		checkingcopyList = new CheckingCopyControl(checkingcopyRepository); 
		authorList = new AuthorControl(authorRepository); 
		assignmentList = new AssignmentControl(assignmentRepository); 
		receiptList = new ReceiptControl(receiptRepository); 
	
	
		ReviewRepository reviewRepository = ReviewRepositoryBDR.getInstance();
		ActivityUserRepository activityuserRepository = ActivityUserRepositoryBDR.getInstance();
		ActivitySpeakerRepository activityspeakerRepository = ActivitySpeakerRepositoryBDR.getInstance();
		ActivityOrganizerRepository activityorganizerRepository = ActivityOrganizerRepositoryBDR.getInstance();
		SubmissionAuthorRepository submissionauthorRepository = SubmissionAuthorRepositoryBDR.getInstance();
		SubmissionUserRepository submissionuserRepository = SubmissionUserRepositoryBDR.getInstance();
		RegistrationRepository registrationRepository = RegistrationRepositoryBDR.getInstance();
		
		reviewList = new ReviewControl(reviewRepository); 
		activityuserList = new ActivityUserControl(activityuserRepository); 
		activityspeakerList = new ActivitySpeakerControl(activityspeakerRepository); 
		activityorganizerList = new ActivityOrganizerControl(activityorganizerRepository); 
		submissionauthorList = new SubmissionAuthorControl(submissionauthorRepository); 
		submissionuserList = new SubmissionUserControl(submissionuserRepository); 
		registrationList = new RegistrationControl(registrationRepository); 
	
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
	public void updateUser(User Entity) throws UserNotFoundException, RepositoryException, UserAlreadyInsertedException{
		userList.update(Entity);
	}
	public List<User> getUserList() throws RepositoryException{
		return userList.getUserList();
	}
	public User searchUser(int idEntity) throws UserNotFoundException, RepositoryException, UserAlreadyInsertedException{
		return userList.search(idEntity);
	}
	public boolean isThereUser(int idEntity) throws RepositoryException{
		return userList.isThere(idEntity);
	}
	public void insertOrganizer(Organizer entity) throws OrganizerAlreadyInsertedException, RepositoryException{
		this.organizerList.insert(entity);
	}
	public void removeOrganizer(int idEntity) throws OrganizerNotFoundException, RepositoryException, OrganizerAlreadyInsertedException{
		organizerList.remove(idEntity);  
	}
	public void updateOrganizer(Organizer Entity) throws OrganizerNotFoundException, RepositoryException, OrganizerAlreadyInsertedException{
		organizerList.update(Entity);
	}
	public List<Organizer> getOrganizerList() throws RepositoryException{
		return organizerList.getOrganizerList();
	}
	public Organizer searchOrganizer(int idEntity) throws OrganizerNotFoundException, RepositoryException, OrganizerAlreadyInsertedException{
		return organizerList.search(idEntity);
	}
	public boolean isThereOrganizer(int idEntity) throws RepositoryException{
		return organizerList.isThere(idEntity);
	}
	public void insertSpeaker(Speaker entity) throws SpeakerAlreadyInsertedException, RepositoryException{
		this.speakerList.insert(entity);
	}
	public void removeSpeaker(int idEntity) throws SpeakerNotFoundException, RepositoryException, SpeakerAlreadyInsertedException{
		speakerList.remove(idEntity);  
	}
	public void updateSpeaker(Speaker Entity) throws SpeakerNotFoundException, RepositoryException, SpeakerAlreadyInsertedException{
		speakerList.update(Entity);
	}
	public List<Speaker> getSpeakerList() throws RepositoryException{
		return speakerList.getSpeakerList();
	}
	public Speaker searchSpeaker(int idEntity) throws SpeakerNotFoundException, RepositoryException, SpeakerAlreadyInsertedException{
		return speakerList.search(idEntity);
	}
	public boolean isThereSpeaker(int idEntity) throws RepositoryException{
		return speakerList.isThere(idEntity);
	}
	public void insertReviewer(Reviewer entity) throws ReviewerAlreadyInsertedException, RepositoryException{
		this.reviewerList.insert(entity);
	}
	public void removeReviewer(int idEntity) throws ReviewerNotFoundException, RepositoryException, ReviewerAlreadyInsertedException{
		reviewerList.remove(idEntity);  
	}
	public void updateReviewer(Reviewer Entity) throws ReviewerNotFoundException, RepositoryException, ReviewerAlreadyInsertedException{
		reviewerList.update(Entity);
	}
	public List<Reviewer> getReviewerList() throws RepositoryException{
		return reviewerList.getReviewerList();
	}
	public Reviewer searchReviewer(int idEntity) throws ReviewerNotFoundException, RepositoryException, ReviewerAlreadyInsertedException{
		return reviewerList.search(idEntity);
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
	public void updateEvent(Event Entity) throws EventNotFoundException, RepositoryException, EventAlreadyInsertedException{
		eventList.update(Entity);
	}
	public List<Event> getEventList() throws RepositoryException{
		return eventList.getEventList();
	}
	public Event searchEvent(int idEntity) throws EventNotFoundException, RepositoryException, EventAlreadyInsertedException{
		return eventList.search(idEntity);
	}
	public int getEventLastId() throws RepositoryException{
		return eventList.getEventLastId();
	}
	public boolean isThereEvent(int idEntity) throws RepositoryException{
		return eventList.isThere(idEntity);
	}
	public void insertActivity(Activity entity) throws ActivityAlreadyInsertedException, RepositoryException{
		this.activityList.insert(entity);
	}
	public void removeActivity(int idEntity) throws ActivityNotFoundException, RepositoryException, ActivityAlreadyInsertedException{
		activityList.remove(idEntity);  
	}
	public void updateActivity(Activity Entity) throws ActivityNotFoundException, RepositoryException, ActivityAlreadyInsertedException{
		activityList.update(Entity);
	}
	public List<Activity> getActivityList() throws RepositoryException{
		return activityList.getActivityList();
	}
	public Activity searchActivity(int idEntity) throws ActivityNotFoundException, RepositoryException, ActivityAlreadyInsertedException{
		return activityList.search(idEntity);
	}
	public int getActivityLastId() throws RepositoryException{
		return activityList.getActivityLastId();
	}
	public boolean isThereActivity(int idEntity) throws RepositoryException{
		return activityList.isThere(idEntity);
	}
	public void insertPayment(Payment entity) throws PaymentAlreadyInsertedException, RepositoryException{
		this.paymentList.insert(entity);
	}
	public void removePayment(int idEntity) throws PaymentNotFoundException, RepositoryException, PaymentAlreadyInsertedException{
		paymentList.remove(idEntity);  
	}
	public void updatePayment(Payment Entity) throws PaymentNotFoundException, RepositoryException, PaymentAlreadyInsertedException{
		paymentList.update(Entity);
	}
	public List<Payment> getPaymentList() throws RepositoryException{
		return paymentList.getPaymentList();
	}
	public Payment searchPayment(int idEntity) throws PaymentNotFoundException, RepositoryException, PaymentAlreadyInsertedException{
		return paymentList.search(idEntity);
	}
	public int getPaymentLastId() throws RepositoryException{
		return paymentList.getPaymentLastId();
	}
	public void removeSubmission(int idEntity) throws SubmissionNotFoundException, RepositoryException, SubmissionAlreadyInsertedException{
		submissionList.remove(idEntity);  
	}
	public List<Submission> getSubmissionList() throws RepositoryException{
		return submissionList.getSubmissionList();
	}
	public Submission searchSubmission(int idEntity) throws SubmissionNotFoundException, RepositoryException, SubmissionAlreadyInsertedException{
		return submissionList.search(idEntity);
	}
	public int getSubmissionLastId() throws RepositoryException{
		return submissionList.getSubmissionLastId();
	}
	public void insertCheckingCopy(CheckingCopy entity) throws CheckingCopyAlreadyInsertedException, RepositoryException{
		this.checkingcopyList.insert(entity);
	}
	public void removeCheckingCopy(int idEntity) throws CheckingCopyNotFoundException, RepositoryException, CheckingCopyAlreadyInsertedException{
		checkingcopyList.remove(idEntity);  
	}
	public void updateCheckingCopy(CheckingCopy Entity) throws CheckingCopyNotFoundException, RepositoryException, CheckingCopyAlreadyInsertedException{
		checkingcopyList.update(Entity);
	}
	public List<CheckingCopy> getCheckingCopyList() throws RepositoryException{
		return checkingcopyList.getCheckingCopyList();
	}
	public CheckingCopy searchCheckingCopy(int idEntity) throws CheckingCopyNotFoundException, RepositoryException, CheckingCopyAlreadyInsertedException{
		return checkingcopyList.search(idEntity);
	}
	public int getCheckingCopyLastId() throws RepositoryException{
		return checkingcopyList.getCheckingCopyLastId();
	}
	public List<Author> getAuthorList() throws RepositoryException{
		return authorList.getAuthorList();
	}
	public int getAuthorLastId() throws RepositoryException{
		return authorList.getAuthorLastId();
	}
	public boolean isThereAuthor(int idEntity) throws RepositoryException{
		return authorList.isThere(idEntity);
	}
	public void insertAssignment(Assignment entity) throws AssignmentAlreadyInsertedException, RepositoryException{
		this.assignmentList.insert(entity);
	}
	public List<Assignment> getAssignmentList() throws RepositoryException{
		return assignmentList.getAssignmentList();
	}
	public void insertReceipt(Receipt entity) throws ReceiptAlreadyInsertedException, RepositoryException{
		this.receiptList.insert(entity);
	}
	public void removeReceipt(int idEntity) throws ReceiptNotFoundException, RepositoryException, ReceiptAlreadyInsertedException{
		receiptList.remove(idEntity);  
	}
	public void updateReceipt(Receipt Entity) throws ReceiptNotFoundException, RepositoryException, ReceiptAlreadyInsertedException{
		receiptList.update(Entity);
	}
	public List<Receipt> getReceiptList() throws RepositoryException{
		return receiptList.getReceiptList();
	}
	public Receipt searchReceipt(int idEntity) throws ReceiptNotFoundException, RepositoryException, ReceiptAlreadyInsertedException{
		return receiptList.search(idEntity);
	}
	public int getReceiptLastId() throws RepositoryException{
		return receiptList.getReceiptLastId();
	}
	public void insertReview(Review entity) throws ReviewAlreadyInsertedException, RepositoryException{
		this.reviewList.insert(entity);
	}
	public void updateReview(Review Entity) throws ReviewNotFoundException, RepositoryException, ReviewAlreadyInsertedException{
		reviewList.update(Entity);
	}
	public List<Review> getReviewList() throws RepositoryException{
		return reviewList.getReviewList();
	}
	public int getReviewLastId() throws RepositoryException{
		return reviewList.getReviewLastId();
	}
	public void insertActivityUser(ActivityUser entity) throws ActivityUserAlreadyInsertedException, RepositoryException{
		this.activityuserList.insert(entity);
	}
	public void removeActivityUser(ActivityUser entity) throws ActivityUserNotFoundException, RepositoryException, ActivityUserAlreadyInsertedException{
		activityuserList.remove(entity);  
	}
	public void updateActivityUser(ActivityUser Entity) throws ActivityUserNotFoundException, RepositoryException, ActivityUserAlreadyInsertedException{
		activityuserList.update(Entity);
	}
	public List<ActivityUser> getActivityUserList() throws RepositoryException{
		return activityuserList.getActivityUserList();
	}
	public int getActivityUserLastId() throws RepositoryException{
		return activityuserList.getActivityUserLastId();
	}
	public boolean isThereActivityUser(ActivityUser entity) throws RepositoryException{
		return activityuserList.isThere(entity);
	}
	public void insertActivitySpeaker(ActivitySpeaker entity) throws ActivitySpeakerAlreadyInsertedException, RepositoryException{
		this.activityspeakerList.insert(entity);
	}
	public void removeActivitySpeaker(ActivitySpeaker entity) throws ActivitySpeakerNotFoundException, RepositoryException, ActivitySpeakerAlreadyInsertedException{
		activityspeakerList.remove(entity);  
	}
	public void updateActivitySpeaker(ActivitySpeaker Entity) throws ActivitySpeakerNotFoundException, RepositoryException, ActivitySpeakerAlreadyInsertedException{
		activityspeakerList.update(Entity);
	}
	public List<ActivitySpeaker> getActivitySpeakerList() throws RepositoryException{
		return activityspeakerList.getActivitySpeakerList();
	}
	public ActivitySpeaker searchActivitySpeaker(ActivitySpeaker Entity) throws ActivitySpeakerNotFoundException, RepositoryException, ActivitySpeakerAlreadyInsertedException{
		return activityspeakerList.search(Entity);
	}
	public int getActivitySpeakerLastId() throws RepositoryException{
		return activityspeakerList.getActivitySpeakerLastId();
	}
	public boolean isThereActivitySpeaker(ActivitySpeaker entity) throws RepositoryException{
		return activityspeakerList.isThere(entity);
	}
	public void insertActivityOrganizer(ActivityOrganizer entity) throws ActivityOrganizerAlreadyInsertedException, RepositoryException{
		this.activityorganizerList.insert(entity);
	}
	public void removeActivityOrganizer(ActivityOrganizer entity) throws ActivityOrganizerNotFoundException, RepositoryException, ActivityOrganizerAlreadyInsertedException{
		activityorganizerList.remove(entity);  
	}
	public void updateActivityOrganizer(ActivityOrganizer Entity) throws ActivityOrganizerNotFoundException, RepositoryException, ActivityOrganizerAlreadyInsertedException{
		activityorganizerList.update(Entity);
	}
	public List<ActivityOrganizer> getActivityOrganizerList() throws RepositoryException{
		return activityorganizerList.getActivityOrganizerList();
	}
	public ActivityOrganizer searchActivityOrganizer(ActivityOrganizer Entity) throws ActivityOrganizerNotFoundException, RepositoryException, ActivityOrganizerAlreadyInsertedException{
		return activityorganizerList.search(Entity);
	}
	public int getActivityOrganizerLastId() throws RepositoryException{
		return activityorganizerList.getActivityOrganizerLastId();
	}
	public boolean isThereActivityOrganizer(ActivityOrganizer entity) throws RepositoryException{
		return activityorganizerList.isThere(entity);
	}
	public void insertSubmissionAuthor(SubmissionAuthor entity) throws SubmissionAuthorAlreadyInsertedException, RepositoryException{
		this.submissionauthorList.insert(entity);
	}
	public void removeSubmissionAuthor(SubmissionAuthor entity) throws SubmissionAuthorNotFoundException, RepositoryException, SubmissionAuthorAlreadyInsertedException{
		submissionauthorList.remove(entity);  
	}
	public void updateSubmissionAuthor(SubmissionAuthor Entity) throws SubmissionAuthorNotFoundException, RepositoryException, SubmissionAuthorAlreadyInsertedException{
		submissionauthorList.update(Entity);
	}
	public List<SubmissionAuthor> getSubmissionAuthorList() throws RepositoryException{
		return submissionauthorList.getSubmissionAuthorList();
	}
	public SubmissionAuthor searchSubmissionAuthor(SubmissionAuthor Entity) throws SubmissionAuthorNotFoundException, RepositoryException, SubmissionAuthorAlreadyInsertedException{
		return submissionauthorList.search(Entity);
	}
	public boolean isThereSubmissionAuthor(SubmissionAuthor entity) throws RepositoryException{
		return submissionauthorList.isThere(entity);
	}
	public void insertSubmissionUser(SubmissionUser entity) throws SubmissionUserAlreadyInsertedException, RepositoryException{
		this.submissionuserList.insert(entity);
	}
	public void removeSubmissionUser(SubmissionUser entity) throws SubmissionUserNotFoundException, RepositoryException, SubmissionUserAlreadyInsertedException{
		submissionuserList.remove(entity);  
	}
	public void updateSubmissionUser(SubmissionUser Entity) throws SubmissionUserNotFoundException, RepositoryException, SubmissionUserAlreadyInsertedException{
		submissionuserList.update(Entity);
	}
	public List<SubmissionUser> getSubmissionUserList() throws RepositoryException{
		return submissionuserList.getSubmissionUserList();
	}
	public SubmissionUser searchSubmissionUser(SubmissionUser Entity) throws SubmissionUserNotFoundException, RepositoryException, SubmissionUserAlreadyInsertedException{
		return submissionuserList.search(Entity);
	}
	public boolean isThereSubmissionUser(SubmissionUser entity) throws RepositoryException{
		return submissionuserList.isThere(entity);
	}
	public void insertRegistration(Registration entity) throws RegistrationAlreadyInsertedException, RepositoryException{
		this.registrationList.insert(entity);
	}
	public void updateRegistration(Registration Entity) throws RegistrationNotFoundException, RepositoryException, RegistrationAlreadyInsertedException{
		registrationList.update(Entity);
	}
	public List<Registration> getRegistrationList() throws RepositoryException{
		return registrationList.getRegistrationList();
	}
	public int getRegistrationLastId() throws RepositoryException{
		return registrationList.getRegistrationLastId();
	}
	public boolean isThereRegistration(Registration entity) throws RepositoryException{
		return registrationList.isThere(entity);
	}
	public List<ActivityOrganizer> getActivitiesOrganizersById(int idActivity) throws RepositoryException{
		return activityorganizerList.getActivitiesById(idActivity);
	}
	public List<ActivitySpeaker> getActivitiesSpeakerById(int idActivity) throws RepositoryException{
		return activityspeakerList.getActivitiesById(idActivity);
	}
	public List<ActivityUser> getActivitiesUsersById(int idActivity) throws RepositoryException{
		return activityuserList.getActivitiesById(idActivity);
	}
 	public List<Activity> getActivitiesByEvent(int idEvent) throws RepositoryException{
		return activityList.getActivitiesByEvent(idEvent);
	}
	
	public int getEventIdByName(String entityName) throws RepositoryException{
		return eventList.getEventIdByName(entityName);
	}
	
	public int getUserIdByName(String entityName) throws RepositoryException{
		return userList.getUserIdByName(entityName);
	}
	
	public int getActivityIdByName(String entityName) throws RepositoryException{
		return activityList.getActivityIdByName(entityName);
	}

	public float getEventMainTrackValue(int idEvent) throws RepositoryException{
		return activityList.getEventMainTrackValue(idEvent);
	}
	
	public int getActivityMainTrackId(int idEvent) throws RepositoryException{
		return activityList.getActivityMainTrackId(idEvent);
	}
	
	public int getEventbyActivity(int idActivity) throws RepositoryException{
		return activityList.getEventbyActivity(idActivity);
	}
	
	public void frequencyPerActivity(List<String> ParticipantsPerActivity, Activity activity, String eventName) throws DocumentException, IOException{
		activityList.frequencyPerActivity(ParticipantsPerActivity, activity, eventName);
	}
	public List<String> getParticipantsPerActivity(int idActivity) throws RepositoryException{
		return activityList.getParticipantsPerActivity(idActivity);
	}
	public List<String> getListOfAuthorsPerActivity(int idActivity) throws RepositoryException{
		return activityList.getListOfAuthorsPerActivity(idActivity);
	}
	
	public void listofAuthorsPerActivity(Set<String> authorsPerActivity, Activity activity) throws DocumentException, IOException{
		activity.listofAuthorsPerActivity(authorsPerActivity);
	}
	
public Author searchAuthor(int idAuthor) throws AuthorNotFoundException, RepositoryException, AuthorAlreadyInsertedException{
	return authorList.search(idAuthor);
} 
	
	
	public void removeValue(float value, int idRegistration) throws RepositoryException{
		registrationList.removeValue(value, idRegistration);
	}
	public void addValue(float value, int idRegistration) throws RepositoryException{
		registrationList.addValue(value, idRegistration);
	}
	public void removeRegistration(int idRegistration) throws RegistrationNotFoundException, RepositoryException, RegistrationAlreadyInsertedException{
		registrationList.remove(idRegistration);  
	}
	public int searchRegistration(int idUser, int idEvent) throws RegistrationNotFoundException, RepositoryException{
		return registrationList.search(idUser, idEvent);
	}
	public Registration searchRegistration(int idRegistration) throws RegistrationNotFoundException, RepositoryException, RegistrationAlreadyInsertedException{
		return registrationList.search(idRegistration);
	}
	
	public void typePayment(Payment payment, Payment paymentout) throws DocumentException, IOException {
		this.paymentList.type(payment, paymentout);
	}
	
	
	public void insertAttachment(File attachment, int idActivity) throws RepositoryException, SubmissionAlreadyInsertedException{
		this.submissionList.inserAttachmanet(attachment, idActivity);
	}
	public void pdfRecovey(int idSubmission) throws RepositoryException{
		this.submissionList.pdfRecover(idSubmission);
	}
	public List<Submission> getSubmissionsByUser(int idUser) throws RepositoryException{
		return submissionList.getSubmissionsByUser(idUser);
	}
	public int getSubmissionIdByTitle(String submissionTitle) throws RepositoryException{
		return submissionList.getSubmissionIdByTitle(submissionTitle);
	}
	public void insertSubmission(Submission submission) throws RepositoryException, SubmissionAlreadyInsertedException{
		this.submissionList.insert(submission);
	}
	public void updateSubmission(Submission submission) throws SubmissionNotFoundException, RepositoryException, SubmissionAlreadyInsertedException{
		submissionList.update(submission);
	}	
	public void insertAuthor(Author author) throws AuthorAlreadyInsertedException, RepositoryException{
		authorList.insert(author);
	}
	public void removeAssignment(Assignment assignment) throws AssignmentNotFoundException, RepositoryException, AssignmentAlreadyInsertedException{
		assignmentList.remove(assignment);  
	}
	public Assignment searchAssignment(Assignment assignment) throws AssignmentNotFoundException, RepositoryException, AssignmentAlreadyInsertedException{
		return assignmentList.search(assignment);
	}
	public boolean isThereAssignment(Assignment assignment) throws RepositoryException{
		return assignmentList.isThere(assignment);
	}
	
	public void removeReview(int idReview) throws ReviewNotFoundException, RepositoryException, ReviewAlreadyInsertedException{
		reviewList.remove(idReview);  
	}
	public Review searchReview(int idReview) throws ReviewNotFoundException, RepositoryException, ReviewAlreadyInsertedException{
		return reviewList.search(idReview);
	}
			
	public List<String> getReviewsBySubmission(int idSubmission) throws RepositoryException{
		return reviewList.getReviewsBySubmission(idSubmission);
	}

	public Reviewer getReviewerByknowledgeArea(String knowledgearea) throws ReviewerNotFoundException, RepositoryException, ReviewerAlreadyInsertedException{
		return reviewerList.getReviewerByknowledgeArea(knowledgearea);
	}
	
	public void generateProgram (List<Activity> activities, Event event) throws DocumentException, IOException{
		eventList.generateProgram(activities, event);
	}
	
	public void generateImportantDates(String abstractDate, String fullPaperDate, String notificationDate, Event event) throws DocumentException, IOException{
		eventList.generateImportantDates(abstractDate, fullPaperDate, notificationDate, event);
	}
	public List<String> getParticipantsPerEvent(int idEvent) throws RepositoryException{
		return eventList.getParticipantsPerEvent(idEvent);
	}
	public void frequencyPerEvent(List<String> ParticipantsPerEvent, Event event) throws DocumentException, IOException{
		eventList.frequencyPerEvent(ParticipantsPerEvent, event);
	}
	
}