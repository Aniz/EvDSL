package riseevents.ev.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import riseevents.ev.exception.RepositoryException;
import riseevents.ev.table.NewOptionTableModel;

import javax.swing.JButton;

public class NewOptionUpdateScreenP extends JInternalFrame {
	private static NewOptionUpdateScreenP instanceNewOptionUpdateScreenP;
	private NewOptionTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	
	JButton btnBack ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public NewOptionUpdateScreenP() {
		

	}
	public static NewOptionUpdateScreenP getInstanceNewOptionUpdateScreenP() {
		return null;
	}
	
}