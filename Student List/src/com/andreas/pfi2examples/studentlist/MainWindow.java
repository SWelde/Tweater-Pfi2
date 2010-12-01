package com.andreas.pfi2examples.studentlist;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private StudentListPanel studentListPanel;

	private ShowStudentPanel showStudentPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 442);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New Student");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewStudentDialog dialog = new NewStudentDialog(MainWindow.this);
				dialog.setVisible(true);
			}
		});
		mnFile.add(mntmNew);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* Close the JFrame */
				dispose();
			}
		});
		mnFile.add(mntmExit);

		JMenu mnHjlp = new JMenu("Hjälp");
		menuBar.add(mnHjlp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* Show the about dialog */
				AboutDialog aboutDialog = new AboutDialog();
				aboutDialog.setVisible(true);
			}
		});
		mnHjlp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		studentListPanel = new StudentListPanel(MainWindow.this);

		showStudentPanel = new ShowStudentPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(studentListPanel,
								GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(showStudentPanel,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addGap(38)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(
								gl_contentPane.createParallelGroup(
										Alignment.BASELINE).addComponent(
										studentListPanel,
										GroupLayout.PREFERRED_SIZE, 278,
										GroupLayout.PREFERRED_SIZE)
										.addComponent(showStudentPanel,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addContainerGap(95, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	public StudentListPanel getStudentListPanel() {
		return studentListPanel;
	}

	public ShowStudentPanel getShowStudentPanel() {
		return showStudentPanel;
	}

	/*
	 * We use this variable when creating new students in the list, as a unique
	 * ID
	 */
	private int id;

	public void addStudent(String fname, String lname) {
		/* Add a temp student description */
		getStudentListPanel().addStudent(new Student(id++, fname, lname));
	}
}
