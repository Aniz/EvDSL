package {{systemName|lower}}.ev.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import {{systemName|lower}}.ev.data.ActivityUser;
import {{systemName|lower}}.ev.data.Event;
import {{systemName|lower}}.ev.data.Registration;
import {{systemName|lower}}.ev.data.User;
import {{systemName|lower}}.ev.exception.ActivityUserAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.RegistrationAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.RepositoryException;

public class RegistrationInsertScreenP extends JInternalFrame {

	private static RegistrationInsertScreenP instanceRegistrationInsertScreenP;
	private JTextField textFieldTotalValue;
	private JComboBox comboBoxUser;
	private JComboBox comboBoxEvent;
	
	
	
	
	public static RegistrationInsertScreenP getInstanceRegistrationInsertScreenP() {
		if (instanceRegistrationInsertScreenP == null) {
			RegistrationInsertScreenP.instanceRegistrationInsertScreenP = new RegistrationInsertScreenP();
		}
		return RegistrationInsertScreenP.instanceRegistrationInsertScreenP;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationInsertScreenP frame = new RegistrationInsertScreenP();
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
	public RegistrationInsertScreenP() {
		setTitle("Insert Registration");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		InsertButtonAction insertAction = new InsertButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		JLabel lblRegistrationId = new JLabel("Registration Id:");
		lblRegistrationId.setBounds(17, 25, 97, 16);
		getContentPane().add(lblRegistrationId);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(126, 25, 61, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(187, 25, 61, 16);
		getContentPane().add(lblUser);
		
		comboBoxUser = new JComboBox();
		comboBoxUser.setBounds(231, 21, 363, 27);
		getContentPane().add(comboBoxUser);
		
		JLabel lblTotalValue = new JLabel("Total Value:");
		lblTotalValue.setBounds(17, 111, 97, 16);
		getContentPane().add(lblTotalValue);
		
		textFieldTotalValue = new JTextField();
		textFieldTotalValue.setBounds(136, 105, 134, 28);
		getContentPane().add(textFieldTotalValue);
		textFieldTotalValue.setColumns(10);
		
		JLabel lblEvent = new JLabel("Event:");
		lblEvent.setBounds(17, 65, 61, 16);
		getContentPane().add(lblEvent);
		
		comboBoxEvent = new JComboBox();
		comboBoxEvent.setBounds(59, 61, 341, 27);
		getContentPane().add(comboBoxEvent);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(157, 237, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(320, 237, 117, 29);
		getContentPane().add(btnBack);
		
		btnInsert.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
		
		try {
			lblNewLabel.setText(String.valueOf({{systemName}}MainScreenP.facade.getRegistrationLastId()));
			
			
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
		carregarUserComboBox();
		carregarEventComboBox();

	}
	
	
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			RegistrationInsertScreenP.instanceRegistrationInsertScreenP = null;
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

				try {
					Registration registration = new Registration();
					
					String nameUser = comboBoxUser.getSelectedItem().toString();
					int idEvent = {{systemName}}MainScreenP.getFacade().getEventIdByName(comboBoxEvent.getSelectedItem().toString());
					
					//Atualizar o valor atual, com o valor do maintrack do evento.
					float totalValue = {{systemName}}MainScreenP.getFacade().getEventMainTrackValue(idEvent);
					textFieldTotalValue.setText(String.valueOf(totalValue));
					
					
					registration.setIdEvent(idEvent);
					registration.setIdUser({{systemName}}MainScreenP.facade.getUserIdByName(nameUser));
					registration.setTotalValue(totalValue);

					{{systemName}}MainScreenP.facade.insertRegistration(registration);
					
					//adicao na tabela de activityuser.
					ActivityUser activityUser = new ActivityUser();
					activityUser.setIdActivity({{systemName}}MainScreenP.getFacade().getActivityMainTrackId(idEvent));
					activityUser.setIdUser(registration.getIdUser());
					{{systemName}}MainScreenP.getFacade().insertActivityUser(activityUser);
					

				} catch (RegistrationAlreadyInsertedException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (RepositoryException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (ActivityUserAlreadyInsertedException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}


				
			}
		
		
	}
	
	private void carregarUserComboBox(){
		try {
			List<User> list = {{systemName}}MainScreenP.facade.getUsers();
			Iterator<User> iterator = list.iterator();
			while(iterator.hasNext()){
				comboBoxUser.addItem(iterator.next().getNameUser());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void carregarEventComboBox(){
		try {
			List<Event> list = {{systemName}}MainScreenP.facade.getEvents();
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
