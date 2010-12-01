package com.andreas.pfi2examples.studentlist;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author andreas
 * 
 */
public class NewStudentDialog extends JDialog {

	private MainWindow parent;

	private final JPanel contentPanel = new JPanel();
	private AddStudentPanel addStudentPanel;

	/**
	 * Create the dialog.
	 * 
	 * We've now added a parameter "MainWindow parent" to this default
	 * constructor, this is so we can link to the parent frame.
	 */
	public NewStudentDialog(final MainWindow parent) {
		this.parent = parent;

		setModal(true);
		setTitle("Add New Student");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			addStudentPanel = new AddStudentPanel();
			contentPanel.add(addStudentPanel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						/* Save the new student in the parent (MainWindow) */
						parent.addStudent(getAddStudentPanel().getFirstName()
								.getText(), getAddStudentPanel().getLastName()
								.getText());
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						/* Exit the dialog, don't save anything */
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		this.pack();
	}

	public AddStudentPanel getAddStudentPanel() {
		return addStudentPanel;
	}
}
