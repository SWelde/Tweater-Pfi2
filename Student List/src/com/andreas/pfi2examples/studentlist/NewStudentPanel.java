package com.andreas.pfi2examples.studentlist;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Composite JPanel, describes all the fields we need to enter for our new
 * Students.
 * 
 * @author andreas
 * 
 */
public class NewStudentPanel extends JPanel {
	private JTextField firstName;
	private JTextField lastName;

	/**
	 * Create the panel.
	 */
	public NewStudentPanel() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null), "Student information", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 75, 115, 0 };
		gridBagLayout.rowHeights = new int[] { 18, 18, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblFirstName = new JLabel("First name");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 0;
		add(lblFirstName, gbc_lblFirstName);

		firstName = new JTextField();
		GridBagConstraints gbc_firstName = new GridBagConstraints();
		gbc_firstName.insets = new Insets(0, 0, 5, 0);
		gbc_firstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstName.gridx = 1;
		gbc_firstName.gridy = 0;
		add(firstName, gbc_firstName);
		firstName.setColumns(10);

		JLabel lblLastName = new JLabel("Last name");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.EAST;
		gbc_lblLastName.insets = new Insets(0, 0, 0, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 1;
		add(lblLastName, gbc_lblLastName);

		lastName = new JTextField();
		GridBagConstraints gbc_lastName = new GridBagConstraints();
		gbc_lastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastName.gridx = 1;
		gbc_lastName.gridy = 1;
		add(lastName, gbc_lastName);
		lastName.setColumns(10);

	}

	public JTextField getFirstName() {
		return firstName;
	}

	public JTextField getLastName() {
		return lastName;
	}
}
