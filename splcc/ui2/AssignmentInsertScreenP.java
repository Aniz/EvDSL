//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package {{systemName|lower}}.ev.ui2;

import {{systemName|lower}}.ev.util.LibraryOfDSL;
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

{% for key in extraData %}
import {{systemName|lower}}.ev.data.{{key}};
import {{systemName|lower}}.ev.business.{{key}}Control;
import {{systemName|lower}}.ev.exception.{{key}}AlreadyInsertedException;
import {{systemName|lower}}.ev.exception.{{key}}NotFoundException;
import {{systemName|lower}}.ev.repository.{{key}}Repository;
import {{systemName|lower}}.ev.repository.{{key}}RepositoryBDR;
{% endfor %}

{% if extraData.Reviewer is defined %}
import {{systemName|lower}}.ev.table.ReviewerTableModel;
{% endif %}

import {{systemName|lower}}.ev.exception.UserNotFoundException;
import {{systemName|lower}}.ev.exception.UserAlreadyInsertedException;
import {{systemName|lower}}.ev.data.User;

{% if extraData.Submission is defined and extraData.Reviewer is defined %}
import {{systemName|lower}}.ev.data.Review;
import {{systemName|lower}}.ev.data.Review.StatusReview;
import {{systemName|lower}}.ev.exception.ReviewAlreadyInsertedException;
{% endif %}
{% if extraData.Author is defined %}
import {{systemName|lower}}.ev.data.SubmissionAuthor;
import {{systemName|lower}}.ev.data.Author;
import {{systemName|lower}}.ev.exception.AuthorAlreadyInsertedException;
import {{systemName|lower}}.ev.exception.AuthorNotFoundException;
{% endif %}
{% if extraData.Submission is defined %}
import {{systemName|lower}}.ev.data.Submission;
{% endif %}
{% if extraData.Reviewer is defined %}
import {{systemName|lower}}.ev.data.Reviewer;
{% endif %}

import {{systemName|lower}}.ev.exception.AssignmentAlreadyInsertedException;
import {{systemName|lower}}.ev.table.AssignmentTableModel;
import {{systemName|lower}}.ev.data.Assignment;
import {{systemName|lower}}.ev.exception.RepositoryException;

public class AssignmentInsertScreenP extends JInternalFrame{

	private static AssignmentInsertScreenP instanceAssignmentInsertScreenP;
	private JTextField textFieldDate;
	private JComboBox comboBoxSubmission;
	
	private JTable tableReviewer;
	private JTable tableSelectReviewer;
	
	{% if 'interestConflict' in data.statments %}
	private JButton btnGenerate;
	{% endif %}
	
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
		
		{% if 'interestConflict' in data.statments %}
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(280, 358, 117, 29);
		getContentPane().add(btnGenerate);
		{% endif %}
	
		btnInsert.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
		buttonInsert.addActionListener(buttonInsertRigthAction);
		buttonRemove.addActionListener(buttonInsertLeftAction);
		
		{% if 'interestConflict' in data.statments %}
		btnGenerate.addActionListener(generateAction);
		{% endif %}	
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
				
				idSubmission = {{systemName}}MainScreenP.facade.getSubmissionIdByTitle(idsubmission);
				
				Submission submission = {{systemName}}MainScreenP.facade.searchSubmission(idSubmission);
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
				
				
				{{systemName}}MainScreenP.facade.insertReview(review1);
				int lastIdReview1 = {{systemName}}MainScreenP.facade.getReviewLastId();
				assignment1.setIdReview(lastIdReview1 - 1);
				review1.setIdReview(lastIdReview1);
				
				{{systemName}}MainScreenP.facade.insertReview(review2);
				int lastIdReview2 = {{systemName}}MainScreenP.facade.getReviewLastId();
				assignment2.setIdReview(lastIdReview2 - 1);
				review2.setIdReview(lastIdReview2);
				
				{{systemName}}MainScreenP.facade.insertReview(review3);
				int lastIdReview3 = {{systemName}}MainScreenP.facade.getReviewLastId();
				assignment3.setIdReview(lastIdReview3 - 1);
				review3.setIdReview(lastIdReview3);
				
				assignment1.setIdReviwerUser(reviewer1.getIdUser());
				assignment2.setIdReviwerUser(reviewer2.getIdUser());
				assignment3.setIdReviwerUser(reviewer3.getIdUser());
				
				{{systemName}}MainScreenP.facade.insertAssignment(assignment1);
				{{systemName}}MainScreenP.facade.insertAssignment(assignment2);
				{{systemName}}MainScreenP.facade.insertAssignment(assignment3);
				
				{% if 'Author' in avaliableDict %}
				Author author = new Author();
				List<SubmissionAuthor> submissionAuthor = new ArrayList<SubmissionAuthor>();
				submissionAuthor = {{systemName}}MainScreenP.facade.getSubmissionAuthorList();
							
				for(SubmissionAuthor sa : submissionAuthor){
					if(sa.getIdSubmission() == idSubmission){
						author = {{systemName}}MainScreenP.facade.searchAuthor(sa.getIdAuthor());
					}
				}
				{% endif %}
				
				{% if 'SubmissionUser' in avaliableDict %}
				User user = new User();
				List<SubmissionUser> submissionUser = new ArrayList<SubmissionUser>();
				submissionUser = {{systemName}}MainScreenP.facade.getSubmissionUserList();
							
				for(SubmissionUser su : submissionUser){
					if(su.getIdSubmission() == idSubmission){
						user = {{systemName}}MainScreenP.facade.searchUser(su.getIdUser());
					}
				}
				{% endif %}
				
				{% if "interestConflict" in data.statments %}
				boolean resultAutomaticConflict1 = false;
				boolean resultAutomaticConflict2 = false;
				boolean resultAutomaticConflict3 = false;
				
				resultAutomaticConflict1 = LibraryOfDSL.automaticInterestConflict({% if 'Author' in avaliableDict %}author,{% endif %}user, reviewer1);
				resultAutomaticConflict2 = LibraryOfDSL.automaticInterestConflict({% if 'Author' in avaliableDict %}author,{% endif %}user, reviewer2);
				resultAutomaticConflict3 = LibraryOfDSL.automaticInterestConflict({% if 'Author' in avaliableDict %}author,{% endif %}user, reviewer3);
				
				if(resultAutomaticConflict1 == true){
				JOptionPane.showMessageDialog(getContentPane(),
						"Essa atribuicao nao pode ser feita por conflito de interesses", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				}else{
				{% if 'notificationsDeadline' in data.statments %}
					enviarEmails(reviewer1, submission, review1);
				{% endif %}
				}
				if(resultAutomaticConflict2 == true){
				JOptionPane.showMessageDialog(getContentPane(),
						"Essa atribuicao nao pode ser feita por conflito de interesses", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				}else{
				{% if 'notificationsDeadline' in data.statments %}
					enviarEmails(reviewer2, submission, review2);
				{% endif %}
				}
				if(resultAutomaticConflict3 == true){
				JOptionPane.showMessageDialog(getContentPane(),
						"Essa atribuicao nao pode ser feita por conflito de interesses", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				}else{
				{% if 'notificationsDeadline' in data.statments %}
					enviarEmails(reviewer3, submission, review3);
				{% endif %}
				}
				{% endif %}
				
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
			{% if "Author" in avaliableDict %}
			catch (AuthorNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AuthorAlreadyInsertedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			{% endif %}
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
			List<Submission> submissions = {{systemName}}MainScreenP.facade.getSubmissionList();
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
				
				idSubmissionSelecionado = {{systemName}}MainScreenP.facade.getSubmissionIdByTitle(comboBoxSubmission.getSelectedItem().toString());
				submissionSelecionado = {{systemName}}MainScreenP.facade.searchSubmission(idSubmissionSelecionado);
				
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
			reviewerList = {{systemName}}MainScreenP.facade.getReviewerList();
			
			idSubmission = {{systemName}}MainScreenP.facade.getSubmissionIdByTitle(comboBoxSubmission.getSelectedItem().toString());
			submissionSelecionado = {{systemName}}MainScreenP.facade.searchSubmission(idSubmission);
			keyWords = LibraryOfDSL.quebrarKeywords(submissionSelecionado);
			
			Iterator<String> iteratorKeywords = keyWords.iterator();
			while(iteratorKeywords.hasNext()){
				reviewerList.add({{systemName}}MainScreenP.facade.getReviewerByknowledgeArea(iteratorKeywords.next()));
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
				
				reviewerRight =  new ReviewerTableModel({{systemName}}MainScreenP.facade.getReviewerList()).get(rowIndex);
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
			model = new ReviewerTableModel({{systemName}}MainScreenP.facade.getReviewerList());

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
			user = {{systemName}}MainScreenP.facade.searchUser(reviewer.getIdUser());
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

	
	{% if 'interestConflict' in data.statments %}
	private class GenerateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			String submissao = comboBoxSubmission.getSelectedItem().toString();
			List<Reviewer> reviewerList = new ArrayList<Reviewer>();
			if(submissao.equals("")){
				JOptionPane.showMessageDialog(getContentPane(),
						"Selecione uma Submissão", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
			}else{
				try {
					int subId = {{systemName}}MainScreenP.facade.getSubmissionIdByTitle(submissao);
					Submission sub = {{systemName}}MainScreenP.facade.searchSubmission(subId);
					String keywords = sub.getKeywords();
					String keywordsSplit[] = keywords.split(Pattern.quote(","));
					reviewerList = {{systemName}}MainScreenP.facade.getReviewerList();
					boolean flag;

					
					for(Reviewer r : reviewerList){
						flag = false;
						ReviewerTableModel model;
						String knowledgeAreaSplit[] = r.getKnowledgeArea().split(Pattern.quote(","));						
						for(String know : knowledgeAreaSplit){
							flag = false;
							for(String key : keywordsSplit){
								if(know.equals(key)){
									listaRevisoresSelecionados.add(r);
									model = new ReviewerTableModel(listaRevisoresSelecionados);
									tableSelectReviewer.setModel(model);
									flag = true;
									break;
								}
							}
							if(flag == true){
								break;
							}
						}
						if(listaRevisoresSelecionados.size() == 3){
							break;
						}
					}
					
					if(listaRevisoresSelecionados.size() < 3){
					
						if(listaRevisoresSelecionados.isEmpty()){
							int i = 0;
							ReviewerTableModel model;
							while(i<3){
								listaRevisoresSelecionados.add(reviewerList.get(i));
								model = new ReviewerTableModel(listaRevisoresSelecionados);
								tableSelectReviewer.setModel(model);
								i++;
							}
							
						}else{
							int i = listaRevisoresSelecionados.size();
							ReviewerTableModel model;
							while(i<3){
								listaRevisoresSelecionados.add(reviewerList.get(i));
								model = new ReviewerTableModel(listaRevisoresSelecionados);
								tableSelectReviewer.setModel(model);
								i++;
							}
							
						}
						
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
				}
			}
				
		}
	}
	{% endif %}
	
}
//#endif