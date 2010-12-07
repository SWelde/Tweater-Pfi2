package mah.k3.pfi2.twitterstream;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import mah.k3.pfi2.twitterstream.event.NewStatusUpdateEvent;
import mah.k3.pfi2.twitterstream.event.NewStatusUpdateEventListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

@SuppressWarnings("serial")
public class Main extends JFrame implements NewStatusUpdateEventListener {

	private JPanel contentPane;
	private StreamPanel streamPanel;
	private LoginPanel loginPanel;

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		streamPanel = new StreamPanel();

		loginPanel = new LoginPanel();
		loginPanel.getPasswordField().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readTweets();
			}
		});
		loginPanel.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readTweets();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																loginPanel,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																560,
																Short.MAX_VALUE)
														.addComponent(
																streamPanel,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																560,
																Short.MAX_VALUE))
										.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addComponent(streamPanel, GroupLayout.DEFAULT_SIZE,
								253, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(loginPanel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * Launching the SwingWorker class defined above, this method is used in
	 * both the button and the password field.
	 * 
	 * This should obviously fetch the result of the login to Twitter before
	 * starting the MyTweetWorker SwingWorker, so we don't try to read the
	 * TwitterStream without an authenticated login.
	 */
	private void readTweets() {
		/* Create the MyTweetWorker thread */
		MyTweetWorker tweeter = new MyTweetWorker(getLoginPanel()
				.getUsernameField().getText(), getLoginPanel()
				.getPasswordField().getPassword());
		/*
		 * Add a listener for this specific worker, in this case we'll say that
		 * the Main.java will listen to the events
		 */
		tweeter.addListener(this);

		/* Start the worker */
		tweeter.execute();

		loginPanel.getButton().setEnabled(false);
		loginPanel.getUsernameField().setEnabled(false);
		loginPanel.getPasswordField().setEnabled(false);
	}

	/**
	 * Returns the StreamPanel, we use this method to access any sub-components
	 * of the StreamPanel.
	 * 
	 * @return
	 */
	protected StreamPanel getStreamPanel() {
		return streamPanel;
	}

	/**
	 * Returns the LoginPanel, we use this method to access any sub-components
	 * of the LoginPanel.
	 * 
	 * @return
	 */
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	@Override
	public void NewStatusUpdate(NewStatusUpdateEvent evt) {
		List<String> updates = evt.getMessages();
		for (String update : updates)
			getStreamPanel().getTextArea().append(update);
	}
}
