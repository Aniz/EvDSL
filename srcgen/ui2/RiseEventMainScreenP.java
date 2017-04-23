package rise.splcc.ui2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import rise.splcc.facade.RiSEventFacade;

public class RiSEEventMainScreenP extends JFrame {

	private JPanel contentPane;
	
    public static RiSEventFacade facade; // caso a variabilidade de tela login seja retirada o sistema deve iniciar dessa tela
	
	private static RiSEEventMainScreenP instanceRiSEEventMainScreenP;      
	
	private UserInsertScreenP screenInsertUser;	
	private UserUpdateScreenP screenUpdateUser;	
	private UserSearchScreenP screenSearchUser;	
	private UserRemoveScreenP screenRemoveUser;	
	private UserListAllScreenP screenListAllUser;	

	private SpeakerInsertScreenP screenInsertSpeaker;	
	private SpeakerUpdateScreenP screenUpdateSpeaker;	
	private SpeakerSearchScreenP screenSearchSpeaker;	
	private SpeakerRemoveScreenP screenRemoveSpeaker;	
	private SpeakerListAllScreenP screenListAllSpeaker;	

	private OrganizerInsertScreenP screenInsertOrganizer;	
	private OrganizerUpdateScreenP screenUpdateOrganizer;	
	private OrganizerSearchScreenP screenSearchOrganizer;	
	private OrganizerRemoveScreenP screenRemoveOrganizer;	
	private OrganizerListAllScreenP screenListAllOrganizer;	

	private ReviewerInsertScreenP screenInsertReviewer;	
	private ReviewerUpdateScreenP screenUpdateReviewer;	
	private ReviewerSearchScreenP screenSearchReviewer;	
	private ReviewerRemoveScreenP screenRemoveReviewer;	
	private ReviewerListAllScreenP screenListAllReviewer;	

	private EventInsertScreenP screenInsertEvent;	
	private EventUpdateScreenP screenUpdateEvent;	
	private EventSearchScreenP screenSearchEvent;	
	private EventRemoveScreenP screenRemoveEvent;	
	private EventListAllScreenP screenListAllEvent;	
	private EventReportslistofauthorsScreenP screenReportslistofauthors;	
	private EventReportsfrequencyperactivityScreenP screenReportsfrequencyperactivity;	
	private EventReportsfrequencypereventScreenP screenReportsfrequencyperevent;	

	private PaymentInsertScreenP screenInsertPayment;	
	private PaymentUpdateScreenP screenUpdatePayment;	
	private PaymentSearchScreenP screenSearchPayment;	
	private PaymentRemoveScreenP screenRemovePayment;	
	private PaymentListAllScreenP screenListAllPayment;	
	private PaymentReportslistofauthorsScreenP screenReportslistofauthors;	
	private PaymentReportsfrequencyperactivityScreenP screenReportsfrequencyperactivity;	
	private PaymentReportsfrequencypereventScreenP screenReportsfrequencyperevent;	

	private ActivityInsertScreenP screenInsertActivity;	
	private ActivityUpdateScreenP screenUpdateActivity;	
	private ActivitySearchScreenP screenSearchActivity;	
	private ActivityRemoveScreenP screenRemoveActivity;	
	private ActivityListAllScreenP screenListAllActivity;	
	private ActivityReportslistofauthorsScreenP screenReportslistofauthors;	
	private ActivityReportsfrequencyperactivityScreenP screenReportsfrequencyperactivity;	
	private ActivityReportsfrequencypereventScreenP screenReportsfrequencyperevent;	

	private AssignmentInsertScreenP screenInsertAssignment;	
	private AssignmentSearchScreenP screenSearchAssignment;	
	private AssignmentRemoveScreenP screenRemoveAssignment;	
	private AssignmentListAllScreenP screenListAllAssignment;	
	private AssignmentReportslistofauthorsScreenP screenReportslistofauthors;	
	private AssignmentReportsfrequencyperactivityScreenP screenReportsfrequencyperactivity;	
	private AssignmentReportsfrequencypereventScreenP screenReportsfrequencyperevent;	

	private SubmissionSearchScreenP screenSearchSubmission;	
	private SubmissionRemoveScreenP screenRemoveSubmission;	
	private SubmissionListAllScreenP screenListAllSubmission;	
	private SubmissionReportslistofauthorsScreenP screenReportslistofauthors;	
	private SubmissionReportsfrequencyperactivityScreenP screenReportsfrequencyperactivity;	
	private SubmissionReportsfrequencypereventScreenP screenReportsfrequencyperevent;	


	private CheckingCopyInsertScreenP screenInsertCheckingCopy;	
	private CheckingCopyUpdateScreenP screenUpdateCheckingCopy;	
	private CheckingCopyRemoveScreenP screenRemoveCheckingCopy;	
	private CheckingCopySearchScreenP screenSearchCheckingCopy;	
	private CheckingCopyListAllScreenP screenListAllCheckingCopy;	
	private CheckingCopyReportslistofauthorsScreenP screenReportslistofauthors;	
	private CheckingCopyReportsfrequencyperactivityScreenP screenReportsfrequencyperactivity;	
	private CheckingCopyReportsfrequencypereventScreenP screenReportsfrequencyperevent;	


	private ReceiptScreenP screenReceipt;
	
	
	private static JLabel labelImagem;
	
	private JDesktopPane desktopPane;
	
	
	public static RiSEventFacade getFacade(){
		return RiSEventFacade.getInstance();
	}
	
	public static RiSEEventMainScreenP getInstanceRiSEEventMainScreenP() {
		if (instanceRiSEEventMainScreenP == null) {
			RiSEEventMainScreenP.instanceRiSEEventMainScreenP = new RiSEEventMainScreenP();
		}
		return RiSEEventMainScreenP.instanceRiSEEventMainScreenP;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RiSEEventMainScreenP frame = new RiSEEventMainScreenP();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RiSEEventMainScreenP() {
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		ExitMenuAction exitMenuAction = new ExitMenuAction();
		
		InsertUserMenuAction insertUserAction = new InsertMenuAction();	
		UpdateUserMenuAction updateUserAction = new UpdateMenuAction();	
		SearchUserMenuAction searchUserAction = new SearchMenuAction();	
		RemoveUserMenuAction removeUserAction = new RemoveMenuAction();	
		ListAllUserMenuAction listallUserAction = new ListAllMenuAction();	

		InsertSpeakerMenuAction insertSpeakerAction = new InsertMenuAction();	
		UpdateSpeakerMenuAction updateSpeakerAction = new UpdateMenuAction();	
		SearchSpeakerMenuAction searchSpeakerAction = new SearchMenuAction();	
		RemoveSpeakerMenuAction removeSpeakerAction = new RemoveMenuAction();	
		ListAllSpeakerMenuAction listallSpeakerAction = new ListAllMenuAction();	

		InsertOrganizerMenuAction insertOrganizerAction = new InsertMenuAction();	
		UpdateOrganizerMenuAction updateOrganizerAction = new UpdateMenuAction();	
		SearchOrganizerMenuAction searchOrganizerAction = new SearchMenuAction();	
		RemoveOrganizerMenuAction removeOrganizerAction = new RemoveMenuAction();	
		ListAllOrganizerMenuAction listallOrganizerAction = new ListAllMenuAction();	

		InsertReviewerMenuAction insertReviewerAction = new InsertMenuAction();	
		UpdateReviewerMenuAction updateReviewerAction = new UpdateMenuAction();	
		SearchReviewerMenuAction searchReviewerAction = new SearchMenuAction();	
		RemoveReviewerMenuAction removeReviewerAction = new RemoveMenuAction();	
		ListAllReviewerMenuAction listallReviewerAction = new ListAllMenuAction();	

		InsertEventMenuAction insertEventAction = new InsertMenuAction();	
		UpdateEventMenuAction updateEventAction = new UpdateMenuAction();	
		SearchEventMenuAction searchEventAction = new SearchMenuAction();	
		RemoveEventMenuAction removeEventAction = new RemoveMenuAction();	
		ListAllEventMenuAction listallEventAction = new ListAllMenuAction();	
		ReportslistofauthorsMenuAction reportslistofauthorsAction = new reportsListofAuthorsMenuAction();	
		ReportsfrequencyperactivityMenuAction reportsfrequencyperactivityAction = new reportsFrequencyperActivityMenuAction();	
		ReportsfrequencypereventMenuAction reportsfrequencypereventAction = new reportsFrequencyperEventMenuAction();	

		InsertPaymentMenuAction insertPaymentAction = new InsertMenuAction();	
		UpdatePaymentMenuAction updatePaymentAction = new UpdateMenuAction();	
		SearchPaymentMenuAction searchPaymentAction = new SearchMenuAction();	
		RemovePaymentMenuAction removePaymentAction = new RemoveMenuAction();	
		ListAllPaymentMenuAction listallPaymentAction = new ListAllMenuAction();	
		ReportslistofauthorsMenuAction reportslistofauthorsAction = new reportsListofAuthorsMenuAction();	
		ReportsfrequencyperactivityMenuAction reportsfrequencyperactivityAction = new reportsFrequencyperActivityMenuAction();	
		ReportsfrequencypereventMenuAction reportsfrequencypereventAction = new reportsFrequencyperEventMenuAction();	

		InsertActivityMenuAction insertActivityAction = new InsertMenuAction();	
		UpdateActivityMenuAction updateActivityAction = new UpdateMenuAction();	
		SearchActivityMenuAction searchActivityAction = new SearchMenuAction();	
		RemoveActivityMenuAction removeActivityAction = new RemoveMenuAction();	
		ListAllActivityMenuAction listallActivityAction = new ListAllMenuAction();	
		ReportslistofauthorsMenuAction reportslistofauthorsAction = new reportsListofAuthorsMenuAction();	
		ReportsfrequencyperactivityMenuAction reportsfrequencyperactivityAction = new reportsFrequencyperActivityMenuAction();	
		ReportsfrequencypereventMenuAction reportsfrequencypereventAction = new reportsFrequencyperEventMenuAction();	

		InsertAssignmentMenuAction insertAssignmentAction = new InsertMenuAction();	
		SearchAssignmentMenuAction searchAssignmentAction = new SearchMenuAction();	
		RemoveAssignmentMenuAction removeAssignmentAction = new RemoveMenuAction();	
		ListAllAssignmentMenuAction listallAssignmentAction = new ListAllMenuAction();	
		ReportslistofauthorsMenuAction reportslistofauthorsAction = new reportsListofAuthorsMenuAction();	
		ReportsfrequencyperactivityMenuAction reportsfrequencyperactivityAction = new reportsFrequencyperActivityMenuAction();	
		ReportsfrequencypereventMenuAction reportsfrequencypereventAction = new reportsFrequencyperEventMenuAction();	

		SearchSubmissionMenuAction searchSubmissionAction = new SearchMenuAction();	
		RemoveSubmissionMenuAction removeSubmissionAction = new RemoveMenuAction();	
		ListAllSubmissionMenuAction listallSubmissionAction = new ListAllMenuAction();	
		ReportslistofauthorsMenuAction reportslistofauthorsAction = new reportsListofAuthorsMenuAction();	
		ReportsfrequencyperactivityMenuAction reportsfrequencyperactivityAction = new reportsFrequencyperActivityMenuAction();	
		ReportsfrequencypereventMenuAction reportsfrequencypereventAction = new reportsFrequencyperEventMenuAction();	


		InsertCheckingCopyMenuAction insertCheckingCopyAction = new InsertMenuAction();	
		UpdateCheckingCopyMenuAction updateCheckingCopyAction = new UpdateMenuAction();	
		RemoveCheckingCopyMenuAction removeCheckingCopyAction = new RemoveMenuAction();	
		SearchCheckingCopyMenuAction searchCheckingCopyAction = new SearchMenuAction();	
		ListAllCheckingCopyMenuAction listallCheckingCopyAction = new ListAllMenuAction();	
		ReportslistofauthorsMenuAction reportslistofauthorsAction = new reportsListofAuthorsMenuAction();	
		ReportsfrequencyperactivityMenuAction reportsfrequencyperactivityAction = new reportsFrequencyperActivityMenuAction();	
		ReportsfrequencypereventMenuAction reportsfrequencypereventAction = new reportsFrequencyperEventMenuAction();	


		//#if ${Bugs} == "T"
		BugtrackScreenMenuAction bugtrackAction = new BugtrackScreenMenuAction();
		//#endif
		
		RiSEEventMainScreenP.facade = RiSEventFacade.getInstance();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1120, 691);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		desktopPane.setBounds(6, 36, 1104, 624);
		contentPane.add(desktopPane);
		
		labelImagem = new JLabel("");
		labelImagem.setBounds(210, 164, 648, 193);
		ImageIcon image = new ImageIcon(getClass().getResource("/images/riseLabs.png"));
		Image imag = image.getImage().getScaledInstance(labelImagem.getWidth(), labelImagem.getHeight(), Image.SCALE_SMOOTH);
		labelImagem.setIcon(new ImageIcon(imag));
		desktopPane.add(labelImagem);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(32, 12, 1078, 22);
		contentPane.add(menuBar);
		
		JMenu mnArchieve = new JMenu("Archieve");
		menuBar.add(mnArchieve);
		
		//#if ${Bugs} == "T"		
		JMenuItem mntmBugtrack = new JMenuItem("Bugtrack");
		mnArchieve.add(mntmBugtrack);
		//#endif
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnArchieve.add(mntmExit);

		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);
		JMenuItem mntmInsertUser = new JMenuItem("Insert");
		mnUser.add(mntmInsertUser);
		mntmInsert.addActionListener(insertUserAction);
		JMenuItem mntmUpdateUser = new JMenuItem("Update");
		mnUser.add(mntmUpdateUser);
		mntmUpdate.addActionListener(updateUserAction);
		JMenuItem mntmSearchUser = new JMenuItem("Search");
		mnUser.add(mntmSearchUser);
		mntmSearch.addActionListener(searchUserAction);
		JMenuItem mntmRemoveUser = new JMenuItem("Remove");
		mnUser.add(mntmRemoveUser);
		mntmRemove.addActionListener(removeUserAction);
		JMenuItem mntmListAllUser = new JMenuItem("ListAll");
		mnUser.add(mntmListAllUser);
		mntmListAll.addActionListener(listallUserAction);
		JMenu mnSpeaker = new JMenu("Speaker");
		menuBar.add(mnSpeaker);
		JMenuItem mntmInsertSpeaker = new JMenuItem("Insert");
		mnSpeaker.add(mntmInsertSpeaker);
		mntmInsert.addActionListener(insertSpeakerAction);
		JMenuItem mntmUpdateSpeaker = new JMenuItem("Update");
		mnSpeaker.add(mntmUpdateSpeaker);
		mntmUpdate.addActionListener(updateSpeakerAction);
		JMenuItem mntmSearchSpeaker = new JMenuItem("Search");
		mnSpeaker.add(mntmSearchSpeaker);
		mntmSearch.addActionListener(searchSpeakerAction);
		JMenuItem mntmRemoveSpeaker = new JMenuItem("Remove");
		mnSpeaker.add(mntmRemoveSpeaker);
		mntmRemove.addActionListener(removeSpeakerAction);
		JMenuItem mntmListAllSpeaker = new JMenuItem("ListAll");
		mnSpeaker.add(mntmListAllSpeaker);
		mntmListAll.addActionListener(listallSpeakerAction);
		JMenu mnOrganizer = new JMenu("Organizer");
		menuBar.add(mnOrganizer);
		JMenuItem mntmInsertOrganizer = new JMenuItem("Insert");
		mnOrganizer.add(mntmInsertOrganizer);
		mntmInsert.addActionListener(insertOrganizerAction);
		JMenuItem mntmUpdateOrganizer = new JMenuItem("Update");
		mnOrganizer.add(mntmUpdateOrganizer);
		mntmUpdate.addActionListener(updateOrganizerAction);
		JMenuItem mntmSearchOrganizer = new JMenuItem("Search");
		mnOrganizer.add(mntmSearchOrganizer);
		mntmSearch.addActionListener(searchOrganizerAction);
		JMenuItem mntmRemoveOrganizer = new JMenuItem("Remove");
		mnOrganizer.add(mntmRemoveOrganizer);
		mntmRemove.addActionListener(removeOrganizerAction);
		JMenuItem mntmListAllOrganizer = new JMenuItem("ListAll");
		mnOrganizer.add(mntmListAllOrganizer);
		mntmListAll.addActionListener(listallOrganizerAction);
		JMenu mnReviewer = new JMenu("Reviewer");
		menuBar.add(mnReviewer);
		JMenuItem mntmInsertReviewer = new JMenuItem("Insert");
		mnReviewer.add(mntmInsertReviewer);
		mntmInsert.addActionListener(insertReviewerAction);
		JMenuItem mntmUpdateReviewer = new JMenuItem("Update");
		mnReviewer.add(mntmUpdateReviewer);
		mntmUpdate.addActionListener(updateReviewerAction);
		JMenuItem mntmSearchReviewer = new JMenuItem("Search");
		mnReviewer.add(mntmSearchReviewer);
		mntmSearch.addActionListener(searchReviewerAction);
		JMenuItem mntmRemoveReviewer = new JMenuItem("Remove");
		mnReviewer.add(mntmRemoveReviewer);
		mntmRemove.addActionListener(removeReviewerAction);
		JMenuItem mntmListAllReviewer = new JMenuItem("ListAll");
		mnReviewer.add(mntmListAllReviewer);
		mntmListAll.addActionListener(listallReviewerAction);
		JMenu mnEvent = new JMenu("Event");
		menuBar.add(mnEvent);
		JMenuItem mntmInsertEvent = new JMenuItem("Insert");
		mnEvent.add(mntmInsertEvent);
		mntmInsert.addActionListener(insertEventAction);
		JMenuItem mntmUpdateEvent = new JMenuItem("Update");
		mnEvent.add(mntmUpdateEvent);
		mntmUpdate.addActionListener(updateEventAction);
		JMenuItem mntmSearchEvent = new JMenuItem("Search");
		mnEvent.add(mntmSearchEvent);
		mntmSearch.addActionListener(searchEventAction);
		JMenuItem mntmRemoveEvent = new JMenuItem("Remove");
		mnEvent.add(mntmRemoveEvent);
		mntmRemove.addActionListener(removeEventAction);
		JMenuItem mntmListAllEvent = new JMenuItem("ListAll");
		mnEvent.add(mntmListAllEvent);
		mntmListAll.addActionListener(listallEventAction);
		JMenu mnPayment = new JMenu("Payment");
		menuBar.add(mnPayment);
		JMenuItem mntmInsertPayment = new JMenuItem("Insert");
		mnPayment.add(mntmInsertPayment);
		mntmInsert.addActionListener(insertPaymentAction);
		JMenuItem mntmUpdatePayment = new JMenuItem("Update");
		mnPayment.add(mntmUpdatePayment);
		mntmUpdate.addActionListener(updatePaymentAction);
		JMenuItem mntmSearchPayment = new JMenuItem("Search");
		mnPayment.add(mntmSearchPayment);
		mntmSearch.addActionListener(searchPaymentAction);
		JMenuItem mntmRemovePayment = new JMenuItem("Remove");
		mnPayment.add(mntmRemovePayment);
		mntmRemove.addActionListener(removePaymentAction);
		JMenuItem mntmListAllPayment = new JMenuItem("ListAll");
		mnPayment.add(mntmListAllPayment);
		mntmListAll.addActionListener(listallPaymentAction);
		JMenu mnActivity = new JMenu("Activity");
		menuBar.add(mnActivity);
		JMenuItem mntmInsertActivity = new JMenuItem("Insert");
		mnActivity.add(mntmInsertActivity);
		mntmInsert.addActionListener(insertActivityAction);
		JMenuItem mntmUpdateActivity = new JMenuItem("Update");
		mnActivity.add(mntmUpdateActivity);
		mntmUpdate.addActionListener(updateActivityAction);
		JMenuItem mntmSearchActivity = new JMenuItem("Search");
		mnActivity.add(mntmSearchActivity);
		mntmSearch.addActionListener(searchActivityAction);
		JMenuItem mntmRemoveActivity = new JMenuItem("Remove");
		mnActivity.add(mntmRemoveActivity);
		mntmRemove.addActionListener(removeActivityAction);
		JMenuItem mntmListAllActivity = new JMenuItem("ListAll");
		mnActivity.add(mntmListAllActivity);
		mntmListAll.addActionListener(listallActivityAction);
		JMenu mnAssignment = new JMenu("Assignment");
		menuBar.add(mnAssignment);
		JMenuItem mntmInsertAssignment = new JMenuItem("Insert");
		mnAssignment.add(mntmInsertAssignment);
		mntmInsert.addActionListener(insertAssignmentAction);
		JMenuItem mntmSearchAssignment = new JMenuItem("Search");
		mnAssignment.add(mntmSearchAssignment);
		mntmSearch.addActionListener(searchAssignmentAction);
		JMenuItem mntmRemoveAssignment = new JMenuItem("Remove");
		mnAssignment.add(mntmRemoveAssignment);
		mntmRemove.addActionListener(removeAssignmentAction);
		JMenuItem mntmListAllAssignment = new JMenuItem("ListAll");
		mnAssignment.add(mntmListAllAssignment);
		mntmListAll.addActionListener(listallAssignmentAction);
		JMenu mnSubmission = new JMenu("Submission");
		menuBar.add(mnSubmission);
		JMenuItem mntmSearchSubmission = new JMenuItem("Search");
		mnSubmission.add(mntmSearchSubmission);
		mntmSearch.addActionListener(searchSubmissionAction);
		JMenuItem mntmRemoveSubmission = new JMenuItem("Remove");
		mnSubmission.add(mntmRemoveSubmission);
		mntmRemove.addActionListener(removeSubmissionAction);
		JMenuItem mntmListAllSubmission = new JMenuItem("ListAll");
		mnSubmission.add(mntmListAllSubmission);
		mntmListAll.addActionListener(listallSubmissionAction);
		JMenu mnAuthor = new JMenu("Author");
		menuBar.add(mnAuthor);
		JMenu mnCheckingCopy = new JMenu("CheckingCopy");
		menuBar.add(mnCheckingCopy);
		JMenuItem mntmInsertCheckingCopy = new JMenuItem("Insert");
		mnCheckingCopy.add(mntmInsertCheckingCopy);
		mntmInsert.addActionListener(insertCheckingCopyAction);
		JMenuItem mntmUpdateCheckingCopy = new JMenuItem("Update");
		mnCheckingCopy.add(mntmUpdateCheckingCopy);
		mntmUpdate.addActionListener(updateCheckingCopyAction);
		JMenuItem mntmRemoveCheckingCopy = new JMenuItem("Remove");
		mnCheckingCopy.add(mntmRemoveCheckingCopy);
		mntmRemove.addActionListener(removeCheckingCopyAction);
		JMenuItem mntmSearchCheckingCopy = new JMenuItem("Search");
		mnCheckingCopy.add(mntmSearchCheckingCopy);
		mntmSearch.addActionListener(searchCheckingCopyAction);
		JMenuItem mntmListAllCheckingCopy = new JMenuItem("ListAll");
		mnCheckingCopy.add(mntmListAllCheckingCopy);
		mntmListAll.addActionListener(listallCheckingCopyAction);
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		mntmExit.addActionListener(exitMenuAction);
	}
	
	private class ExitMenuAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	} 

	private class InsertUserMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenInsertUser = UserInsertScreenP.getInstanceUserInsertScreenP();
			if(screenInsertUser.getParent() == null){
				desktopPane.add(screenInsertUser);
			}
			screenInsertUser.setVisible(true);
			desktopPane.moveToFront(screenInsertUser);
			try {
				screenInsertUser.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class UpdateUserMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenUpdateUser = UserUpdateScreenP.getInstanceUserUpdateScreenP();
			if(screenUpdateUser.getParent() == null){
				desktopPane.add(screenUpdateUser);
			}
			screenUpdateUser.setVisible(true);
			desktopPane.moveToFront(screenUpdateUser);
			try {
				screenUpdateUser.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class SearchUserMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchUser = UserSearchScreenP.getInstanceUserSearchScreenP();
			if(screenSearchUser.getParent() == null){
				desktopPane.add(screenSearchUser);
			}
			screenSearchUser.setVisible(true);
			desktopPane.moveToFront(screenSearchUser);
			try {
				screenSearchUser.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class RemoveUserMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemoveUser = UserRemoveScreenP.getInstanceUserRemoveScreenP();
			if(screenRemoveUser.getParent() == null){
				desktopPane.add(screenRemoveUser);
			}
			screenRemoveUser.setVisible(true);
			desktopPane.moveToFront(screenRemoveUser);
			try {
				screenRemoveUser.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class ListAllUserMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllUser = UserListAllScreenP.getInstanceUserListAllScreenP();
			if(screenListAllUser.getParent() == null){
				desktopPane.add(screenListAllUser);
			}
			screenListAllUser.setVisible(true);
			desktopPane.moveToFront(screenListAllUser);
			try {
				screenListAllUser.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class InsertSpeakerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenInsertSpeaker = SpeakerInsertScreenP.getInstanceSpeakerInsertScreenP();
			if(screenInsertSpeaker.getParent() == null){
				desktopPane.add(screenInsertSpeaker);
			}
			screenInsertSpeaker.setVisible(true);
			desktopPane.moveToFront(screenInsertSpeaker);
			try {
				screenInsertSpeaker.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class UpdateSpeakerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenUpdateSpeaker = SpeakerUpdateScreenP.getInstanceSpeakerUpdateScreenP();
			if(screenUpdateSpeaker.getParent() == null){
				desktopPane.add(screenUpdateSpeaker);
			}
			screenUpdateSpeaker.setVisible(true);
			desktopPane.moveToFront(screenUpdateSpeaker);
			try {
				screenUpdateSpeaker.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class SearchSpeakerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchSpeaker = SpeakerSearchScreenP.getInstanceSpeakerSearchScreenP();
			if(screenSearchSpeaker.getParent() == null){
				desktopPane.add(screenSearchSpeaker);
			}
			screenSearchSpeaker.setVisible(true);
			desktopPane.moveToFront(screenSearchSpeaker);
			try {
				screenSearchSpeaker.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class RemoveSpeakerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemoveSpeaker = SpeakerRemoveScreenP.getInstanceSpeakerRemoveScreenP();
			if(screenRemoveSpeaker.getParent() == null){
				desktopPane.add(screenRemoveSpeaker);
			}
			screenRemoveSpeaker.setVisible(true);
			desktopPane.moveToFront(screenRemoveSpeaker);
			try {
				screenRemoveSpeaker.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class ListAllSpeakerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllSpeaker = SpeakerListAllScreenP.getInstanceSpeakerListAllScreenP();
			if(screenListAllSpeaker.getParent() == null){
				desktopPane.add(screenListAllSpeaker);
			}
			screenListAllSpeaker.setVisible(true);
			desktopPane.moveToFront(screenListAllSpeaker);
			try {
				screenListAllSpeaker.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class InsertOrganizerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenInsertOrganizer = OrganizerInsertScreenP.getInstanceOrganizerInsertScreenP();
			if(screenInsertOrganizer.getParent() == null){
				desktopPane.add(screenInsertOrganizer);
			}
			screenInsertOrganizer.setVisible(true);
			desktopPane.moveToFront(screenInsertOrganizer);
			try {
				screenInsertOrganizer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class UpdateOrganizerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenUpdateOrganizer = OrganizerUpdateScreenP.getInstanceOrganizerUpdateScreenP();
			if(screenUpdateOrganizer.getParent() == null){
				desktopPane.add(screenUpdateOrganizer);
			}
			screenUpdateOrganizer.setVisible(true);
			desktopPane.moveToFront(screenUpdateOrganizer);
			try {
				screenUpdateOrganizer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class SearchOrganizerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchOrganizer = OrganizerSearchScreenP.getInstanceOrganizerSearchScreenP();
			if(screenSearchOrganizer.getParent() == null){
				desktopPane.add(screenSearchOrganizer);
			}
			screenSearchOrganizer.setVisible(true);
			desktopPane.moveToFront(screenSearchOrganizer);
			try {
				screenSearchOrganizer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class RemoveOrganizerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemoveOrganizer = OrganizerRemoveScreenP.getInstanceOrganizerRemoveScreenP();
			if(screenRemoveOrganizer.getParent() == null){
				desktopPane.add(screenRemoveOrganizer);
			}
			screenRemoveOrganizer.setVisible(true);
			desktopPane.moveToFront(screenRemoveOrganizer);
			try {
				screenRemoveOrganizer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class ListAllOrganizerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllOrganizer = OrganizerListAllScreenP.getInstanceOrganizerListAllScreenP();
			if(screenListAllOrganizer.getParent() == null){
				desktopPane.add(screenListAllOrganizer);
			}
			screenListAllOrganizer.setVisible(true);
			desktopPane.moveToFront(screenListAllOrganizer);
			try {
				screenListAllOrganizer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class InsertReviewerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenInsertReviewer = ReviewerInsertScreenP.getInstanceReviewerInsertScreenP();
			if(screenInsertReviewer.getParent() == null){
				desktopPane.add(screenInsertReviewer);
			}
			screenInsertReviewer.setVisible(true);
			desktopPane.moveToFront(screenInsertReviewer);
			try {
				screenInsertReviewer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class UpdateReviewerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenUpdateReviewer = ReviewerUpdateScreenP.getInstanceReviewerUpdateScreenP();
			if(screenUpdateReviewer.getParent() == null){
				desktopPane.add(screenUpdateReviewer);
			}
			screenUpdateReviewer.setVisible(true);
			desktopPane.moveToFront(screenUpdateReviewer);
			try {
				screenUpdateReviewer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class SearchReviewerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchReviewer = ReviewerSearchScreenP.getInstanceReviewerSearchScreenP();
			if(screenSearchReviewer.getParent() == null){
				desktopPane.add(screenSearchReviewer);
			}
			screenSearchReviewer.setVisible(true);
			desktopPane.moveToFront(screenSearchReviewer);
			try {
				screenSearchReviewer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class RemoveReviewerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemoveReviewer = ReviewerRemoveScreenP.getInstanceReviewerRemoveScreenP();
			if(screenRemoveReviewer.getParent() == null){
				desktopPane.add(screenRemoveReviewer);
			}
			screenRemoveReviewer.setVisible(true);
			desktopPane.moveToFront(screenRemoveReviewer);
			try {
				screenRemoveReviewer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class ListAllReviewerMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllReviewer = ReviewerListAllScreenP.getInstanceReviewerListAllScreenP();
			if(screenListAllReviewer.getParent() == null){
				desktopPane.add(screenListAllReviewer);
			}
			screenListAllReviewer.setVisible(true);
			desktopPane.moveToFront(screenListAllReviewer);
			try {
				screenListAllReviewer.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class InsertEventMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenInsertEvent = EventInsertScreenP.getInstanceEventInsertScreenP();
			if(screenInsertEvent.getParent() == null){
				desktopPane.add(screenInsertEvent);
			}
			screenInsertEvent.setVisible(true);
			desktopPane.moveToFront(screenInsertEvent);
			try {
				screenInsertEvent.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class UpdateEventMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenUpdateEvent = EventUpdateScreenP.getInstanceEventUpdateScreenP();
			if(screenUpdateEvent.getParent() == null){
				desktopPane.add(screenUpdateEvent);
			}
			screenUpdateEvent.setVisible(true);
			desktopPane.moveToFront(screenUpdateEvent);
			try {
				screenUpdateEvent.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class SearchEventMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchEvent = EventSearchScreenP.getInstanceEventSearchScreenP();
			if(screenSearchEvent.getParent() == null){
				desktopPane.add(screenSearchEvent);
			}
			screenSearchEvent.setVisible(true);
			desktopPane.moveToFront(screenSearchEvent);
			try {
				screenSearchEvent.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class RemoveEventMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemoveEvent = EventRemoveScreenP.getInstanceEventRemoveScreenP();
			if(screenRemoveEvent.getParent() == null){
				desktopPane.add(screenRemoveEvent);
			}
			screenRemoveEvent.setVisible(true);
			desktopPane.moveToFront(screenRemoveEvent);
			try {
				screenRemoveEvent.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class ListAllEventMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllEvent = EventListAllScreenP.getInstanceEventListAllScreenP();
			if(screenListAllEvent.getParent() == null){
				desktopPane.add(screenListAllEvent);
			}
			screenListAllEvent.setVisible(true);
			desktopPane.moveToFront(screenListAllEvent);
			try {
				screenListAllEvent.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class InsertPaymentMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenInsertPayment = PaymentInsertScreenP.getInstancePaymentInsertScreenP();
			if(screenInsertPayment.getParent() == null){
				desktopPane.add(screenInsertPayment);
			}
			screenInsertPayment.setVisible(true);
			desktopPane.moveToFront(screenInsertPayment);
			try {
				screenInsertPayment.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class UpdatePaymentMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenUpdatePayment = PaymentUpdateScreenP.getInstancePaymentUpdateScreenP();
			if(screenUpdatePayment.getParent() == null){
				desktopPane.add(screenUpdatePayment);
			}
			screenUpdatePayment.setVisible(true);
			desktopPane.moveToFront(screenUpdatePayment);
			try {
				screenUpdatePayment.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class SearchPaymentMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchPayment = PaymentSearchScreenP.getInstancePaymentSearchScreenP();
			if(screenSearchPayment.getParent() == null){
				desktopPane.add(screenSearchPayment);
			}
			screenSearchPayment.setVisible(true);
			desktopPane.moveToFront(screenSearchPayment);
			try {
				screenSearchPayment.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class RemovePaymentMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemovePayment = PaymentRemoveScreenP.getInstancePaymentRemoveScreenP();
			if(screenRemovePayment.getParent() == null){
				desktopPane.add(screenRemovePayment);
			}
			screenRemovePayment.setVisible(true);
			desktopPane.moveToFront(screenRemovePayment);
			try {
				screenRemovePayment.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class ListAllPaymentMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllPayment = PaymentListAllScreenP.getInstancePaymentListAllScreenP();
			if(screenListAllPayment.getParent() == null){
				desktopPane.add(screenListAllPayment);
			}
			screenListAllPayment.setVisible(true);
			desktopPane.moveToFront(screenListAllPayment);
			try {
				screenListAllPayment.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class InsertActivityMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenInsertActivity = ActivityInsertScreenP.getInstanceActivityInsertScreenP();
			if(screenInsertActivity.getParent() == null){
				desktopPane.add(screenInsertActivity);
			}
			screenInsertActivity.setVisible(true);
			desktopPane.moveToFront(screenInsertActivity);
			try {
				screenInsertActivity.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class UpdateActivityMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenUpdateActivity = ActivityUpdateScreenP.getInstanceActivityUpdateScreenP();
			if(screenUpdateActivity.getParent() == null){
				desktopPane.add(screenUpdateActivity);
			}
			screenUpdateActivity.setVisible(true);
			desktopPane.moveToFront(screenUpdateActivity);
			try {
				screenUpdateActivity.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class SearchActivityMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchActivity = ActivitySearchScreenP.getInstanceActivitySearchScreenP();
			if(screenSearchActivity.getParent() == null){
				desktopPane.add(screenSearchActivity);
			}
			screenSearchActivity.setVisible(true);
			desktopPane.moveToFront(screenSearchActivity);
			try {
				screenSearchActivity.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class RemoveActivityMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemoveActivity = ActivityRemoveScreenP.getInstanceActivityRemoveScreenP();
			if(screenRemoveActivity.getParent() == null){
				desktopPane.add(screenRemoveActivity);
			}
			screenRemoveActivity.setVisible(true);
			desktopPane.moveToFront(screenRemoveActivity);
			try {
				screenRemoveActivity.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class ListAllActivityMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllActivity = ActivityListAllScreenP.getInstanceActivityListAllScreenP();
			if(screenListAllActivity.getParent() == null){
				desktopPane.add(screenListAllActivity);
			}
			screenListAllActivity.setVisible(true);
			desktopPane.moveToFront(screenListAllActivity);
			try {
				screenListAllActivity.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class InsertAssignmentMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenInsertAssignment = AssignmentInsertScreenP.getInstanceAssignmentInsertScreenP();
			if(screenInsertAssignment.getParent() == null){
				desktopPane.add(screenInsertAssignment);
			}
			screenInsertAssignment.setVisible(true);
			desktopPane.moveToFront(screenInsertAssignment);
			try {
				screenInsertAssignment.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class SearchAssignmentMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchAssignment = AssignmentSearchScreenP.getInstanceAssignmentSearchScreenP();
			if(screenSearchAssignment.getParent() == null){
				desktopPane.add(screenSearchAssignment);
			}
			screenSearchAssignment.setVisible(true);
			desktopPane.moveToFront(screenSearchAssignment);
			try {
				screenSearchAssignment.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class RemoveAssignmentMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemoveAssignment = AssignmentRemoveScreenP.getInstanceAssignmentRemoveScreenP();
			if(screenRemoveAssignment.getParent() == null){
				desktopPane.add(screenRemoveAssignment);
			}
			screenRemoveAssignment.setVisible(true);
			desktopPane.moveToFront(screenRemoveAssignment);
			try {
				screenRemoveAssignment.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class ListAllAssignmentMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllAssignment = AssignmentListAllScreenP.getInstanceAssignmentListAllScreenP();
			if(screenListAllAssignment.getParent() == null){
				desktopPane.add(screenListAllAssignment);
			}
			screenListAllAssignment.setVisible(true);
			desktopPane.moveToFront(screenListAllAssignment);
			try {
				screenListAllAssignment.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class SearchSubmissionMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchSubmission = SubmissionSearchScreenP.getInstanceSubmissionSearchScreenP();
			if(screenSearchSubmission.getParent() == null){
				desktopPane.add(screenSearchSubmission);
			}
			screenSearchSubmission.setVisible(true);
			desktopPane.moveToFront(screenSearchSubmission);
			try {
				screenSearchSubmission.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class RemoveSubmissionMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemoveSubmission = SubmissionRemoveScreenP.getInstanceSubmissionRemoveScreenP();
			if(screenRemoveSubmission.getParent() == null){
				desktopPane.add(screenRemoveSubmission);
			}
			screenRemoveSubmission.setVisible(true);
			desktopPane.moveToFront(screenRemoveSubmission);
			try {
				screenRemoveSubmission.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class ListAllSubmissionMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllSubmission = SubmissionListAllScreenP.getInstanceSubmissionListAllScreenP();
			if(screenListAllSubmission.getParent() == null){
				desktopPane.add(screenListAllSubmission);
			}
			screenListAllSubmission.setVisible(true);
			desktopPane.moveToFront(screenListAllSubmission);
			try {
				screenListAllSubmission.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class InsertCheckingCopyMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenInsertCheckingCopy = CheckingCopyInsertScreenP.getInstanceCheckingCopyInsertScreenP();
			if(screenInsertCheckingCopy.getParent() == null){
				desktopPane.add(screenInsertCheckingCopy);
			}
			screenInsertCheckingCopy.setVisible(true);
			desktopPane.moveToFront(screenInsertCheckingCopy);
			try {
				screenInsertCheckingCopy.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class UpdateCheckingCopyMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenUpdateCheckingCopy = CheckingCopyUpdateScreenP.getInstanceCheckingCopyUpdateScreenP();
			if(screenUpdateCheckingCopy.getParent() == null){
				desktopPane.add(screenUpdateCheckingCopy);
			}
			screenUpdateCheckingCopy.setVisible(true);
			desktopPane.moveToFront(screenUpdateCheckingCopy);
			try {
				screenUpdateCheckingCopy.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class RemoveCheckingCopyMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenRemoveCheckingCopy = CheckingCopyRemoveScreenP.getInstanceCheckingCopyRemoveScreenP();
			if(screenRemoveCheckingCopy.getParent() == null){
				desktopPane.add(screenRemoveCheckingCopy);
			}
			screenRemoveCheckingCopy.setVisible(true);
			desktopPane.moveToFront(screenRemoveCheckingCopy);
			try {
				screenRemoveCheckingCopy.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class SearchCheckingCopyMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenSearchCheckingCopy = CheckingCopySearchScreenP.getInstanceCheckingCopySearchScreenP();
			if(screenSearchCheckingCopy.getParent() == null){
				desktopPane.add(screenSearchCheckingCopy);
			}
			screenSearchCheckingCopy.setVisible(true);
			desktopPane.moveToFront(screenSearchCheckingCopy);
			try {
				screenSearchCheckingCopy.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}
	private class ListAllCheckingCopyMenuAction implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			screenListAllCheckingCopy = CheckingCopyListAllScreenP.getInstanceCheckingCopyListAllScreenP();
			if(screenListAllCheckingCopy.getParent() == null){
				desktopPane.add(screenListAllCheckingCopy);
			}
			screenListAllCheckingCopy.setVisible(true);
			desktopPane.moveToFront(screenListAllCheckingCopy);
			try {
				screenListAllCheckingCopy.setSelected(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}  
	}

				//#if ${Bugs} == "T"
				private class BugtrackScreenMenuAction  implements ActionListener{ 

					@Override
					public void actionPerformed(ActionEvent e) {
						screenBugtrack = BugtrackScreenP.getInstanceBugtrackScreenP();
						//desktopPane.add(screenBugtrack);
						if(screenBugtrack.getParent() == null){
							desktopPane.add(screenBugtrack);
						}
						screenBugtrack.setVisible(true);
						desktopPane.moveToFront(screenBugtrack);
						try {
							screenBugtrack.setSelected(true);
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}  
				}
				//#endif
}