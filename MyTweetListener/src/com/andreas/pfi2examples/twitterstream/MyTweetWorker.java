package com.andreas.pfi2examples.twitterstream;

import java.util.List;

import javax.swing.SwingWorker;

import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/**
 * SwingWorker object, will handle starting the TwitterStream thread and post
 * callbacks to the UI. (Hint: We should never update the widget/s state from
 * anywhere but the event dispatch thread, the SwingWorker has a fancy method
 * called "process" that queues on to the EDT, so any program updating Swing
 * widgets from the SwingWorker process method should be considered fairly
 * "thread safe")
 * 
 * @author andreas
 * 
 */
public class MyTweetWorker extends SwingWorker<String, String> implements
		StatusListener {

	TwitterStream mTwitterStream;
	Main parent;

	/**
	 * Constructor, we need to link to our mainclass to get a hold on the UI
	 * elements.
	 * 
	 * @param parent
	 */
	public MyTweetWorker(Main parent) {
		super();
		this.parent = parent;
	}

	/* SwingWorker methods */
	@Override
	protected String doInBackground() throws Exception {
		String login = parent.getLoginPanel().getUsernameField().getText();
		String password = new String(parent.getLoginPanel()
				.getPasswordField().getPassword());

		mTwitterStream = new TwitterStreamFactory(this).getInstance(login,
				password);
		mTwitterStream.sample();

		return null;
	}

	@Override
	protected void process(List<String> chunks) {
		for (String s : chunks)
			parent.getStreamPanel().getTextArea().append(s);
	}

	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
	}

	/* StatusListener methods */
	@Override
	public void onStatus(Status arg0) {
		StringBuilder sb = new StringBuilder();
		sb.append(arg0.getUser().getName()).append(" ").append(arg0.getText())
				.append("\n");

		this.publish(sb.toString());

	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(Exception arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
		// TODO Auto-generated method stub

	}

}
