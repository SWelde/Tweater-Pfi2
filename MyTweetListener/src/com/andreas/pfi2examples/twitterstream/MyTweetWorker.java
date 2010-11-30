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
	 * Constructor, we need to link to our Main.java instance to get a hold on
	 * the UI elements, the Main.java instance acts as a link to the parent
	 * class - by accessing the parents public or protected methods we can link
	 * to the exposed UI widgets.
	 * 
	 * @param parent
	 */
	public MyTweetWorker(Main parent) {
		super();
		this.parent = parent;
	}

	/**
	 * SwingWorker methods, these methods belong to the description of the
	 * SwingWorker type. The SwingWorker is a simplified threading class that
	 * makes it easy to post changes to the Swing widgets in the User Interface.
	 * 
	 * doInBackground - the method in which we do all our background
	 * logic/computation
	 * 
	 * process - the definition of what should happen on the User Interface, in
	 * this method we're allowed to access any widget in the UI without causing
	 * problems
	 * 
	 * done - if we need to close any streams or do some finishing works before
	 * we close this Thread for all eternity, do them in this method
	 */
	@Override
	protected String doInBackground() throws Exception {
		String login = parent.getLoginPanel().getUsernameField().getText();
		String password = new String(parent.getLoginPanel().getPasswordField()
				.getPassword());

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

	/**
	 * StatusListener methods.
	 * 
	 * These methods are inherited from the interface "StatusListener", by add
	 * the words "implements StatusListener" to the class definition we agree to
	 * adhere to the rules that define what a StatusListener object should look
	 * like.
	 * 
	 * This is a "lowest common denominator" rule, and it only states that we
	 * MUST include the methods mentioned below, although we are welcome to add
	 * more of our own methods if we choose to do so.
	 */
	@Override
	public void onStatus(Status arg0) {
		StringBuilder sb = new StringBuilder();
		if (arg0.getUser().getLang().equals("en")) {
			sb.append(arg0.getUser().getName() + " " + arg0.getText() + "\n");
		}
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
