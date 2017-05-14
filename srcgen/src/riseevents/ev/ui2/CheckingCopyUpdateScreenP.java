//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package riseevents.ev.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import riseevents.ev.data.CheckingCopy;
import riseevents.ev.data.CheckingCopy.TypeCheckingCopy;
import riseevents.ev.data.Registration;
import riseevents.ev.data.User;
import riseevents.ev.exception.CheckingCopyAlreadyInsertedException;
import riseevents.ev.exception.CheckingCopyNotFoundException;
import riseevents.ev.exception.RegistrationAlreadyInsertedException;
import riseevents.ev.exception.RegistrationNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.UserAlreadyInsertedException;
import riseevents.ev.exception.UserNotFoundException;

public class CheckingCopyUpdateScreenP extends JInternalFrame {

	private static CheckingCopyUpdateScreenP instanceCheckingCopyUpdateScreenP;
	private JTextField textFieldDate;
	private JComboBox comboBoxCheckingCopyType;
	private JComboBox comboBoxUserId;
	private JComboBox comboBoxRegistrationId;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JTextField textFieldId;
	
	public static CheckingCopyUpdateScreenP getInstanceCheckingCopyUpdateScreenP() {
		if (instanceCheckingCopyUpdateScreenP == null) {
			CheckingCopyUpdateScreenP.instanceCheckingCopyUpdateScreenP = new CheckingCopyUpdateScreenP();
		}
		return CheckingCopyUpdateScreenP.instanceCheckingCopyUpdateScreenP;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckingCopyUpdateScreenP frame = new CheckingCopyUpdateScreenP();
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
	public CheckingCopyUpdateScreenP() {
		setTitle("Update CheckingCopy");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		UpdateButtonAction insertAction = new UpdateButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		JLabel lblCheckingCopyId = new JLabel("CheckingCopy Id:");
		lblCheckingCopyId.setBounds(6, 25, 126, 16);
		getContentPane().add(lblCheckingCopyId);
		
		JLabel lblRegistration = new JLabel("Registration:");
		lblRegistration.setBounds(172, 25, 87, 16);
		getContentPane().add(lblRegistration);
		
		JLabel lblCheckingCopyType = new JLabel("CheckingCopy Type:");
		lblCheckingCopyType.setBounds(17, 113, 153, 16);
		getContentPane().add(lblCheckingCopyType);
		
		comboBoxCheckingCopyType = new JComboBox();
		comboBoxCheckingCopyType.setBounds(146, 109, 159, 27);
		getContentPane().add(comboBoxCheckingCopyType);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(376, 113, 61, 16);
		getContentPane().add(lblDate);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(459, 228, 204, 164);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(417, 101, 134, 28);
		getContentPane().add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(157, 237, 117, 29);
		getContentPane().add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(320, 237, 117, 29);
		getContentPane().add(btnBack);
		
		btnUpdate.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(376, 25, 61, 16);
		getContentPane().add(lblUser);
		
		comboBoxUserId = new JComboBox();
		comboBoxUserId.setBounds(417, 21, 278, 27);
		getContentPane().add(comboBoxUserId);
		
		comboBoxRegistrationId = new JComboBox();
		comboBoxRegistrationId.setBounds(254, 21, 117, 27);
		getContentPane().add(comboBoxRegistrationId);
		
		textFieldId = new JTextField();
		textFieldId.setBounds(118, 19, 42, 28);
		getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		
		TypeCheckingCopy[] types = TypeCheckingCopy.values();
		List<String> names = new ArrayList<String>();
		for(int i=0; i<types.length; i++){
			names.add(i, types[i].name());
			comboBoxCheckingCopyType.addItem(types[i].name());
		}
		
		carregarRegistrationComboBox();
		carregarUserComboBox();
		
		ItemListener listener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				setarNomeUsuarioByRegistration();
			}
			
		};
		comboBoxRegistrationId.addItemListener(listener);

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			CheckingCopyUpdateScreenP.instanceCheckingCopyUpdateScreenP = null;
		}
	}
	
	private void setarNomeUsuarioByRegistration(){
		try {
			Registration registration = RiseEventsMainScreenP.facade.searchRegistration(Integer.parseInt(comboBoxRegistrationId.getSelectedItem().toString()));
			User user = RiseEventsMainScreenP.facade.searchUser(registration.getIdUser());
			comboBoxUserId.setSelectedItem(user.getNameUser());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (RegistrationNotFoundException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (RegistrationAlreadyInsertedException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (UserAlreadyInsertedException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private class UpdateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			CheckingCopy checkingCopy = new CheckingCopy();
			
			int idCheckingCopy = Integer.valueOf(textFieldId.getText());
			String registration = comboBoxRegistrationId.getSelectedItem().toString();
			String user = comboBoxUserId.getSelectedItem().toString();
			String type = comboBoxCheckingCopyType.getSelectedItem().toString();
			String date = textFieldDate.getText();
			
			try {
				checkingCopy = RiseEventsMainScreenP.facade.searchCheckingCopy(idCheckingCopy);
			} catch (CheckingCopyNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (CheckingCopyAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
			checkingCopy.setIdCheckingCopy(idCheckingCopy);
			checkingCopy.setIdRegistration(Integer.valueOf(registration));
			checkingCopy.setIdUser(Integer.valueOf(user));
			checkingCopy.setCheckingCopyType(TypeCheckingCopy.valueOf(type));
			checkingCopy.setDateOfIssue(date);
			
			try {
				RiseEventsMainScreenP.facade.updateCheckingCopy(checkingCopy);
				checkingCopy = RiseEventsMainScreenP.facade.searchCheckingCopy(checkingCopy.getIdCheckingCopy());
				textArea.setText("");
				textArea.append(checkingCopy.toString());
			} catch (CheckingCopyNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (CheckingCopyAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
		}
	}	
	
	private void carregarRegistrationComboBox(){
		try {
			List<Registration> list = RiseEventsMainScreenP.facade.getRegistrationList();
			Iterator<Registration> iterator = list.iterator();
			while(iterator.hasNext()){
				comboBoxRegistrationId.addItem(iterator.next().getIdRegistration());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void carregarUserComboBox(){
		try {
			List<User> list = RiseEventsMainScreenP.facade.getUserList();
			Iterator<User> iterator = list.iterator();
			while(iterator.hasNext()){
				comboBoxUserId.addItem(iterator.next().getNameUser());
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