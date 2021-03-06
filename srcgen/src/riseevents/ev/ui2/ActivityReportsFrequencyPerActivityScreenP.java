//#if ${ReportsFrequencyperActivity} == "T"
package riseevents.ev.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.lowagie.text.DocumentException;

import riseevents.ev.data.Activity;
import riseevents.ev.data.Event;
import riseevents.ev.exception.ActivityAlreadyInsertedException;
import riseevents.ev.exception.ActivityNotFoundException;
import riseevents.ev.exception.RepositoryException;

public class ActivityReportsFrequencyPerActivityScreenP extends JInternalFrame {

	private JComboBox comboBoxEvent;
	private JComboBox comboBoxActivity;
	
	List<String> participants;
	
	private static ActivityReportsFrequencyPerActivityScreenP instanceActivityReportsFrequencyPerActivityScreenP;
	
	public static ActivityReportsFrequencyPerActivityScreenP getInstanceActivityReportsFrequencyPerActivityScreenP() {
		if (instanceActivityReportsFrequencyPerActivityScreenP == null) {
			ActivityReportsFrequencyPerActivityScreenP.instanceActivityReportsFrequencyPerActivityScreenP = new ActivityReportsFrequencyPerActivityScreenP();
		}
		return ActivityReportsFrequencyPerActivityScreenP.instanceActivityReportsFrequencyPerActivityScreenP;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityReportsFrequencyPerActivityScreenP frame = new ActivityReportsFrequencyPerActivityScreenP();
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
	public ActivityReportsFrequencyPerActivityScreenP() {
		setTitle("Frequency per Activity");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		comboBoxEvent = new JComboBox();
		comboBoxEvent.setBounds(78, 34, 416, 35);
		getContentPane().add(comboBoxEvent);
		
		JLabel lblEvent = new JLabel("Event:");
		lblEvent.setBounds(23, 42, 61, 16);
		getContentPane().add(lblEvent);
		
		JLabel lblActivity = new JLabel("Activity:");
		lblActivity.setBounds(23, 93, 61, 16);
		getContentPane().add(lblActivity);
		
		comboBoxActivity = new JComboBox();
		comboBoxActivity.setBounds(78, 89, 416, 27);
		getContentPane().add(comboBoxActivity);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(541, 42, 117, 29);
		getContentPane().add(btnGenerate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(541, 83, 117, 29);
		getContentPane().add(btnBack);
		
		GenerateButtonAction generateAction = new GenerateButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		SelectComboActivityAction selectActivityAction = new SelectComboActivityAction();
		SelectComboEventAction selectEventAction = new SelectComboEventAction();
		
		btnGenerate.addActionListener(generateAction);
		btnBack.addActionListener(backAction);
		comboBoxActivity.addActionListener(selectActivityAction);
		comboBoxEvent.addActionListener(selectEventAction);
		
		carregarEventComboBox();

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			ActivityReportsFrequencyPerActivityScreenP.instanceActivityReportsFrequencyPerActivityScreenP = null;
		}
	}
	
	private class GenerateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int idActivity;
			try {
				idActivity = RiseEventsMainScreenP.getFacade().getActivityIdByName(comboBoxActivity.getSelectedItem().toString());
				Activity activity = RiseEventsMainScreenP.getFacade().searchActivity(idActivity);
				RiseEventsMainScreenP.getFacade().frequencyPerActivity(participants, activity, comboBoxEvent.getSelectedItem().toString());	
				
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ActivityNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ActivityAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (DocumentException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			

			
			}
	}
	
	private class SelectComboActivityAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				if(comboBoxActivity.getItemCount() ==0)
					return;
				// buscando atividade com base no nome
				int i;
				i = RiseEventsMainScreenP.facade.getActivityIdByName(comboBoxActivity.getSelectedItem().toString());
				
				participants = RiseEventsMainScreenP.facade.getParticipantsPerActivity(i);
				
				
				
				
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
		}
	}
	
	private class SelectComboEventAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				// buscando atividade com base no nome
				List<Activity> activities;
				List<String> nameActivities = new ArrayList<String>();
				activities = RiseEventsMainScreenP.facade.getActivitiesByEvent(RiseEventsMainScreenP.facade.getEventIdByName(comboBoxEvent.getSelectedItem().toString()));
				comboBoxActivity.removeAllItems();
				//Passando de lista de atividades para lista de nome de atividades
				Iterator<Activity> iteratorActivity = activities.iterator();
				while(iteratorActivity.hasNext()){
					nameActivities.add(iteratorActivity.next().getNameActivity());
				}
				//CarregarCombobox activities	
				Iterator<String> iterator = nameActivities.iterator();
				while(iterator.hasNext()){
					comboBoxActivity.addItem(iterator.next());
				}
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
		}
	}
	
	private void carregarEventComboBox(){
		try {
			List<Event> list = RiseEventsMainScreenP.facade.getEventList();
			Iterator<Event> iterator = list.iterator();
			while(iterator.hasNext()){
				comboBoxEvent.addItem(iterator.next().getEventName());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
	}
}
//#endif