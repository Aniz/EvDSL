//#if ${ReportsFrequencyperEvent} == "T"
package evco.ev.ui2;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JInternalFrame;

import evco.ev.data.Event;
import evco.ev.exception.EventAlreadyInsertedException;
import evco.ev.exception.EventNotFoundException;
import evco.ev.exception.RepositoryException;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.lowagie.text.DocumentException;


public class EventReportsFrequencyPerEventScreenP extends JInternalFrame {

	
	List<String> participants;
	
	JComboBox comboBoxEvent;
	
	private static EventReportsFrequencyPerEventScreenP instanceEventReportsFrequencyPerEventScreenP;
	
	public static EventReportsFrequencyPerEventScreenP getInstanceEventReportsFrequencyPerEventScreenP() {
		if (instanceEventReportsFrequencyPerEventScreenP == null) {
			EventReportsFrequencyPerEventScreenP.instanceEventReportsFrequencyPerEventScreenP = new EventReportsFrequencyPerEventScreenP();
		}
		return EventReportsFrequencyPerEventScreenP.instanceEventReportsFrequencyPerEventScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventReportsFrequencyPerEventScreenP frame = new EventReportsFrequencyPerEventScreenP();
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
	public EventReportsFrequencyPerEventScreenP() {
		setTitle("Frequency per Event");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		JLabel lblEvent = new JLabel("Event:");
		lblEvent.setBounds(28, 51, 61, 16);
		getContentPane().add(lblEvent);
		
		comboBoxEvent = new JComboBox();
		comboBoxEvent.setBounds(78, 47, 458, 27);
		getContentPane().add(comboBoxEvent);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(563, 46, 117, 29);
		getContentPane().add(btnGenerate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(563, 87, 117, 29);
		getContentPane().add(btnBack);

		SelectComboEventAction selectEventAction = new SelectComboEventAction();
		GenerateButtonAction generateAction = new GenerateButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		btnGenerate.addActionListener(generateAction);
		btnBack.addActionListener(backAction);
		comboBoxEvent.addActionListener(selectEventAction);
		
		carregarEventComboBox();
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			EventReportsFrequencyPerEventScreenP.instanceEventReportsFrequencyPerEventScreenP = null;
		}
	}
	
	private class GenerateButtonAction  implements ActionListener{ 
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int idEvent;
			try {
				idEvent = EvCoMainScreenP.getFacade().getEventIdByName(comboBoxEvent.getSelectedItem().toString());
				Event event = EvCoMainScreenP.getFacade().searchEvent(idEvent);
				EvCoMainScreenP.getFacade().frequencyPerEvent(participants,event);
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (EventNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (EventAlreadyInsertedException e1) {
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
	
	private void carregarEventComboBox(){
		try {
			List<Event> list = EvCoMainScreenP.facade.getEventList();
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
	
	private class SelectComboEventAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				int i;
				i = EvCoMainScreenP.facade.getEventIdByName(comboBoxEvent.getSelectedItem().toString());
				participants = EvCoMainScreenP.facade.getParticipantsPerEvent(i);
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
		}
	}
}
//#endif