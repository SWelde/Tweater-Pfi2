package com.andreas.pfi2examples.twitterstream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

/**
 * Just a simple Login widget. To make the Main.java implementation
 * easier/cleaner we define the login panel as it's own component which we can
 * just place as any other standard component in the drag-and-drop interface.
 * 
 * @author andreas
 * 
 */
public class LoginPanel extends JPanel {
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton btnReadTweetSteam;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null), "Login", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		setLayout(new MigLayout("", "[][][grow]", "[][][]"));

		JLabel lblLogin = new JLabel("Username");
		add(lblLogin, "cell 1 1,alignx trailing");

		usernameField = new JTextField();
		add(usernameField, "cell 2 1,growx");
		usernameField.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		add(lblPassword, "cell 1 2,alignx trailing");

		passwordField = new JPasswordField();
		add(passwordField, "flowx,cell 2 2,growx");

		btnReadTweetSteam = new JButton("Read Tweet Steam");
		add(btnReadTweetSteam, "cell 2 2");

	}

	public JTextField getUsernameField() {
		return usernameField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JButton getButton() {
		return btnReadTweetSteam;
	}
}
