//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package evco.ev.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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

import evco.ev.data.Assignment;
import evco.ev.business.AssignmentControl;
import evco.ev.exception.AssignmentAlreadyInsertedException;
import evco.ev.exception.AssignmentNotFoundException;
import evco.ev.repository.AssignmentRepository;
import evco.ev.repository.AssignmentRepositoryBDR;
import evco.ev.table.AssignmentTableModel;

import evco.ev.table.ReviewerTableModel;

import evco.ev.data.Review.StatusReview;

import evco.ev.exception.RepositoryException;
import evco.ev.util.LibraryOfDSL;

public class AssignmentManagementScreenP extends JInternalFrame {

	
	private static AssignmentManagementScreenP instanceAssignmentManagementScreenP;
	private JPanel contentPane;
	private JTable table;
	
	private JTable tableReviewer;
	private JTable tableSelectReviewer;
	
	private JButton btnBack;
	private JTextField textFieldDate;
	private JTable table_1;
	
	private JComboBox comboBoxSubmission;
	private List<Reviewer> listaRevisoresSelecionados = new ArrayList<Reviewer>();
	private Submission submissionSelecionado = new Submission();
	private Assignment assignmentSelecionado = new Assignment();
	
	public static AssignmentManagementScreenP getInstanceAssignmentManagementScreenP() {
		if (instanceAssignmentManagementScreenP == null) {
			AssignmentManagementScreenP.instanceAssignmentManagementScreenP = new AssignmentManagementScreenP();
		}
		return AssignmentManagementScreenP.instanceAssignmentManagementScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignmentManagementScreenP frame = new AssignmentManagementScreenP();
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
	public AssignmentManagementScreenP() {
		setTitle("Assignment Management");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		SelectComboSubmissionAction selectSubmissionAction = new SelectComboSubmissionAction();
		RightButtonAction buttonInsertRigthAction = new RightButtonAction();
		LeftButtonAction buttonInsertLeftAction = new LeftButtonAction();
		InsertButtonAction insertAction = new InsertButtonAction(); 
		RemoveButtonAction removeAction = new RemoveButtonAction(); 
		SelectButtonAction selectAction = new SelectButtonAction(); 
		CleanButtonAction cleanAction = new CleanButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(147, 17, 413, 72);
		contentPane.add(panel);
		
		JLabel label = new JLabel("");
		label.setBounds(6, 6, 399, 60);
		ImageIcon image = new ImageIcon(getClass().getResource("/images/riseLabs.png"));
		Image imag = image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imag));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 97, 680, 164);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(499, 12, 61, 16);
		panel_1.add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(540, 6, 134, 28);
		panel_1.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JLabel label_1 = new JLabel("Submission:");
		label_1.setBounds(6, 12, 88, 16);
		panel_1.add(label_1);
		
		comboBoxSubmission = new JComboBox();
		comboBoxSubmission.setBounds(84, 8, 403, 27);
		panel_1.add(comboBoxSubmission);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 47, 202, 111);
		panel_1.add(scrollPane_1);
		
		tableReviewer = new JTable();
		scrollPane_1.setViewportView(tableReviewer);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(424, 40, 228, 118);
		panel_1.add(scrollPane_2);
		
		JButton buttonInsert = new JButton("-->");
		buttonInsert.setBounds(263, 61, 117, 29);
		panel_1.add(buttonInsert);
		
		JButton buttonRemove = new JButton("<--");
		buttonRemove.setBounds(263, 102, 117, 29);
		panel_1.add(buttonRemove);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 309, 716, 143);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		tableSelectReviewer = new JTable();
		scrollPane_2.setViewportView(tableSelectReviewer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 689, 118);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(6, 273, 117, 29);
		contentPane.add(btnInsert);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(127, 273, 117, 29);
		contentPane.add(btnRemove);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(377, 273, 117, 29);
		contentPane.add(btnSelect);
		
		JButton btnClean = new JButton("Clean");
		btnClean.setBounds(494, 273, 117, 29);
		contentPane.add(btnClean);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(605, 273, 117, 29);
		contentPane.add(btnBack);
		
		JList list = new JList();
		list.setBounds(335, 106, 1, 1);
		getContentPane().add(list);
		
		//PASSO 2
		btnInsert.addActionListener(insertAction);
		btnRemove.addActionListener(removeAction);
		btnSelect.addActionListener(selectAction);
		btnClean.addActionListener(cleanAction);
		btnBack.addActionListener(backAction);
		buttonInsert.addActionListener(buttonInsertRigthAction);
		buttonRemove.addActionListener(buttonInsertLeftAction);

		populateTable();
		populateTableReviewer();
		carregarComboSubmission();
		comboBoxSubmission.addActionListener(selectSubmissionAction);
		
	}
	
	private void populateTable(){
		
		try {
			AssignmentTableModel model;
			model = new AssignmentTableModel(EvCoMainScreenP.facade.getAssignmentList());	
			table.setModel(model);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			AssignmentManagementScreenP.instanceAssignmentManagementScreenP = null;
		}
	}
	
	private class CleanButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			cleanFields();
		}
	}
	
	private void cleanFields(){
		comboBoxSubmission.setSelectedIndex(0);
		textFieldDate.setText("");
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
			catch (AuthorNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AuthorAlreadyInsertedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					
			catch (UserNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UserAlreadyInsertedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}		
		}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				EvCoMainScreenP.facade.removeAssignment(assignmentSelecionado);
				JOptionPane.showMessageDialog(getContentPane(), "Remoção realizada com sucesso!!","Remoção",JOptionPane.INFORMATION_MESSAGE);
			} catch (AssignmentNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AssignmentAlreadyInsertedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		}
	}
	
	private class SelectButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = table.getSelectedRow();

			try {
				assignmentSelecionado =  new AssignmentTableModel(EvCoMainScreenP.facade.getAssignmentList()).get(rowIndex);
			} catch (RepositoryException e1) {
				// TODO Auto-generated catch block
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