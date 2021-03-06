//#if ${SubmissionCompleta} == "T"
package riseevents.ev.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import riseevents.ev.data.Activity;

import riseevents.ev.data.Author;
import riseevents.ev.data.SubmissionAuthor;
import riseevents.ev.exception.AuthorAlreadyInsertedException;
import riseevents.ev.exception.SubmissionAuthorAlreadyInsertedException;

import riseevents.ev.data.Submission;
import riseevents.ev.data.Submission.TypeSubmission;
import riseevents.ev.exception.SubmissionAlreadyInsertedException;
import riseevents.ev.exception.SubmissionNotFoundException;

import riseevents.ev.data.User;

import riseevents.ev.data.SubmissionUser;
import riseevents.ev.exception.SubmissionUserAlreadyInsertedException;

import riseevents.ev.exception.RepositoryException;

public class SubmissionCompleteInsertScreenP extends JInternalFrame{

	
	static private String newline = "\n";
    private JTextArea log;
    private JFileChooser fc;
    private JPanel contentPane2;
	
	
	private JTextField textFieldAbstract;
	private JTextField textFieldKeywords;
	private JPanel contentPane;
	
	JComboBox<String> comboBoxActivityName;
	JComboBox<String> typeComboBox;
	JButton btnAttach;
	
	JLabel lblLastSubmissionId;
	private JLabel lblUserId;
	private JLabel lblIdUserLogado;
	private JLabel lblTitle;
	private JTextField textFieldTitle;
	private JTextField textFieldauthorName;
	private JTextField textFieldFiliation;
	private JTextField textFieldEmail;

	private JLabel lblAuthorId;
	private JLabel lblIdAuthor;
	
	//retirada de login
	private JComboBox comboBoxUser;
	
	private static SubmissionCompleteInsertScreenP instanceSubmissionCompleteInsertScreenP;
	
	 public static SubmissionCompleteInsertScreenP getInstanceSubmissionCompleteInsertScreenP() {
		 if (instanceSubmissionCompleteInsertScreenP == null) {
			 SubmissionCompleteInsertScreenP.instanceSubmissionCompleteInsertScreenP = new SubmissionCompleteInsertScreenP();
		 }
		 return SubmissionCompleteInsertScreenP.instanceSubmissionCompleteInsertScreenP;
	 }
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubmissionCompleteInsertScreenP frame = new SubmissionCompleteInsertScreenP();
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
	public SubmissionCompleteInsertScreenP() {
		
		setTitle("Insert Submission");
		
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
		InsertNewAuthorButtonAction insertNewAuthorAction = new InsertNewAuthorButtonAction();
		AttachButtonAction attachAction  = new AttachButtonAction();
		SelectTypeComboboxAction selectAction = new SelectTypeComboboxAction();
		
		
		//Retirada de Login
		SelectComboUserAction selectUserAction = new SelectComboUserAction();
		
		setTitle("Submission Insert");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon image = new ImageIcon(getClass().getResource("/images/riseLabs.png"));
		
		JLabel lblSubmissionId = new JLabel("Submission Id:");
		lblSubmissionId.setBounds(6, 6, 104, 16);
		getContentPane().add(lblSubmissionId);
		
		lblLastSubmissionId = new JLabel("");
		lblLastSubmissionId.setBounds(112, 6, 61, 16);
		getContentPane().add(lblLastSubmissionId);
		
		JLabel lblActivityName = new JLabel("Activity Name:");
		lblActivityName.setBounds(6, 49, 104, 16);
		getContentPane().add(lblActivityName);
		
		comboBoxActivityName = new JComboBox<String>();
		comboBoxActivityName.setBounds(102, 45, 194, 27);
		getContentPane().add(comboBoxActivityName);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(308, 49, 61, 16);
		getContentPane().add(lblType);
		
		JLabel lblAbstract = new JLabel("Abstract:");
		lblAbstract.setBounds(6, 130, 76, 16);
		getContentPane().add(lblAbstract);
		
		textFieldAbstract = new JTextField();
		textFieldAbstract.setBounds(74, 124, 463, 127);
		getContentPane().add(textFieldAbstract);
		textFieldAbstract.setColumns(10);
		
		typeComboBox = new JComboBox<String>();
		typeComboBox.setBounds(357, 45, 134, 27);
		getContentPane().add(typeComboBox);
		
		JLabel lblKeywords = new JLabel("Keywords:");
		lblKeywords.setBounds(6, 273, 84, 16);
		getContentPane().add(lblKeywords);
		
		textFieldKeywords = new JTextField();
		textFieldKeywords.setBounds(74, 267, 463, 28);
		getContentPane().add(textFieldKeywords);
		textFieldKeywords.setColumns(10);
		
		lblUserId = new JLabel("User Id");
		lblUserId.setBounds(151, 6, 61, 16);
		getContentPane().add(lblUserId);
		
		lblIdUserLogado = new JLabel("");
		lblIdUserLogado.setBounds(205, 6, 61, 16);
		getContentPane().add(lblIdUserLogado);
		
		JButton btnInsert = new JButton("Submit");
		btnInsert.setBounds(337, 432, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(466, 432, 117, 29);
		getContentPane().add(btnBack);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setBounds(16, 90, 61, 16);
		contentPane.add(lblTitle);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setBounds(74, 84, 463, 28);
		contentPane.add(textFieldTitle);
		textFieldTitle.setColumns(10);
		JLabel lblAuthor = new JLabel("Author Name:");
		lblAuthor.setBounds(16, 319, 87, 16);
		contentPane.add(lblAuthor);
		
		JLabel lblFiliation = new JLabel("Filiation:");
		lblFiliation.setBounds(21, 353, 61, 16);
		contentPane.add(lblFiliation);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(16, 385, 61, 16);
		contentPane.add(lblEmail);
		
		textFieldauthorName = new JTextField();
		textFieldauthorName.setBounds(112, 313, 352, 27);
		contentPane.add(textFieldauthorName);
		textFieldauthorName.setColumns(10);
		
		textFieldFiliation = new JTextField();
		textFieldFiliation.setBounds(78, 347, 386, 27);
		contentPane.add(textFieldFiliation);
		textFieldFiliation.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(78, 381, 268, 27);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JButton btnInsertNewAuthor = new JButton("Insert New Author");
		btnInsertNewAuthor.setBounds(181, 432, 141, 29);
		contentPane.add(btnInsertNewAuthor);
		
		lblAuthorId = new JLabel("Author Id:");
		lblAuthorId.setBounds(476, 319, 76, 16);
		contentPane.add(lblAuthorId);
		
		lblIdAuthor = new JLabel("");
		lblIdAuthor.setBounds(567, 319, 61, 16);
		contentPane.add(lblIdAuthor);
		btnAttach = new JButton("Attach");
		btnAttach.setBounds(553, 174, 117, 29);
		contentPane.add(btnAttach);
		//retirada de login
		comboBoxUser = new JComboBox();
		comboBoxUser.setBounds(278, 2, 151, 27);
		contentPane.add(comboBoxUser);
		
		btnInsert.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
		btnInsertNewAuthor.addActionListener(insertNewAuthorAction);
		loadLastAuthorIndex();
		btnAttach.addActionListener(attachAction);
		typeComboBox.addActionListener(selectAction);
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
	private void loadLastAuthorIndex(){
		try {
			lblIdAuthor.setText(String.valueOf(RiseEventsMainScreenP.facade.getAuthorLastId()));
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	// retirada ne login
//	private void pegarUsuarioLogado(){
//		lblIdUserLogado.setText(String.valueOf(RiSEEventLoginScreen.usuarioLogado.getIdUser()));
//	}
	
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
	private class SelectTypeComboboxAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			if(typeComboBox.getSelectedItem().toString().equals("Completa")){
				btnAttach.setEnabled(true);
			}else{
				btnAttach.setEnabled(false);
			}
		}
	}
	private class InsertNewAuthorButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			//clearAuthorFields();
			loadLastAuthorIndex();
			
			Author author = new Author();
			
			String nameAuthor = textFieldauthorName.getText();
			String filiation = textFieldFiliation.getText();
			String email = textFieldEmail.getText();
			
			if ( nameAuthor.equals(" ") || filiation.equals(" ") || email.equals(" ")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				try {
				author.setName(nameAuthor);
				author.setFiliation(filiation);
				author.setEmail(email);
				//Insere na tabela de authors
				RiseEventsMainScreenP.facade.insertAuthor(author);
				
				JOptionPane.showMessageDialog(getContentPane(),
						"Author inserido com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				
				
				} catch (AuthorAlreadyInsertedException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
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
						
						
						if(typeComboBox.getSelectedItem().toString().equals("Completa"))
							RiseEventsMainScreenP.facade.updateSubmission(submission);
						
						
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
						
						//Inserir na tabela de submissionAUTHORS
						SubmissionAuthor submissionAuthor = new SubmissionAuthor();
						//retirada tela login
						//int idCorrespondingAuthor = RiSEEventLoginScreen.usuarioLogado.getIdUser();
						//retirada tela login
						int idCorrespondingAuthor = Integer.valueOf(lblIdUserLogado.getText());
						submissionAuthor.setIdAuthor(idCorrespondingAuthor);
						submissionAuthor.setIdSubmission(Integer.valueOf(lblLastSubmissionId.getText()));
						submissionAuthor.setIdActivity(RiseEventsMainScreenP.facade.getActivityIdByName(comboBoxActivityName.getSelectedItem().toString()));
						RiseEventsMainScreenP.facade.insertSubmissionAuthor(submissionAuthor);
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
					} catch (SubmissionNotFoundException e1) {
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
					catch (SubmissionAuthorAlreadyInsertedException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e1.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					} 
					


				}
		
		}
		
	}
	private class AttachButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			//FileChooser.getInstanceFileChooserDemo2().setVisible(true);
			
			 //Set up the file chooser.
	        if (fc == null) {
	            fc = new JFileChooser();

		    //Add a custom file filter and disable the default
		    //(Accept All) file filter.

	            fc.setAcceptAllFileFilterUsed(true);
	        }

	        //Show it.
	        int returnVal = fc.showDialog(SubmissionCompleteInsertScreenP.this,
	                                      "Attach");

	        //Process the results.
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
//	            log.append("Attaching file: " + file.getName()
//	                       + "." + newline);
	            
	            try {
	            	//byte[] b = new byte[(int)file.length()];
	            	int idActivity = RiseEventsMainScreenP.facade.getActivityIdByName(comboBoxActivityName.getSelectedItem().toString());
					RiseEventsMainScreenP.facade.insertAttachment(file,idActivity);
					JOptionPane.showMessageDialog(getContentPane(),
							"Artigo inserido com sucesso", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (RepositoryException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (SubmissionAlreadyInsertedException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				};
	        } else {
	            log.append("Attachment cancelled by user." + newline);
	        }
	        //log.setCaretPosition(log.getDocument().getLength());

	        //Reset the file chooser for the next time it's shown.
	        fc.setSelectedFile(null);
	    }

	}
	
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			SubmissionCompleteInsertScreenP.instanceSubmissionCompleteInsertScreenP = null;
		}
	}
}
//#endif