package mah.k3.pfi2.twitterstream.event;

import java.util.EventObject;
import java.util.List;

import mah.k3.pfi2.twitterstream.MyTweetWorker;

/**
 * This is the event that is launched by the MyTweetWorker thread. When creating
 * the event we supply any extra information that we want the event to carry -
 * in this case we supply the list of strings.
 * 
 * We decide to supply a list of strings because the SwingWorker thread might
 * batch all new messages rather than sending them one at a time.
 * 
 * @author andreas
 * 
 */
public class NewStatusUpdateEvent extends EventObject {
	private List<String> messages;

	public NewStatusUpdateEvent(MyTweetWorker src, List<String> messages) {
		super(src);
		this.messages = messages;
	}

	/**
	 * @return the messages
	 */
	public List<String> getMessages() {
		return messages;
	}
}
