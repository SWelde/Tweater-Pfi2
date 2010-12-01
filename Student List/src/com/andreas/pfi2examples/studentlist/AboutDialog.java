package com.andreas.pfi2examples.studentlist;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 222);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AboutDialog.class.getResource("/com/andreas/pfi2examples/studentlist/images/logo.png")));
		label.setSize(new Dimension(150, 150));
		label.setPreferredSize(new Dimension(150, 150));
		JLabel lblStudentListApplication = new JLabel("Student List Application");
		lblStudentListApplication.setFont(new Font("Dialog", Font.BOLD, 18));
		JLabel lblOneSentenceDescription = new JLabel("<html>One sentence description of the <br>application and it's core functionality.</html>");
		lblOneSentenceDescription.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblAuthor = new JLabel("Author");
		
		JLabel lblVersion = new JLabel("Version");
		
		JLabel lblWebsite = new JLabel("Website");
		
		JLabel lblmalmUniversity = new JLabel("http://www.mah.se");
		lblmalmUniversity.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_2 = new JLabel("1.0");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblMyNameOr = new JLabel("Mr. Mister");
		lblMyNameOr.setFont(new Font("Dialog", Font.PLAIN, 12));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStudentListApplication, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
						.addComponent(lblOneSentenceDescription, GroupLayout.PREFERRED_SIZE, 254, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblWebsite)
								.addComponent(lblVersion)
								.addComponent(lblAuthor))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMyNameOr, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
								.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
								.addComponent(lblmalmUniversity, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStudentListApplication)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblOneSentenceDescription)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAuthor)
								.addComponent(lblMyNameOr))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVersion)
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblWebsite)
								.addComponent(lblmalmUniversity))))
					.addGap(2))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						/* Exit the dialog when we press OK */
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
