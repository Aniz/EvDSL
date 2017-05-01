package RiseEvents.ev.ui2;
import riseevents.ev.util.LibraryOfDSL;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import riseevents.ev.data.Newclass;
import riseevents.ev.business.NewclassControl;
import riseevents.ev.exception.NewclassAlreadyInsertedException;
import riseevents.ev.exception.NewclassNotFoundException;
import riseevents.ev.repository.NewclassRepository;
import riseevents.ev.repository.NewclassRepositoryBDR;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.table.NewclassTableModel;

public class NewclassManagementScreenP extends JInternalFrame{

	private static NewclassManagementScreenP instanceNewclassManagementScreenP;
	private JTextField textFieldDate;
	private JTextField textFieldDescription;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	
	private JButton btnBack;
	
	JComboBox<String> submissionIdcomboBox;
	JComboBox<String> statusComboBox;
	
	JButton btnInsert;
	JButton btnRemove;
	JButton btnUpdate;
	JButton btnSelect;
	JButton btnClean;
	
	JLabel lblLastNewclassId;
	private JTextField textFieldNewclassId;
	

	 public static NewclassManagementScreenP getInstanceNewclassManagementScreenP() {
		 if (instanceNewclassManagementScreenP == null) {
			 NewclassManagementScreenP.instanceNewclassManagementScreenP = new NewclassManagementScreenP();
		 }
		 return NewclassManagementScreenP.instanceNewclassManagementScreenP;
	 }
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewclassManagementScreenP frame = new NewclassManagementScreenP();
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
	public NewclassManagementScreenP() {
		
		InsertButtonAction insertAction = new InsertButtonAction(); 
		RemoveButtonAction removeAction = new RemoveButtonAction(); 
		UpdateButtonAction updateAction = new UpdateButtonAction();
		SelectButtonAction selectAction = new SelectButtonAction(); 
		CleanButtonAction cleanAction = new CleanButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		setTitle("Newclass Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(220, 18, 392, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblImage = new JLabel("");
		lblImage.setBounds(6, 6, 380, 56);
		ImageIcon image = new ImageIcon(getClass().getResource("/images/riseLabs.png"));
		Image imag = image.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(imag));
		panel.add(lblImage);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 99, 789, 189);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewclassId = new JLabel("Newclass Id:");
		lblNewclassId.setBounds(6, 6, 69, 16);
		panel_1.add(lblNewclassId);
		
		lblLastNewclassId = new JLabel("");
		lblLastNewclassId.setBounds(79, 6, 61, 16);
		panel_1.add(lblLastNewclassId);
		
		JLabel lblSubmissionId = new JLabel("Submission Id:");
		lblSubmissionId.setBounds(6, 49, 104, 16);
		panel_1.add(lblSubmissionId);
		
		submissionIdcomboBox = new JComboBox<String>();
		submissionIdcomboBox.setBounds(102, 45, 84, 27);
		panel_1.add(submissionIdcomboBox);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(205, 49, 61, 16);
		panel_1.add(lblStatus);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(420, 49, 61, 16);
		panel_1.add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(465, 43, 134, 28);
		panel_1.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		statusComboBox = new JComboBox<String>();
		statusComboBox.setBounds(262, 45, 134, 27);
		panel_1.add(statusComboBox);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(6, 126, 104, 16);
		panel_1.add(lblDescription);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setBounds(95, 120, 504, 28);
		panel_1.add(textFieldDescription);
		textFieldDescription.setColumns(10);
		
		textFieldNewclassId = new JTextField();
		textFieldNewclassId.setBounds(74, 0, 134, 28);
		panel_1.add(textFieldNewclassId);
		textFieldNewclassId.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 327, 829, 158);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 800, 121);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(26, 296, 117, 29);
		contentPane.add(btnInsert);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(166, 296, 117, 29);
		contentPane.add(btnRemove);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(305, 296, 117, 29);
		contentPane.add(btnUpdate);
		
		btnSelect = new JButton("Selection");
		btnSelect.setBounds(441, 296, 117, 29);
		contentPane.add(btnSelect);
		
		btnClean = new JButton("Clean");
		btnClean.setBounds(570, 296, 117, 29);
		contentPane.add(btnClean);
		
		btnInsert.addActionListener(insertAction);
		btnRemove.addActionListener(removeAction);
		btnUpdate.addActionListener(updateAction);
		btnSelect.addActionListener(selectAction);
		btnClean.addActionListener(cleanAction);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(694, 296, 117, 29);
		getContentPane().add(btnBack);
		
		btnBack.addActionListener(backAction);

		
		carregarComboBoxStatus();
		carregarComboBoxIdSubmission();
		
		populateTable();
		
		
	}
	
	private void carregarComboBoxStatus(){
		StatusNewclass[] status = StatusNewclass.values();
		List<String> statusnewclasss = new ArrayList<String>();
		for(int i=0; i<status.length; i++){
			statusnewclasss.add(i, status[i].name());
			statusComboBox.addItem(status[i].name());
		}
	}
	
	private void carregarComboBoxIdSubmission(){
		List<Submission> submissions = new ArrayList<Submission>();
		try {
			submissions = RiseEventsMainScreenP.facade.getSubmissions();
		} catch (RepositoryException ex) {
			JOptionPane.showMessageDialog(getContentPane(),
					ex.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			ex.printStackTrace();
		}
		
		for(Submission submission : submissions){
			submissionIdcomboBox.addItem(String.valueOf(submission.getIdSubmission()));
		}
		
	}
	
	
	private void populateTable(){
		try {
			NewclassTableModel model;
			model = new NewclassTableModel(RiseEventsMainScreenP.facade.getNewclasss());
			table.setModel(model);
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void cleanFields() {
		textFieldDate.setText("");
		textFieldDescription.setText("");
		btnInsert.setEnabled(true);
	}
	
	
	//INSERINDO UMA REVIEW
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			
			Newclass newclass = null;
			
			Integer submissionId = Integer.parseInt(submissionIdcomboBox.getSelectedItem().toString());
			StatusNewclass status = StatusNewclass.valueOf(statusComboBox.getSelectedItem().toString());
			String date = textFieldDate.getText();
			String description  = textFieldDescription.getText();
			
			//int resultado = 0;
			if (submissionId.equals("") || status.equals("") || date.equals("")
					|| description.equals("") ) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {					
				try {
					
					newclass = new Newclass();
					newclass.setIdSubmission(submissionId);
					newclass.setDate(date);
					newclass.setDescription(description);
					newclass.setStatus(status);
					
					//Atualizar JTable
					NewclassTableModel model = new NewclassTableModel(RiseEventsMainScreenP.facade.getNewclasss());
					
					RiseEventsMainScreenP.facade.insertNewclass(newclass); //isso obriga que o programa seja executado a partir da tela main screen, caso ele seja iniciado da tela de login ficaria RISEEVENTLOGINSCREEN.facade....
				

							//(NewclassTableModel) table.getModel();
					model.addNewclass(newclass);
					table.setModel(model);

					// Limpar campos
					cleanFields();

				} catch (NewclassAlreadyInsertedException e1) {
					JOptionPane
					.showMessageDialog(
							getContentPane(),
							"Já existe uma revisao cadastrada com esse Registro!",
							"Revisao Existente",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (RepositoryException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
				
				
			}

			
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = table.getSelectedRow();
			
			if(rowIndex == -1){
				JOptionPane.showMessageDialog(getContentPane(),
						"Selecione a Revisao a ser removida!");
				return;
			}
			
			try {
				Newclass newclass = new NewclassTableModel(RiseEventsMainScreenP.facade.getNewclasss()).get(rowIndex);
				RiseEventsMainScreenP.facade.removeNewclass(newclass.getIdNewclass());
				NewclassTableModel model = (NewclassTableModel) table.getModel();
				model.removeNewclass(rowIndex);
				table.setModel(model);
				
				cleanFields();

			} catch (NewclassNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (NewclassAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
		}
	}
	
	private class UpdateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
		int rowIndex = table.getSelectedRow();
			
			
			try {

				
				Integer submissionId = Integer.parseInt(submissionIdcomboBox.getSelectedItem().toString());
				StatusNewclass status = StatusNewclass.valueOf(statusComboBox.getSelectedItem().toString());
				String date = textFieldDate.getText();
				String description  = textFieldDescription.getText();
				
				if (submissionId.equals("") || status.equals("") || date.equals("")
						|| description.equals("") ) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Não pode haver campo vazio.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;

				} else {
					
					Newclass newclassNew = new Newclass();
					newclassNew.setIdSubmission(submissionId);
					newclassNew.setDate(date);
					newclassNew.setDescription(description);
					newclassNew.setStatus(status);
					
					try {
						RiseEventsMainScreenP.facade.updateNewclass(newclassNew);
						NewclassTableModel model;
						model = new NewclassTableModel(RiseEventsMainScreenP.facade.getNewclasss());
						table.setModel(model);
					} catch (NewclassNotFoundException e1) {
						JOptionPane
						.showMessageDialog(
								getContentPane(),
								"Revisao que está tentando alterar não existe!",
								"Revisao Inexistente",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (NewclassAlreadyInsertedException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e1.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					}
					
				}
				
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
			
		}
	}
	
	private class SelectButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int rowIndex = table.getSelectedRow();
			Newclass newclassOld = null;

			try {
				newclassOld=  new NewclassTableModel(RiseEventsMainScreenP.facade.getNewclasss()).get(rowIndex);
			
				lblLastNewclassId.setText(String.valueOf(newclassOld.getIdNewclass()));
				submissionIdcomboBox.setSelectedItem(newclassOld.getIdSubmission());
				statusComboBox.setSelectedItem(newclassOld.getStatus());
				textFieldDate.setText(newclassOld.getDate());
				textFieldDescription.setText(newclassOld.getDescription());
				
			} catch (RepositoryException ex) {
				JOptionPane.showMessageDialog(getContentPane(),
						ex.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				ex.printStackTrace();
			}
			
		}
	}
	
	private class CleanButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
		cleanFields();
			
		}
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			NewclassManagementScreenP.instanceNewclassManagementScreenP = null;
		}
	}
	
}
//#endif