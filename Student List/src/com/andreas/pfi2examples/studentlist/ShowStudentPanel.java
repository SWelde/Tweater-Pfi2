package com.andreas.pfi2examples.studentlist;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

/**
 * Information panel, read-only composite JPanel.
 * 
 * @author andreas
 * 
 */
public class ShowStudentPanel extends JPanel {
	private JLabel firstname;
	private JLabel lastname;
	private JLabel lblId;
	private JLabel id;

	/**
	 * Create the panel.
	 */
	public ShowStudentPanel() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null), "Student", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 100, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		lblId = new JLabel("id");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		add(lblId, gbc_lblId);

		id = new JLabel("...");
		id.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_id = new GridBagConstraints();
		gbc_id.anchor = GridBagConstraints.EAST;
		gbc_id.insets = new Insets(0, 0, 5, 0);
		gbc_id.gridx = 1;
		gbc_id.gridy = 0;
		add(id, gbc_id);

		JLabel lblFirstName = new JLabel("First Name");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 1;
		add(lblFirstName, gbc_lblFirstName);

		firstname = new JLabel("...");
		firstname.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_firstname = new GridBagConstraints();
		gbc_firstname.anchor = GridBagConstraints.EAST;
		gbc_firstname.insets = new Insets(0, 0, 5, 0);
		gbc_firstname.gridx = 1;
		gbc_firstname.gridy = 1;
		add(firstname, gbc_firstname);

		JLabel lblLastName = new JLabel("Last Name");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 0, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 2;
		add(lblLastName, gbc_lblLastName);

		lastname = new JLabel("...");
		lastname.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lastname = new GridBagConstraints();
		gbc_lastname.anchor = GridBagConstraints.EAST;
		gbc_lastname.gridx = 1;
		gbc_lastname.gridy = 2;
		add(lastname, gbc_lastname);

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id.getText();
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id.setText(id);
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname.getText();
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname.setText(firstname);
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname.getText();
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname.setText(lastname);
	}

	/**
	 * Change the contens of the JLabels that describe the selected student.
	 * 
	 * @param student
	 */
	public void setSelection(Student student) {
		setId(Integer.toString(student.getId()));
		setFirstname(student.getFirstName());
		setLastname(student.getLastName());
	}
}
