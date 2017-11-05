//#if ${SubmissionParcial} == "T" 
package riseevents.ev.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JInternalFrame;

import riseevents.ev.data.Activity;


import riseevents.ev.data.Submission;
import riseevents.ev.data.Submission.TypeSubmission;
import riseevents.ev.exception.SubmissionAlreadyInsertedException;
import riseevents.ev.exception.SubmissionNotFoundException;

import riseevents.ev.data.User;

import riseevents.ev.data.SubmissionUser;
import riseevents.ev.exception.SubmissionUserAlreadyInsertedException;

import riseevents.ev.exception.RepositoryException;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SubmissionPartialInsertScreenP extends JInternalFrame {
	
	private static SubmissionPartialInsertScreenP instanceSubmissionPartialInsertScreenP;
	private JTextField textFieldTitle;
	private JTextField textFieldAbstract;
	private JTextField textFieldKeywords;
	private JTextField textFieldauthorName;
	private JTextField textFieldFiliation;
	private JTextField textFieldEmail;
	private JComboBox comboBoxUser;
	private JComboBox comboBoxActivityName;
	private JComboBox typeComboBox;
	private JLabel lblIdAuthor;
	private JLabel lblIdUserLogado;
	private JLabel lblLastSubmissionId;
	
	 public static SubmissionPartialInsertScreenP getInstanceSubmissionPartialInsertScreenP() {
		 if (instanceSubmissionPartialInsertScreenP == null) {
			 SubmissionPartialInsertScreenP.instanceSubmissionPartialInsertScreenP = new SubmissionPartialInsertScreenP();
		 }
		 return SubmissionPartialInsertScreenP.instanceSubmissionPartialInsertScreenP;
	 }
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubmissionPartialInsertScreenP frame = new SubmissionPartialInsertScreenP();
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
	public SubmissionPartialInsertScreenP() {
		setTitle("Insert Partial Submission");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 842,
				581);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		InsertButtonAction insertAction = new InsertButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		//#if ${InsertAuthors} == "T"
		//#endif
		
		SelectComboUserAction selectUserAction = new SelectComboUserAction();
		
		JLabel lblSubmissionId = new JLabel("Submission Id:");
		lblSubmissionId.setBounds(23, 18, 93, 16);
		getContentPane().add(lblSubmissionId);
		
		lblLastSubmissionId = new JLabel("");
		lblLastSubmissionId.setBounds(128, 18, 61, 16);
		getContentPane().add(lblLastSubmissionId);
		
		JLabel lblUserId = new JLabel("User Id:");
		lblUserId.setBounds(244, 18, 61, 16);
		getContentPane().add(lblUserId);
		
		comboBoxUser = new JComboBox();
		comboBoxUser.setBounds(409, 14, 182, 27);
		getContentPane().add(comboBoxUser);
		
		JLabel lblActivityName = new JLabel("Activity Name:");
		lblActivityName.setBounds(22, 67, 94, 16);
		getContentPane().add(lblActivityName);
		
		comboBoxActivityName = new JComboBox();
		comboBoxActivityName.setBounds(128, 63, 177, 27);
		getContentPane().add(comboBoxActivityName);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(331, 67, 61, 16);
		getContentPane().add(lblType);
		
		typeComboBox = new JComboBox();
		typeComboBox.setBounds(370, 63, 108, 27);
		getContentPane().add(typeComboBox);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(23, 123, 61, 16);
		getContentPane().add(lblTitle);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(91, 117, 387, 28);
		getContentPane().add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		JLabel lblAbstract = new JLabel("Abstract:");
		lblAbstract.setBounds(23, 162, 61, 16);
		getContentPane().add(lblAbstract);
		
		textFieldAbstract = new JTextField();
		textFieldAbstract.setBounds(89, 157, 338, 126);
		getContentPane().add(textFieldAbstract);
		textFieldAbstract.setColumns(10);
		
		JLabel lblKeywords = new JLabel("KeyWords:");
		lblKeywords.setBounds(6, 302, 78, 16);
		getContentPane().add(lblKeywords);
		
		textFieldKeywords = new JTextField();
		textFieldKeywords.setBounds(91, 296, 301, 28);
		getContentPane().add(textFieldKeywords);
		textFieldKeywords.setColumns(10);
		
		JButton btnInsert = new JButton("Submit");
		btnInsert.setBounds(364, 470, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(493, 470, 117, 29);
		getContentPane().add(btnBack);
		
	    lblIdUserLogado = new JLabel("New label");
		lblIdUserLogado.setBounds(312, 18, 61, 16);
		getContentPane().add(lblIdUserLogado);
		
		btnInsert.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
		//#if ${InsertAuthors} == "T"
		//#endif
	
		// Retirada de Login
		comboBoxUser.addActionListener(selectUserAction);
		//Retirada Login
	    carregarComboUser();
		
		loadLastIndex();
		carregarComboBoxTipo();
		carregarComboBoxActivity();
		
		//retirada de login
				//pegarUsuarioLogado();

	}
	
	//Retirada Login
			private void carregarComboUser(){
				try {
					List<User> list;
					list = RiseEventsMainScreenP.facade.getUserList();
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
			//Retirada de login
			private class SelectComboUserAction  implements ActionListener{ 

				@Override
				public void actionPerformed(ActionEvent e) {
					
					
					try {
						String nameUser = comboBoxUser.getSelectedItem().toString();
						lblIdUserLogado.setText(String.valueOf(RiseEventsMainScreenP.facade.getUserIdByName(nameUser)));
					} catch (RepositoryException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e1.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					}
					
				}
			}
		
		private void loadLastIndex(){
			try {
				lblLastSubmissionId.setText(String.valueOf(RiseEventsMainScreenP.facade.getSubmissionLastId()));
			} catch (RepositoryException e) {
				JOptionPane.showMessageDialog(getContentPane(),
						e.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
			}
		}
		//#if ${InsertAuthors} == "T"
		//#endif
		// retirada ne login
//		private void pegarUsuarioLogado(){
//			lblIdUserLogado.setText(String.valueOf(RiSEEventLoginScreen.usuarioLogado.getIdUser()));
//		}
		
		private void carregarComboBoxTipo(){
			TypeSubmission[] types = TypeSubmission.values();
			List<String> typessubmissions = new ArrayList<String>();
			for(int i=0; i<types.length; i++){
				typessubmissions.add(i, types[i].name());
				typeComboBox.addItem(types[i].name());
			}
			
		}
		
		private void carregarComboBoxActivity(){
			try {
				List<Activity> list = RiseEventsMainScreenP.facade.getActivityList();
				Iterator<Activity> iterator = list.iterator();
				while(iterator.hasNext()){
					comboBoxActivityName.addItem(iterator.next().getNameActivity());
				}
			} catch (RepositoryException e) {
				JOptionPane.showMessageDialog(getContentPane(),
						e.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
			}	
		}
		
		//#if ${InsertAuthors} == "T"
		//#endif
		
		private class InsertButtonAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Submission submission = null;
				
				String nameActivity = comboBoxActivityName.getSelectedItem().toString();
				String type = typeComboBox.getSelectedItem().toString();
				String title = textFieldTitle.getText();
				String abstractPaper = textFieldAbstract.getText();
				String keywords = textFieldKeywords.getText();
				
				if ( type.equals("") || title.equals("") || abstractPaper.equals("") || keywords.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Não pode haver campo vazio.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
						try {
							submission = new Submission();
							submission.setIdSubmission(Integer.valueOf(lblLastSubmissionId.getText()));
							submission.setAbstractPaper(abstractPaper);
							submission.setIdActivity(RiseEventsMainScreenP.facade.getActivityIdByName(nameActivity));
							submission.setKeywords(keywords);
							submission.setTitle(title);
							submission.setTypeSubmission(TypeSubmission.valueOf(type));
							
							//Inserir na tabela de submissao
							//estamos inserindo com o update pq usamos o insert para colocar o pdf no banco, attahcment com outros campos vazios,
							// quando o submit é pressionado um update é feito com os valores da submission, substituindo os que estao em branco. 
							
							// no caso da dubmissao parcial como ela acontece antes da completa podemos usar o insersubmission sem problema, uma vez
							// que serao sempre novas(que nao estao no banco) submissoes q serao inseridas.
							if(typeComboBox.getSelectedItem().toString().equals("Parcial"))
								RiseEventsMainScreenP.facade.insertSubmission(submission);
							
							//Inserir na tabela de submissionUSER
							//retirada tela login
							//int idUsuario = RiSEEventLoginScreen.usuarioLogado.getIdUser();
							//retirada tela login
							int idUsuario = Integer.valueOf(lblIdUserLogado.getText());
							SubmissionUser submissionUser = new SubmissionUser();
							submissionUser.setIdUser(idUsuario);
							submissionUser.setIdSubmission(Integer.valueOf(lblLastSubmissionId.getText()));
							submissionUser.setIdActivity(RiseEventsMainScreenP.facade.getActivityIdByName(nameActivity));
							RiseEventsMainScreenP.facade.insertSubmissionUser(submissionUser);
							//#if ${InsertAuthors} == "T"
							//#endif
							JOptionPane.showMessageDialog(getContentPane(),
									"Submission inserida com sucesso", "Sucesso",
									JOptionPane.INFORMATION_MESSAGE);
							

						} catch (SubmissionAlreadyInsertedException e1) {
							JOptionPane.showMessageDialog(getContentPane(),
									e1.toString(), "Erro",
									JOptionPane.INFORMATION_MESSAGE);
							e1.printStackTrace();
						} catch (RepositoryException e1) {
							JOptionPane.showMessageDialog(getContentPane(),
									e1.toString(), "Erro",
									JOptionPane.INFORMATION_MESSAGE);
							e1.printStackTrace();
						} catch (SubmissionUserAlreadyInsertedException e1) {
							JOptionPane.showMessageDialog(getContentPane(),
									e1.toString(), "Erro",
									JOptionPane.INFORMATION_MESSAGE);
							e1.printStackTrace();
						}
						//#if ${InsertAuthors} == "T"
						//#endif


					}
			
			}
			
		}
		
		private class BackButtonAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SubmissionPartialInsertScreenP.instanceSubmissionPartialInsertScreenP = null;
			}
		}
}
//#endif