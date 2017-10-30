package riseevents.ev.ui2;
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

	
import riseevents.ev.data.NewOption.TypeNewOption;
import riseevents.ev.data.NewOption;
import riseevents.ev.business.NewOptionControl;
import riseevents.ev.exception.NewOptionAlreadyInsertedException;
import riseevents.ev.exception.NewOptionNotFoundException;
import riseevents.ev.repository.NewOptionRepository;
import riseevents.ev.repository.NewOptionRepositoryBDR;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.table.NewOptionTableModel;

public class NewOptionManagementScreenP extends JInternalFrame{

	private static NewOptionManagementScreenP instanceNewOptionManagementScreenP;
	private JTextField textFieldDate;
	private JTextField textFieldDescription;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	
	private JButton btnBack;
	
	JComboBox<String> newoptionIdcomboBox;
	JComboBox<String> typeNewOptionComboBox;
	
	JButton btnInsert;
	JButton btnRemove;
	JButton btnUpdate;
	JButton btnSelect;
	JButton btnClean;
	
	JLabel lblLastNewOptionId;
	private JTextField textFieldNewOptionId;
	

	 public static NewOptionManagementScreenP getInstanceNewOptionManagementScreenP() {
		 if (instanceNewOptionManagementScreenP == null) {
			 NewOptionManagementScreenP.instanceNewOptionManagementScreenP = new NewOptionManagementScreenP();
		 }
		 return NewOptionManagementScreenP.instanceNewOptionManagementScreenP;
	 }
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewOptionManagementScreenP frame = new NewOptionManagementScreenP();
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
	public NewOptionManagementScreenP() {
		
		InsertButtonAction insertAction = new InsertButtonAction(); 
		RemoveButtonAction removeAction = new RemoveButtonAction(); 
		UpdateButtonAction updateAction = new UpdateButtonAction();
		SelectButtonAction selectAction = new SelectButtonAction(); 
		CleanButtonAction cleanAction = new CleanButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		setTitle("NewOption Management");
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
		
		JLabel lblNewOptionId = new JLabel("NewOption Id:");
		lblNewOptionId.setBounds(6, 6, 69, 16);
		panel_1.add(lblNewOptionId);
		
		lblLastNewOptionId = new JLabel("");
		lblLastNewOptionId.setBounds(79, 6, 61, 16);
		panel_1.add(lblLastNewOptionId);
		
		newoptionIdcomboBox = new JComboBox<String>();
		newoptionIdcomboBox.setBounds(102, 45, 84, 27);
		panel_1.add(newoptionIdcomboBox);
		
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
		
		typeNewOptionComboBox = new JComboBox<String>();
		typeNewOptionComboBox.setBounds(262, 45, 134, 27);
		panel_1.add(typeNewOptionComboBox);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(6, 126, 104, 16);
		panel_1.add(lblDescription);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setBounds(95, 120, 504, 28);
		panel_1.add(textFieldDescription);
		textFieldDescription.setColumns(10);
		
		textFieldNewOptionId = new JTextField();
		textFieldNewOptionId.setBounds(74, 0, 134, 28);
		panel_1.add(textFieldNewOptionId);
		textFieldNewOptionId.setColumns(10);
		
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

		carregarComboBoxIdNewOption();
		populateTable();
	}
	
	private void carregarComboBoxStatus(){
		TypeNewOption[] type = TypeNewOption.values();
		List<String> typenewoptions = new ArrayList<String>();
		for(int i=0; i<type.length; i++){
			typenewoptions.add(i, type[i].name());
			typeNewOptionComboBox.addItem(type[i].name());
		}
	}
	
	private void carregarComboBoxIdNewOption(){
		List<NewOption> newoption = new ArrayList<NewOption>();
		try {
			newoption = RiseEventsMainScreenP.facade.getNewOptionList();
		} catch (RepositoryException ex) {
			JOptionPane.showMessageDialog(getContentPane(),
					ex.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			ex.printStackTrace();
		}
		
		for(NewOption entity : newoption){
			newoptionIdcomboBox.addItem(String.valueOf(entity.getIdNewOption()));
		}
		
	}
	
	
	private void populateTable(){
		try {
			NewOptionTableModel model;
			model = new NewOptionTableModel(RiseEventsMainScreenP.facade.getNewOptionList());
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

			
			NewOption newoption = null;
			
			Integer newoptionId = Integer.parseInt(newoptionIdcomboBox.getSelectedItem().toString());
	
			TypeNewOption type = TypeNewOption.valueOf(typeNewOptionComboBox.getSelectedItem().toString());
			String date = textFieldDate.getText();
			String description  = textFieldDescription.getText();
			
			//int resultado = 0;
			if (newoptionId.equals("") || type.equals("") || date.equals("")
					|| description.equals("") ) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {					
				try {
					
					newoption = new NewOption();
					newoption.setIdNewOption(newoptionId);
	
					newoption.setTypeNewOption(type);
										
					//Atualizar JTable
					NewOptionTableModel model = new NewOptionTableModel(RiseEventsMainScreenP.facade.getNewOptionList());
					
					RiseEventsMainScreenP.facade.insertNewOption(newoption); //isso obriga que o programa seja executado a partir da tela main screen, caso ele seja iniciado da tela de login ficaria RISEEVENTLOGINSCREEN.facade....
				

							//(NewOptionTableModel) table.getModel();
					model.addNewOption(newoption);
					table.setModel(model);

					// Limpar campos
					cleanFields();

				} catch (NewOptionAlreadyInsertedException e1) {
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
				NewOption newoption = new NewOptionTableModel(RiseEventsMainScreenP.facade.getNewOptionList()).get(rowIndex);
				RiseEventsMainScreenP.facade.removeNewOption(newoption.getIdNewOption());
				NewOptionTableModel model = (NewOptionTableModel) table.getModel();
				model.removeNewOption(rowIndex);
				table.setModel(model);
				
				cleanFields();

			} catch (NewOptionNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (NewOptionAlreadyInsertedException e1) {
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

				
				NewOption newoption = null;
				
				String typeNewoption = typeNewOptionComboBox.getSelectedItem().toString();
				
					newoption.setTypeNewOption(TypeNewOption.valueOf(typeNewOption));
					
					try {
						
						RiseEventsMainScreenP.facade.updateNewOption(NewOption);
						NewOptionTableModel model;
						model = new NewOptionTableModel(RiseEventsMainScreenP.facade.getNewOptionList());
						table.setModel(model);
					} catch (NewOptionNotFoundException e1) {
						JOptionPane
						.showMessageDialog(
								getContentPane(),
								"NewOption que está tentando alterar não existe!",
								"NewOption Inexistente",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (NewOptionAlreadyInsertedException e1) {
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
	
	private class SelectButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int rowIndex = table.getSelectedRow();
			NewOption newoptionOld = null;

			try {
				newoptionOld=  new NewOptionTableModel(RiseEventsMainScreenP.facade.getNewOptionList()).get(rowIndex);
			
				lblLastNewOptionId.setText(String.valueOf(newoptionOld.getIdNewOption()));
				newoptionIdcomboBox.setSelectedItem(newoptionOld.getIdNewOption());
				
	
				typeNewOptionComboBox.setSelectedItem(newoptionOld.getTypeNewOption());
				
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
			NewOptionManagementScreenP.instanceNewOptionManagementScreenP = null;
		}
	}
	
}
//#endif