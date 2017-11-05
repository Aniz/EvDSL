package riseevents.ev.ui2;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
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

import riseevents.ev.data.NewOption;
import riseevents.ev.data.NewOption.TypeNewOption;
import riseevents.ev.data.Registration;
import riseevents.ev.data.User;
import riseevents.ev.exception.NewOptionAlreadyInsertedException;
import riseevents.ev.exception.NewOptionNotFoundException;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.table.NewOptionTableModel;

public class NewOptionManagementScreenP extends JInternalFrame{

	private static NewOptionManagementScreenP instanceNewOptionManagementScreenP;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	
	JComboBox<String> typeComboBox;
	
	JButton btnInsert;
	JButton btnRemove;
	JButton btnUpdate;
	JButton btnSelect;
	JButton btnClean;
	JButton btnBack;
	
	JLabel lblLastNewOptionId;
	
	private JComboBox comboBoxTypeNewOption;
	
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
		panel_1.setBounds(6, 99, 829, 189);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
	
		JLabel lblNewOptionId = new JLabel("NewOption ID:");
		lblNewOptionId.setBounds(6, 20, 120, 16);
		panel_1.add(lblNewOptionId);
		
		lblLastNewOptionId = new JLabel("");
		lblLastNewOptionId.setBounds(128, 20, 61, 16);
		panel_1.add(lblLastNewOptionId);
		
		typeComboBox = new JComboBox<String>();
		typeComboBox.setBounds(248, 83, 134, 27);
		panel_1.add(typeComboBox);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(205, 87, 61, 16);
		panel_1.add(lblType);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 327, 829, 158);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 817, 146);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		InsertButtonAction insertAction = new InsertButtonAction(); 
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(6, 296, 117, 29);
		contentPane.add(btnInsert);
		btnInsert.addActionListener(insertAction);
	
		RemoveButtonAction removeAction = new RemoveButtonAction(); 
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(127, 296, 117, 29);
		contentPane.add(btnRemove);
		btnRemove.addActionListener(removeAction);
	
		UpdateButtonAction updateAction = new UpdateButtonAction();
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(248, 296, 117, 29);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(updateAction);
	
		SelectButtonAction selectAction = new SelectButtonAction(); 
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(377, 296, 117, 29);
		contentPane.add(btnSelect);
		btnSelect.addActionListener(selectAction);

		CleanButtonAction cleanAction = new CleanButtonAction();
		JButton btnClean = new JButton("Clean");
		btnClean.setBounds(503, 296, 117, 29);
		contentPane.add(btnClean);
		btnClean.addActionListener(cleanAction);

		BackButtonAction backAction = new BackButtonAction();
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(621, 296, 117, 29);
		contentPane.add(btnBack);
		btnBack.addActionListener(backAction);
		
		//Retirada Login
		loadLastIndex();
			carregarComboBoxTypeNewOption();
		populateTable();
	}
	
	private void loadLastIndex(){
		try {
			lblLastNewOptionId.setText(String.valueOf(RiseEventsMainScreenP.facade.getNewOptionLastId()));
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void carregarComboBoxTypeNewOption(){
		TypeNewOption[] types = TypeNewOption.values();
		List<String> typescheckingcopys = new ArrayList<String>();
		for(int i=0; i<types.length; i++){
			typescheckingcopys.add(i, types[i].name());
			typeComboBox.addItem(types[i].name());
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
	
		btnInsert.setEnabled(true);
	}
	
	//INSERINDO UM CHECKING COPY 
		private class InsertButtonAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				NewOption NewOption = null;
				
				TypeNewOption typenewoption = TypeNewOption.valueOf(comboBoxTypeNewOption.getSelectedItem().toString());
				
					try {
						
						NewOption = new NewOption();
							NewOption.setTypeNewOption(TypeNewOption.valueOf(typenewoption.toString()));
							
						//Atualizar JTable
						NewOptionTableModel model = new NewOptionTableModel(RiseEventsMainScreenP.facade.getNewOptionList());
						
						RiseEventsMainScreenP.facade.insertNewOption(NewOption); //isso obriga que o programa seja executado a partir da tela main screen, caso ele seja iniciado da tela de login ficaria RISEEVENTLOGINSCREEN.facade....
					
						model.addNewOption(NewOption);
						table.setModel(model);

						// Limpar campos
						cleanFields();

					} catch (NewOptionAlreadyInsertedException e1) {
						JOptionPane
						.showMessageDialog(
								getContentPane(),
								"Já existe um Registro!",
								"Registro Existente",
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
		
		private class RemoveButtonAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				
				if(rowIndex == -1){
					JOptionPane.showMessageDialog(getContentPane(),
							"Selecione o Registro a ser removido!");
					return;
				}
				
				try {
					NewOption NewOption = new NewOptionTableModel(RiseEventsMainScreenP.facade.getNewOptionList()).get(rowIndex);
					RiseEventsMainScreenP.facade.removeNewOption(NewOption.getIdNewOption());
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

					String typeNewOption = comboBoxTypeNewOption.getSelectedItem().toString();
					if (typeNewOption.equals("") ) {
						JOptionPane.showMessageDialog(getContentPane(),
								"Não pode haver campo vazio.", "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						
						NewOption NewOptionNew = new NewOption();
							NewOptionNew.setTypeNewOption(TypeNewOption.valueOf(typeNewOption));
						
						try {
							RiseEventsMainScreenP.facade.updateNewOption(NewOptionNew);
							NewOptionTableModel model;
							model = new NewOptionTableModel(RiseEventsMainScreenP.facade.getNewOptionList());
							table.setModel(model);
						} catch (NewOptionNotFoundException e1) {
							JOptionPane
							.showMessageDialog(
									getContentPane(),
									"Registro Inexistente!",
									"Registro Inexistente",
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
		}

	private class SelectButtonAction  implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = table.getSelectedRow();
			NewOption NewOptionOld = null;
			try {
				NewOptionOld=  new NewOptionTableModel(RiseEventsMainScreenP.facade.getNewOptionList()).get(rowIndex);
				lblLastNewOptionId.setText(String.valueOf(NewOptionOld.getIdNewOption()));
				typeComboBox.setSelectedItem(NewOptionOld.getTypeNewOption());
				
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
			loadLastIndex();		
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