package mah.k3.pfi2.twitterstream;

import java.util.List;

import javax.swing.SwingWorker;
import javax.swing.event.EventListenerList;

import mah.k3.pfi2.twitterstream.event.NewStatusUpdateEvent;
import mah.k3.pfi2.twitterstream.event.NewStatusUpdateEventListener;

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

	/*
	 * The TwitterStream object, this actually launches a thread of it's own
	 * which takes care of polling new information from the Twitter servers.
	 */
	private TwitterStream mTwitterStream;

	/* Login information */
	private String username;
	private char[] password;

	/*
	 * List of objects listening to this specific instance, this is a very
	 * special kind of list (not like ArrayList etc).
	 */
	EventListenerList listenerList = new EventListenerList();

	/**
	 * MyTweetWorker requires a string user name and a char array password, it
	 * will then try to login to the Twitter Servers and read the stream.
	 * 
	 * @param username
	 * @param password
	 */
	public MyTweetWorker(String username, char[] password) {
		super();
		this.username = username;
		this.password = password;
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
		mTwitterStream = new TwitterStreamFactory(this).getInstance(username,
				new String(password));
		mTwitterStream.sample();

		return null;
	}

	@Override
	protected void process(List<String> chunks) {
		/* Fire a new event to all listeners */
		newStatusUpdateEvent(new NewStatusUpdateEvent(this, chunks));
	}

	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
	}

	/*
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

	/*
	 * NewStatusUpdateEvent methods.
	 * 
	 * These methods are used to add and remove listeners for this specific
	 * instance of the MyTweetWorker. Each instance has their own list of
	 * listeners who receive any events that this instance launches.
	 */
	/**
	 * Used to add a new receiver of events from this instance.
	 */
	public void addListener(NewStatusUpdateEventListener listener) {
		listenerList.add(NewStatusUpdateEventListener.class, listener);
	}

	/**
	 * Used to remove a receiver of events from this instance.
	 */
	public void removeListener(NewStatusUpdateEventListener listener) {
		listenerList.remove(NewStatusUpdateEventListener.class, listener);
	}

	/**
	 * Used by MyTweetWorker to send new statusUpdateEvents to all current
	 * listeners.
	 */
	void newStatusUpdateEvent(NewStatusUpdateEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == NewStatusUpdateEventListener.class) {
				((NewStatusUpdateEventListener) listeners[i + 1])
						.NewStatusUpdate(evt);
			}
		}

	}
}
