//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package riseevents.ev.ui2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.mail.EmailException;

import riseevents.ev.data.Assignment;
import riseevents.ev.data.Review;
import riseevents.ev.data.Review.StatusReview;
import riseevents.ev.data.Submission;
import riseevents.ev.data.User;
import riseevents.ev.exception.RepositoryException;
import riseevents.ev.exception.ReviewAlreadyInsertedException;
import riseevents.ev.exception.ReviewNotFoundException;
import riseevents.ev.exception.SubmissionAlreadyInsertedException;
import riseevents.ev.exception.SubmissionNotFoundException;
import riseevents.ev.exception.UserAlreadyInsertedException;
import riseevents.ev.exception.UserNotFoundException;
import riseevents.ev.util.LibraryOfDSL;

public class ReviewInsertScreenP extends JInternalFrame  {

	
	private static ReviewInsertScreenP instanceReviewInsertScreenP;
	
	private JTextField textFieldDate;
	private JTextField textFieldDescription;
	
	JComboBox<String> statusComboBox;
	JComboBox<String> submissionComboBox;
	JComboBox<String> comboBoxResult;
	private JTextField textFieldUserId;
	
	public static ReviewInsertScreenP getInstanceReviewInsertScreenP() {
		if (instanceReviewInsertScreenP == null) {
			ReviewInsertScreenP.instanceReviewInsertScreenP = new ReviewInsertScreenP();
		}
		return ReviewInsertScreenP.instanceReviewInsertScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewInsertScreenP frame = new ReviewInsertScreenP();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ReviewInsertScreenP() {
		setTitle("Insert Review");
		
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
		ValidateButtonAction validateAction = new ValidateButtonAction();
		
		JLabel lblUserd = new JLabel("User Id:");
		lblUserd.setBounds(17, 25, 76, 16);
		getContentPane().add(lblUserd);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(90, 25, 61, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblSubmission = new JLabel("Submission (Title):");
		lblSubmission.setBounds(17, 91, 125, 16);
		getContentPane().add(lblSubmission);
		
		submissionComboBox = new JComboBox<String>();
		submissionComboBox.setEnabled(false);
		submissionComboBox.setBounds(139, 87, 530, 27);
		getContentPane().add(submissionComboBox);
		
		textFieldDate = new JTextField();
		textFieldDate.setEnabled(false);
		textFieldDate.setBounds(53, 119, 117, 28);
		getContentPane().add(textFieldDate);
		textFieldDate.setColumns(10);
		
		statusComboBox = new JComboBox<String>();
		statusComboBox.setEnabled(false);
		statusComboBox.setBounds(231, 119, 195, 27);
		getContentPane().add(statusComboBox);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(19, 160, 104, 16);
		getContentPane().add(lblDescription);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setEnabled(false);
		textFieldDescription.setBounds(108, 154, 561, 28);
		getContentPane().add(textFieldDescription);
		textFieldDescription.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setEnabled(false);
		btnInsert.setBounds(157, 237, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(320, 237, 117, 29);
		getContentPane().add(btnBack);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(17, 125, 61, 16);
		getContentPane().add(lblDate);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(175, 123, 61, 16);
		getContentPane().add(lblStatus);
		
		textFieldUserId = new JTextField();
		textFieldUserId.setBounds(84, 19, 134, 28);
		getContentPane().add(textFieldUserId);
		textFieldUserId.setColumns(10);
		
		JLabel lblDigiteIdRecebido = new JLabel("Digite ID para liberar cadastro da revisão");
		lblDigiteIdRecebido.setForeground(Color.RED);
		lblDigiteIdRecebido.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		lblDigiteIdRecebido.setBounds(17, 52, 424, 16);
		getContentPane().add(lblDigiteIdRecebido);
		
		JButton btnValidate = new JButton("Validate");
		btnValidate.setBounds(230, 20, 76, 29);
		getContentPane().add(btnValidate);
		
		JLabel lblResultado = new JLabel("Resultado:");
		lblResultado.setBounds(430, 126, 76, 16);
		getContentPane().add(lblResultado);
		
		comboBoxResult = new JComboBox();
		comboBoxResult.setBounds(497, 121, 172, 27);
		getContentPane().add(comboBoxResult);
		
		btnInsert.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
		btnValidate.addActionListener(validateAction);
		
		carregarComboBoxStatus();
		carregarComboBoxResultado();
	
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	
	private class ValidateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Submission submission;
				boolean validacao = RiseEventsMainScreenP.facade.isThereReviewer(Integer.valueOf(textFieldUserId.getText()));
				if(validacao == true){
					List<Assignment> assignments = RiseEventsMainScreenP.facade.getAssignmentList();
					for(Assignment a : assignments){
						if(textFieldUserId.getText().equals(a.getIdReviwerUser())){
							submission = RiseEventsMainScreenP.facade.searchSubmission(a.getIdReviewSubmission());
							submissionComboBox.addItem(String.valueOf(submission.getTitle()));
						}
					 
					}
					submissionComboBox.setEnabled(true);
					textFieldDate.setEnabled(true);
					textFieldDescription.setEnabled(true);
					statusComboBox.setEnabled(true);
				}else{
					JOptionPane.showMessageDialog(getContentPane(),
							"ID inexistente! ", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (SubmissionNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
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
			}
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Review review = null;
			
			String date = textFieldDate.getText();
			String description = textFieldDate.getText();
			String titleSubmission = submissionComboBox.getSelectedItem().toString();
			String status = statusComboBox.getSelectedItem().toString();
			String resultado = comboBoxResult.getSelectedItem().toString();
			
			if (date.equals("") || description.equals("") || titleSubmission.equals("") || status.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
					try {
						review = new Review();
						review.setDate(date);
						review.setDescription(description);
						review.setStatus(StatusReview.valueOf(status));
						review.setIdSubmission(RiseEventsMainScreenP.facade.getSubmissionIdByTitle(titleSubmission));
						int round = verificarRound(); 
						review.setRound(round + 1);
						review.setResult(resultado);
						
						RiseEventsMainScreenP.facade.updateReview(review);
						
						enviarEmails(review);

					} catch (ReviewAlreadyInsertedException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e1.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					} catch (RepositoryException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e1.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					} catch (ReviewNotFoundException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e1.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					}


				}
		
		}
		
	}
	
	public int verificarRound(){
		List<Review> reviews = new ArrayList<Review>();
		int round = 0;
		title = submissionComboBox.getSelectedItem().toString();
		try {
			int submissionid = RiseEventsMainScreenP.facade.getSubmissionIdByTitle(title);
			reviews = RiseEventsMainScreenP.facade.getReviewList();
			for(Review r : reviews){
				if(submissionid == r.getIdSubmission()){
					round = r.getRound();
				}else{
					round = 1;
				}
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		return round;
	}
	
		public void enviarEmails(Review review){
		try {
			Submission submission = RiseEventsMainScreenP.facade.searchSubmission(review.getIdSubmission());
//			Author author = pegarAuthorSubmission(submission);
			User user = RiseEventsMainScreenP.facade.searchUser(Integer.valueOf(textFieldUserId.getText()));
			LibraryOfDSL.sendRoundNotification(review, user);
			
		} catch (EmailException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (SubmissionNotFoundException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (SubmissionAlreadyInsertedException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserAlreadyInsertedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public Author pegarAuthorSubmission(Submission submission){
//		Author authorResult = new Author();
//		SubmissionAuthor authorSubmission = new SubmissionAuthor();
//		List<SubmissionAuthor> submissionAuthorList = null;
//		try {
//			submissionAuthorList = RiseEventsMainScreenP.facade.getSubmissionAuthorList();
//		} catch (RepositoryException e) {
//			JOptionPane.showMessageDialog(getContentPane(),
//					e.toString(), "Erro",
//					JOptionPane.INFORMATION_MESSAGE);
//			e.printStackTrace();
//		}
//		
//		authorSubmission.setIdActivity(submission.getIdActivity());
//		authorSubmission.setIdSubmission(submission.getIdSubmission());
//		
//		for(SubmissionAuthor submissionAuthor : submissionAuthorList){
//			authorSubmission.setIdAuthor(submissionAuthor.getIdAuthor());
//			boolean bool;
//			try {
//				bool = RiseEventsMainScreenP.facade.isThereSubmissionAuthor(authorSubmission);
//				if(bool == true){
//					authorResult = RiseEventsMainScreenP.facade.searchAuthor(authorSubmission.getIdAuthor());
//					break;
//				}
//			} catch (RepositoryException e) {
//				JOptionPane.showMessageDialog(getContentPane(),
//						e.toString(), "Erro",
//						JOptionPane.INFORMATION_MESSAGE);
//				e.printStackTrace();
//			} catch (AuthorNotFoundException e) {
//				JOptionPane.showMessageDialog(getContentPane(),
//						e.toString(), "Erro",
//						JOptionPane.INFORMATION_MESSAGE);
//				e.printStackTrace();
//			} catch (AuthorAlreadyInsertedException e) {
//				JOptionPane.showMessageDialog(getContentPane(),
//						e.toString(), "Erro",
//						JOptionPane.INFORMATION_MESSAGE);
//				e.printStackTrace();
//			}
//		}
//		return authorResult; 
//	}
	
	private void carregarComboBoxStatus(){
		
		statusComboBox.addItem("Aceito");
		statusComboBox.addItem("Rejeitado");
		//#if ${ReviewRoundofReview} == "T"
		statusComboBox.addItem("Em Analise");
		//#endif
	}
	
	private void carregarComboBoxResultado(){
		StatusReview[] status = StatusReview.values();
		List<String> statusreviews = new ArrayList<String>();
		for(int i=0; i<status.length; i++){
			statusreviews.add(i, status[i].name());
			statusComboBox.addItem(status[i].name());
		}
	}
}
//#endif