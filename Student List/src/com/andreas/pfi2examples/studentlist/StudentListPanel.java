package com.andreas.pfi2examples.studentlist;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;

public class StudentListPanel extends JPanel implements ListSelectionListener {

	/* Parent window, for linking to other components&panels */
	private MainWindow parent;

	/* JList components */
	private ArrayList<Student> students = new ArrayList<Student>();
	private JList list;

	private DefaultListModel model = new DefaultListModel();

	/**
	 * Slightly modified constructor, we made a link to the MainWindow parent
	 * for this container so that we can make affect other components of the
	 * GUI.
	 * 
	 * @param parent
	 */
	public StudentListPanel(MainWindow parent) {
		/* This is the only change we made really... */
		this.parent = parent;

		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null), "Student List", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(model);
		list.addListSelectionListener(this);
	}

	public void addStudent(Student newStudent) {
		int pos = getList().getModel().getSize();
		model.add(pos, newStudent);
		students.add(newStudent);
	}

	public JList getList() {
		return list;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		/*
		 * When we make a new selection, we want to update the JLabels inside
		 * the ShowStudentPanel in the parent window.
		 */
		parent.getShowStudentPanel().setSelection(students.get(((JList) e.getSource())
				.getSelectedIndex()));
	}
}
