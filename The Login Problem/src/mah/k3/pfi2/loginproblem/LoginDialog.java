nu ändrar jag här, med det här meddelandet
package mah.k3.pfi2.loginproblem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import org.jdesktop.beansbinding.ObjectProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class LoginDialog extends JDialog implements WindowListener {

	/* Link to the mainwindow */
	private MainWindow parent;

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;

	private boolean loginok = false;
	private JButton btnAbout;

	/**
	 * Create the dialog.
	 */
	public LoginDialog(MainWindow parent) {
		setTitle("Loginlogon");
		setForeground(new Color(0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginDialog.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
		setResizable(false);
		setModal(true);
		/* Link to the mainwindow */
		this.parent = parent;

		setBounds(100, 100, 222, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(250, 250, 210));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setFont(new Font("Courier New", Font.PLAIN, 15));
			GridBagConstraints gbc_lblUsername = new GridBagConstraints();
			gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsername.gridx = 0;
			gbc_lblUsername.gridy = 0;
			contentPanel.add(lblUsername, gbc_lblUsername);
		}
		{
			textField = new JTextField();
			textField.setBackground(new Color(255, 255, 255));
			textField.setToolTipText("Enter your username");
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.gridwidth = 5;
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 0;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Courier New", Font.PLAIN, 15));
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.anchor = GridBagConstraints.EAST;
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 0;
			gbc_lblPassword.gridy = 2;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBackground(new Color(255, 255, 255));
			passwordField.setToolTipText("Enter your password");
			GridBagConstraints gbc_passwordField = new GridBagConstraints();
			gbc_passwordField.gridwidth = 5;
			gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordField.gridx = 0;
			gbc_passwordField.gridy = 3;
			contentPanel.add(passwordField, gbc_passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(250, 250, 210));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAbout = new JButton("About");
				btnAbout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				buttonPane.add(btnAbout);
			}
			{
				{
					JButton okButton = new JButton("OK");
					buttonPane.add(okButton);
					okButton.setFont(new Font("Courier New", Font.PLAIN, 15));
					okButton.setForeground(Color.BLACK);
					okButton.setBackground(new Color(240, 230, 140));
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							/* Try to login */
							String username = textField.getText();
							String password = new String(passwordField
									.getPassword());
							LoginDialog.this.parent.login(username, password);
							loginok = true;
							/* Close the dialog */
							dispose();
						}
					});
					okButton.setActionCommand("OK");
					getRootPane().setDefaultButton(okButton);
				}
			}
		}

		this.addWindowListener(this);
		pack();
		initDataBindings();
	}

	/* WINDOW LISTENER METHODS */
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		if (!loginok) {
			/* Try to login with wrong credentials */
			LoginDialog.this.parent.login("asd", "asd");
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	protected void initDataBindings() {
		ObjectProperty<JButton> jButtonObjectProperty = ObjectProperty.create();
		AutoBinding<JTextField, JTextField, JButton, JButton> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ, textField, btnAbout, jButtonObjectProperty);
		autoBinding.bind();
	}
}
