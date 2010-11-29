package com.andreas.pfi2examples.twitterstream;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ObjectProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField mLogin;
	private JPasswordField mPassword;
	private JTextArea textArea;
	private JButton btnLogin;
	private JPanel loginpanel;

	/**
	 * Never do anything in this method.
	 */
	/**
	 * Launch the apbbplication.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * This method should NOT be edited by hand, or try to never edit this. Some
	 * times it's easier to just edit this method...
	 */
	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel streampanel = new JPanel();
		streampanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Stream",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		loginpanel = new JPanel();
		loginpanel.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Login",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(
								gl_contentPane.createParallelGroup(
										Alignment.TRAILING).addComponent(
										streampanel, Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE, 560,
										Short.MAX_VALUE).addComponent(
										loginpanel, Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(streampanel, GroupLayout.DEFAULT_SIZE,
								251, Short.MAX_VALUE).addPreferredGap(
								ComponentPlacement.RELATED).addComponent(
								loginpanel, GroupLayout.PREFERRED_SIZE, 83,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));

		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_streampanel = new GroupLayout(streampanel);
		gl_streampanel.setHorizontalGroup(gl_streampanel.createParallelGroup(
				Alignment.LEADING).addComponent(scrollPane, Alignment.TRAILING,
				GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE));
		gl_streampanel.setVerticalGroup(gl_streampanel.createParallelGroup(
				Alignment.LEADING).addComponent(scrollPane, Alignment.TRAILING,
				GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE));

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setRows(10);
		textArea.setLineWrap(true);
		streampanel.setLayout(gl_streampanel);

		JLabel lblUsername = new JLabel("Username");

		JLabel lblPassword = new JLabel("Password");

		mLogin = new JTextField();
		mLogin.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if (mPassword.getPassword().length > 0
						&& mLogin.getText().length() > 0)
					btnLogin.setEnabled(true);
				else if (btnLogin.isEnabled())
					btnLogin.setEnabled(false);
			}
		});
		mLogin.setColumns(10);

		mPassword = new JPasswordField();
		mPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (mPassword.getPassword().length > 0
						&& mLogin.getText().length() > 0)
					readTweets();
			}
		});
		mPassword.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if (mPassword.getPassword().length > 0
						&& mLogin.getText().length() > 0)
					btnLogin.setEnabled(true);
				else if (btnLogin.isEnabled())
					btnLogin.setEnabled(false);
			}
		});

		btnLogin = new JButton("Read Tweets");
		btnLogin.setEnabled(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (mPassword.getPassword().length > 0
						&& mLogin.getText().length() > 0)
					readTweets();
			}
		});
		GroupLayout gl_loginpanel = new GroupLayout(loginpanel);
		gl_loginpanel
				.setHorizontalGroup(gl_loginpanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_loginpanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_loginpanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblUsername)
														.addComponent(
																lblPassword))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_loginpanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																Alignment.TRAILING,
																gl_loginpanel
																		.createSequentialGroup()
																		.addComponent(
																				mPassword,
																				GroupLayout.DEFAULT_SIZE,
																				302,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnLogin))
														.addComponent(
																mLogin,
																GroupLayout.DEFAULT_SIZE,
																332,
																Short.MAX_VALUE))
										.addContainerGap()));
		gl_loginpanel
				.setVerticalGroup(gl_loginpanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_loginpanel
										.createSequentialGroup()
										.addGroup(
												gl_loginpanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblUsername)
														.addComponent(
																mLogin,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_loginpanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblPassword)
														.addGroup(
																gl_loginpanel
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				mPassword,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				btnLogin)))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		loginpanel.setLayout(gl_loginpanel);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}

	/**
	 * SwingWorker object, will handle starting the TwitterStream thread and
	 * post callbacks to the UI. (Hint: We should never update the widget/s
	 * state from anywhere but the event dispatch thread, the SwingWorker has a
	 * fancy method called "process" that queues on to the EDT, so any program
	 * updating Swing widgets from the SwingWorker process method should be
	 * considered fairly "thread safe")
	 * 
	 * @author andreas
	 * 
	 */
	private class MyTweetListener extends SwingWorker<String, String> implements
			StatusListener {

		TwitterStream mTwitterStream;

		/* SwingWorker methods */
		@Override
		protected String doInBackground() throws Exception {
			String login = mLogin.getText();
			String password = new String(mPassword.getPassword());

			mTwitterStream = new TwitterStreamFactory(this).getInstance(login,
					password);
			mTwitterStream.sample();

			return null;
		}

		@Override
		protected void process(List<String> chunks) {
			for (String s : chunks)
				textArea.append(s);
		}

		/* StatusListener methods */
		@Override
		public void onDeletionNotice(StatusDeletionNotice arg0) {
		}

		@Override
		public void onException(Exception arg0) {
		}

		@Override
		public void onStatus(Status arg0) {
			StringBuilder sb = new StringBuilder();
			sb.append(arg0.getUser().getName()).append(" ").append(
					arg0.getText()).append("\n");

			this.publish(sb.toString());
		}

		@Override
		public void onTrackLimitationNotice(int arg0) {
		}
	}

	/**
	 * Launching the SwingWorker class defined above, this method is used in
	 * both the button and the password field.
	 */
	private void readTweets() {
		MyTweetListener tweeter = new MyTweetListener();
		tweeter.execute();

		btnLogin.setEnabled(false);
		mLogin.setEnabled(false);
		mPassword.setEnabled(false);
	}

	protected void initDataBindings() {
		ObjectProperty<JTextArea> jTextAreaObjectProperty = ObjectProperty
				.create();
		AutoBinding<JPanel, JPanel, JTextArea, JTextArea> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ, contentPane, textArea,
						jTextAreaObjectProperty);
		autoBinding.bind();
		//
		ObjectProperty<JButton> jButtonObjectProperty = ObjectProperty.create();
		AutoBinding<JPanel, JPanel, JButton, JButton> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ, contentPane, btnLogin,
						jButtonObjectProperty);
		autoBinding_1.bind();
		//
		ObjectProperty<JPanel> jPanelObjectProperty = ObjectProperty.create();
		AutoBinding<JPanel, JPanel, JPanel, JPanel> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ, contentPane,
						loginpanel, jPanelObjectProperty);
		autoBinding_2.bind();
	}
}
