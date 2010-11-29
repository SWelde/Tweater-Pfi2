package com.andreas.pfi2examples.twitterstream;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private JPanel contentPane;
	private LoginPanel myLoginPanel;
	private StreamPanel streamPanel;

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

		myLoginPanel = new LoginPanel();
		myLoginPanel.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readTweets();
			}
		});

		streamPanel = new StreamPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(
								gl_contentPane.createParallelGroup(
										Alignment.TRAILING).addComponent(
										streamPanel, Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE, 560,
										Short.MAX_VALUE).addComponent(
										myLoginPanel, Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE, 560,
										Short.MAX_VALUE)).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(streamPanel, GroupLayout.PREFERRED_SIZE,
								249, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(myLoginPanel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
	}

	/**
	 * Launching the SwingWorker class defined above, this method is used in
	 * both the button and the password field.
	 */
	private void readTweets() {
		MyTweetWorker tweeter = new MyTweetWorker(this);
		tweeter.execute();

		myLoginPanel.getButton().setEnabled(false);
		myLoginPanel.getUsernameField().setEnabled(false);
		myLoginPanel.getPasswordField().setEditable(false);
	}

	protected LoginPanel getMyLoginPanel() {
		return myLoginPanel;
	}

	protected void initDataBindings() {
	}

	protected StreamPanel getStreamPanel() {
		return streamPanel;
	}
}
