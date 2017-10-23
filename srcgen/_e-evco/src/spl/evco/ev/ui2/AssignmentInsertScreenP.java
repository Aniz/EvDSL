//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package evco.ev.ui2;

import evco.ev.util.LibraryOfDSL;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextField;

import org.apache.commons.mail.EmailException;

import evco.ev.data.User;
import evco.ev.business.UserControl;
import evco.ev.exception.UserAlreadyInsertedException;
import evco.ev.exception.UserNotFoundException;
import evco.ev.repository.UserRepository;
import evco.ev.repository.UserRepositoryBDR;
import evco.ev.data.Reviewer;
import evco.ev.business.ReviewerControl;
import evco.ev.exception.ReviewerAlreadyInsertedException;
import evco.ev.exception.ReviewerNotFoundException;
import evco.ev.repository.ReviewerRepository;
import evco.ev.repository.ReviewerRepositoryBDR;
import evco.ev.data.Submission;
import evco.ev.business.SubmissionControl;
import evco.ev.exception.SubmissionAlreadyInsertedException;
import evco.ev.exception.SubmissionNotFoundException;
import evco.ev.repository.SubmissionRepository;
import evco.ev.repository.SubmissionRepositoryBDR;
import evco.ev.data.Author;
import evco.ev.business.AuthorControl;
import evco.ev.exception.AuthorAlreadyInsertedException;
import evco.ev.exception.AuthorNotFoundException;
import evco.ev.repository.AuthorRepository;
import evco.ev.repository.AuthorRepositoryBDR;
import evco.ev.data.SubmissionUser;
import evco.ev.business.SubmissionUserControl;
import evco.ev.exception.SubmissionUserAlreadyInsertedException;
import evco.ev.exception.SubmissionUserNotFoundException;
import evco.ev.repository.SubmissionUserRepository;
import evco.ev.repository.SubmissionUserRepositoryBDR;
import evco.ev.data.SubmissionAuthor;
import evco.ev.business.SubmissionAuthorControl;
import evco.ev.exception.SubmissionAuthorAlreadyInsertedException;
import evco.ev.exception.SubmissionAuthorNotFoundException;
import evco.ev.repository.SubmissionAuthorRepository;
import evco.ev.repository.SubmissionAuthorRepositoryBDR;
import evco.ev.data.Review;
import evco.ev.business.ReviewControl;
import evco.ev.exception.ReviewAlreadyInsertedException;
import evco.ev.exception.ReviewNotFoundException;
import evco.ev.repository.ReviewRepository;
import evco.ev.repository.ReviewRepositoryBDR;

import evco.ev.table.ReviewerTableModel;

import evco.ev.data.Review.StatusReview;

import evco.ev.exception.AssignmentAlreadyInsertedException;
import evco.ev.table.AssignmentTableModel;
import evco.ev.data.Assignment;
import evco.ev.exception.RepositoryException;
import evco.ev.util.LibraryOfDSL;

public class AssignmentInsertScreenP extends JInternalFrame{

	private static AssignmentInsertScreenP instanceAssignmentInsertScreenP;
	private JTextField textFieldDate;
	private JComboBox comboBoxSubmission;
	
	private JTable tableReviewer;
	private JTable tableSelectReviewer;
	
	
	private List<Reviewer> listaRevisoresSelecionados = new ArrayList<Reviewer>();
	
	private Submission submissionSelecionado = new Submission();
	
	public static AssignmentInsertScreenP getInstanceAssignmentInsertScreenP() {
		if (instanceAssignmentInsertScreenP == null) {
			AssignmentInsertScreenP.instanceAssignmentInsertScreenP = new AssignmentInsertScreenP();
		}
		return AssignmentInsertScreenP.instanceAssignmentInsertScreenP;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignmentInsertScreenP frame = new AssignmentInsertScreenP();
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
	public AssignmentInsertScreenP() {
		setTitle("Insert Assignment");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		SelectComboSubmissionAction selectSubmissionAction = new SelectComboSubmissionAction();
		InsertButtonAction insertAction = new InsertButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		RightButtonAction buttonInsertRigthAction = new RightButtonAction();
		LeftButtonAction buttonInsertLeftAction = new LeftButtonAction();
		//#if ${Assignmentautomatic} == "T"
		GenerateButtonAction generateAction = new GenerateButtonAction();
		//#endif
		JLabel lblSubmission = new JLabel("Submission:");
		lblSubmission.setBounds(6, 71, 80, 16);
		getContentPane().add(lblSubmission);
		
		comboBoxSubmission = new JComboBox();
		comboBoxSubmission.setBounds(86, 67, 606, 27);
		getContentPane().add(comboBoxSubmission);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(514, 23, 61, 16);
		getContentPane().add(lblDate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 133, 202, 246);
		getContentPane().add(scrollPane);
		
		tableReviewer = new JTable();
		scrollPane.setViewportView(tableReviewer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(427, 133, 228, 246);
		getContentPane().add(scrollPane_1);
		
		tableSelectReviewer = new JTable();
		scrollPane_1.setViewportView(tableSelectReviewer);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(558, 17, 134, 28);
		getContentPane().add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JButton buttonInsert = new JButton("-->");
		buttonInsert.setBounds(280, 184, 117, 29);
		getContentPane().add(buttonInsert);
		
		JButton buttonRemove = new JButton("<--");
		buttonRemove.setBounds(280, 225, 117, 29);
		getContentPane().add(buttonRemove);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(194, 399, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(366, 399, 117, 29);
		getContentPane().add(btnBack);
		
		JList list = new JList();
		list.setBounds(335, 106, 1, 1);
		getContentPane().add(list);
		
	
		btnInsert.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
		buttonInsert.addActionListener(buttonInsertRigthAction);
		buttonRemove.addActionListener(buttonInsertLeftAction);
		
	
		populateTableReviewer();
		carregarComboSubmission();
		comboBoxSubmission.addActionListener(selectSubmissionAction);
		
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			AssignmentInsertScreenP.instanceAssignmentInsertScreenP = null;
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Assignment assignment1;
			Assignment assignment2;
			Assignment assignment3;
			Reviewer reviewer1;
			Reviewer reviewer2;
			Reviewer reviewer3;
			Review review1;
			Review review2;
			Review review3;
			
			
			String idsubmission = comboBoxSubmission.getSelectedItem().toString();
			String date = textFieldDate.getText();
			
			
			if (date.equals("") || idsubmission.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			try {
				
				assignment1 = new Assignment();
				assignment2 = new Assignment();
				assignment3 = new Assignment();
				reviewer1 = new Reviewer();
				reviewer2 = new Reviewer();
				reviewer3 = new Reviewer();
				review1 = new Review();
				review2 = new Review();
				review3 = new Review();
				assignment1.setDate(date);
				assignment2.setDate(date);
				assignment3.setDate(date);
				
				int idSubmission;
				
				idSubmission = EvCoMainScreenP.facade.getSubmissionIdByTitle(idsubmission);
				
				Submission submission = EvCoMainScreenP.facade.searchSubmission(idSubmission);
				assignment1.setIdReviewSubmission(idSubmission);
				assignment2.setIdReviewSubmission(idSubmission);
				assignment3.setIdReviewSubmission(idSubmission);
				
				review1.setIdSubmission(idSubmission);
				review2.setIdSubmission(idSubmission);
				review3.setIdSubmission(idSubmission);
				
				review1.setStatus(StatusReview.valueOf("Aguardando"));
				review2.setStatus(StatusReview.valueOf("Aguardando"));
				review3.setStatus(StatusReview.valueOf("Aguardando"));
				
				review1.setRound(0);
				review2.setRound(0);
				review3.setRound(0);
				
				reviewer1 = listaRevisoresSelecionados.get(0);
				reviewer2 = listaRevisoresSelecionados.get(1);
				reviewer3 = listaRevisoresSelecionados.get(2);
				
				
				EvCoMainScreenP.facade.insertReview(review1);
				int lastIdReview1 = EvCoMainScreenP.facade.getReviewLastId();
				assignment1.setIdReview(lastIdReview1 - 1);
				review1.setIdReview(lastIdReview1);
				
				EvCoMainScreenP.facade.insertReview(review2);
				int lastIdReview2 = EvCoMainScreenP.facade.getReviewLastId();
				assignment2.setIdReview(lastIdReview2 - 1);
				review2.setIdReview(lastIdReview2);
				
				EvCoMainScreenP.facade.insertReview(review3);
				int lastIdReview3 = EvCoMainScreenP.facade.getReviewLastId();
				assignment3.setIdReview(lastIdReview3 - 1);
				review3.setIdReview(lastIdReview3);
				
				assignment1.setIdReviwerUser(reviewer1.getIdUser());
				assignment2.setIdReviwerUser(reviewer2.getIdUser());
				assignment3.setIdReviwerUser(reviewer3.getIdUser());
				
				EvCoMainScreenP.facade.insertAssignment(assignment1);
				EvCoMainScreenP.facade.insertAssignment(assignment2);
				EvCoMainScreenP.facade.insertAssignment(assignment3);
				
				Author author = new Author();
				List<SubmissionAuthor> submissionAuthor = new ArrayList<SubmissionAuthor>();
				submissionAuthor = EvCoMainScreenP.facade.getSubmissionAuthorList();
							
				for(SubmissionAuthor sa : submissionAuthor){
					if(sa.getIdSubmission() == idSubmission){
						author = EvCoMainScreenP.facade.searchAuthor(sa.getIdAuthor());
					}
				}
				User user = new User();
				List<SubmissionUser> submissionUser = new ArrayList<SubmissionUser>();
				submissionUser = EvCoMainScreenP.facade.getSubmissionUserList();
							
				for(SubmissionUser su : submissionUser){
					if(su.getIdSubmission() == idSubmission){
						user = EvCoMainScreenP.facade.searchUser(su.getIdUser());
					}
				}
				
				boolean resultAutomaticConflict1 = false;
				boolean resultAutomaticConflict2 = false;
				boolean resultAutomaticConflict3 = false;
				
				if(resultAutomaticConflict1 == true){
				JOptionPane.showMessageDialog(getContentPane(),
						"Essa atribuicao nao pode ser feita por conflito de interesses", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				}else{
				}
				if(resultAutomaticConflict2 == true){
				JOptionPane.showMessageDialog(getContentPane(),
						"Essa atribuicao nao pode ser feita por conflito de interesses", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				}else{
				}
				if(resultAutomaticConflict3 == true){
				JOptionPane.showMessageDialog(getContentPane(),
						"Essa atribuicao nao pode ser feita por conflito de interesses", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				}else{
				}
				
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
			} catch (SubmissionAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ReviewAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (AssignmentAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
				
			} 
			//#if ${InsertAuthors} == "T"
			catch (AuthorNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AuthorAlreadyInsertedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//#endif
			catch (UserNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UserAlreadyInsertedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			}
		}
	
	private void carregarComboSubmission(){
		try {
			List<Submission> submissions = EvCoMainScreenP.facade.getSubmissionList();
			Iterator<Submission> iterator = submissions.iterator();
			while(iterator.hasNext()){
				comboBoxSubmission.addItem(iterator.next().getTitle());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private class SelectComboSubmissionAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				int idSubmissionSelecionado;
				
				idSubmissionSelecionado = EvCoMainScreenP.facade.getSubmissionIdByTitle(comboBoxSubmission.getSelectedItem().toString());
				submissionSelecionado = EvCoMainScreenP.facade.searchSubmission(idSubmissionSelecionado);
				
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
			} catch (SubmissionAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
		}
	}
	
	private List<Reviewer> retornarReviewersSubmission(){
		List<Reviewer> reviewerList = new ArrayList<Reviewer>();
		List<String> keyWords;
		Submission submissionSelecionado = null;
		int idSubmission;
		try {
			reviewerList = EvCoMainScreenP.facade.getReviewerList();
			
			idSubmission = EvCoMainScreenP.facade.getSubmissionIdByTitle(comboBoxSubmission.getSelectedItem().toString());
			submissionSelecionado = EvCoMainScreenP.facade.searchSubmission(idSubmission);
			keyWords = LibraryOfDSL.quebrarKeywords(submissionSelecionado);
			
			Iterator<String> iteratorKeywords = keyWords.iterator();
			while(iteratorKeywords.hasNext()){
				reviewerList.add(EvCoMainScreenP.facade.getReviewerByknowledgeArea(iteratorKeywords.next()));
			}
		} catch (RepositoryException e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		} catch (SubmissionNotFoundException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (SubmissionAlreadyInsertedException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (ReviewerNotFoundException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (ReviewerAlreadyInsertedException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		return reviewerList;
	}
	
	private class LeftButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = tableSelectReviewer.getSelectedRow();
			
			listaRevisoresSelecionados.remove(rowIndex); 
			ReviewerTableModel model;
			model = new ReviewerTableModel(listaRevisoresSelecionados);
			tableSelectReviewer.setModel(model);
		}
	}
	
	private class RightButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = tableReviewer.getSelectedRow();
			Reviewer reviewerRight = null;
			
	//		Reviewer reviewerLeft = null;
			
			try {
				
				reviewerRight =  new ReviewerTableModel(EvCoMainScreenP.facade.getReviewerList()).get(rowIndex);
				if(listaRevisoresSelecionados.size() < 3){
					listaRevisoresSelecionados.add(reviewerRight);
					ReviewerTableModel model;
					model = new ReviewerTableModel(listaRevisoresSelecionados);
					tableSelectReviewer.setModel(model);
				}else{
					JOptionPane.showMessageDialog(getContentPane(),
							"Apenas 3 Revisores podem ser selecionados!", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} 
			
		}
	}
	
	private void populateTableReviewer(){
		try {
			ReviewerTableModel model;
			model = new ReviewerTableModel(EvCoMainScreenP.facade.getReviewerList());

			tableReviewer.setModel(model);
			
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void enviarEmails(Reviewer reviewer, Submission submission, Review review){
		User user = new User();
		try {
			user = EvCoMainScreenP.facade.searchUser(reviewer.getIdUser());
		} catch (UserNotFoundException e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		} catch (RepositoryException e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		} catch (UserAlreadyInsertedException e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		}
		
		try {
			LibraryOfDSL.sendNotification(user, review);
		} catch (EmailException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}

	
	
}
//#endif