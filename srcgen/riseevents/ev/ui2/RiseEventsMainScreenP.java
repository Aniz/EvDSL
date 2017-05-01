// Autogenerated by EvDSL
package riseevents.ev.ui2;
import riseevents.ev.util.LibraryOfDSL;
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

import riseevents.ev.facade.RiseEventsFacade;

public class RiseEventsMainScreenP extends JFrame {

	private JPanel contentPane;
	
    public static RiseEventsFacade facade; // caso a variabilidade de tela login seja retirada o sistema deve iniciar dessa tela
	
	private static RiseEventsMainScreenP instanceRiseEventsMainScreenP;      
	
	private UserInsertScreenP screenInsertUser;	
	private UserUpdateScreenP screenUpdateUser;	
	private UserSearchScreenP screenSearchUser;	
	private UserRemoveScreenP screenRemoveUser;	
	private UserListAllScreenP screenListAllUser;	

	private ActivityInsertScreenP screenInsertActivity;	
	private ActivityUpdateScreenP screenUpdateActivity;	
	private ActivitySearchScreenP screenSearchActivity;	
	private ActivityRemoveScreenP screenRemoveActivity;	
	private ActivityListAllScreenP screenListAllActivity;	

	private EventInsertScreenP screenInsertEvent;	
	private EventUpdateScreenP screenUpdateEvent;	
	private EventSearchScreenP screenSearchEvent;	
	private EventRemoveScreenP screenRemoveEvent;	
	private EventListAllScreenP screenListAllEvent;	


	private ReceiptScreenP screenReceipt;
	
	
	private static JLabel labelImagem;
	
	private JDesktopPane desktopPane;
	
	
	public static RiseEventsFacade getFacade(){
		return RiseEventsFacade.getInstance();
	}
	
	public static RiseEventsMainScreenP getInstanceRiseEventsMainScreenP() {
		if (instanceRiseEventsMainScreenP == null) {
			RiseEventsMainScreenP.instanceRiseEventsMainScreenP = new RiseEventsMainScreenP();
		}
		return RiseEventsMainScreenP.instanceRiseEventsMainScreenP;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RiseEventsMainScreenP frame = new RiseEventsMainScreenP();
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
	public RiseEventsMainScreenP() {
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		ExitMenuAction exitMenuAction = new ExitMenuAction();
		
		InsertUserMenuAction insertUserAction = new InsertUserMenuAction();	
		UpdateUserMenuAction updateUserAction = new UpdateUserMenuAction();	
		SearchUserMenuAction searchUserAction = new SearchUserMenuAction();	
		RemoveUserMenuAction removeUserAction = new RemoveUserMenuAction();	
		ListAllUserMenuAction listallUserAction = new ListAllUserMenuAction();	

		InsertActivityMenuAction insertActivityAction = new InsertActivityMenuAction();	
		UpdateActivityMenuAction updateActivityAction = new UpdateActivityMenuAction();	
		SearchActivityMenuAction searchActivityAction = new SearchActivityMenuAction();	
		RemoveActivityMenuAction removeActivityAction = new RemoveActivityMenuAction();	
		ListAllActivityMenuAction listallActivityAction = new ListAllActivityMenuAction();	

		InsertEventMenuAction insertEventAction = new InsertEventMenuAction();	
		UpdateEventMenuAction updateEventAction = new UpdateEventMenuAction();	
		SearchEventMenuAction searchEventAction = new SearchEventMenuAction();	
		RemoveEventMenuAction removeEventAction = new RemoveEventMenuAction();	
		ListAllEventMenuAction listallEventAction = new ListAllEventMenuAction();	


		RiseEventsMainScreenP.facade = RiseEventsFacade.getInstance();
		
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
		mntmInsertUser.addActionListener(insertUserAction);
		JMenuItem mntmUpdateUser = new JMenuItem("Update");
		mnUser.add(mntmUpdateUser);
		mntmUpdateUser.addActionListener(updateUserAction);
		JMenuItem mntmSearchUser = new JMenuItem("Search");
		mnUser.add(mntmSearchUser);
		mntmSearchUser.addActionListener(searchUserAction);
		JMenuItem mntmRemoveUser = new JMenuItem("Remove");
		mnUser.add(mntmRemoveUser);
		mntmRemoveUser.addActionListener(removeUserAction);
		JMenuItem mntmListAllUser = new JMenuItem("ListAll");
		mnUser.add(mntmListAllUser);
		mntmListAllUser.addActionListener(listallUserAction);
		JMenu mnActivity = new JMenu("Activity");
		menuBar.add(mnActivity);
		JMenuItem mntmInsertActivity = new JMenuItem("Insert");
		mnActivity.add(mntmInsertActivity);
		mntmInsertActivity.addActionListener(insertActivityAction);
		JMenuItem mntmUpdateActivity = new JMenuItem("Update");
		mnActivity.add(mntmUpdateActivity);
		mntmUpdateActivity.addActionListener(updateActivityAction);
		JMenuItem mntmSearchActivity = new JMenuItem("Search");
		mnActivity.add(mntmSearchActivity);
		mntmSearchActivity.addActionListener(searchActivityAction);
		JMenuItem mntmRemoveActivity = new JMenuItem("Remove");
		mnActivity.add(mntmRemoveActivity);
		mntmRemoveActivity.addActionListener(removeActivityAction);
		JMenuItem mntmListAllActivity = new JMenuItem("ListAll");
		mnActivity.add(mntmListAllActivity);
		mntmListAllActivity.addActionListener(listallActivityAction);
		JMenu mnEvent = new JMenu("Event");
		menuBar.add(mnEvent);
		JMenuItem mntmInsertEvent = new JMenuItem("Insert");
		mnEvent.add(mntmInsertEvent);
		mntmInsertEvent.addActionListener(insertEventAction);
		JMenuItem mntmUpdateEvent = new JMenuItem("Update");
		mnEvent.add(mntmUpdateEvent);
		mntmUpdateEvent.addActionListener(updateEventAction);
		JMenuItem mntmSearchEvent = new JMenuItem("Search");
		mnEvent.add(mntmSearchEvent);
		mntmSearchEvent.addActionListener(searchEventAction);
		JMenuItem mntmRemoveEvent = new JMenuItem("Remove");
		mnEvent.add(mntmRemoveEvent);
		mntmRemoveEvent.addActionListener(removeEventAction);
		JMenuItem mntmListAllEvent = new JMenuItem("ListAll");
		mnEvent.add(mntmListAllEvent);
		mntmListAllEvent.addActionListener(listallEventAction);
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

}