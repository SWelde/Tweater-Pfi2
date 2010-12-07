package mah.k3.pfi2.twitterstream.event;

import java.util.EventListener;

/**
 * Listener interface for our NewStatusUpdateEvent, any class that we want
 * listening to our MyTweetWorker needs to implement this interface.
 * 
 * @author andreas
 * 
 */
public interface NewStatusUpdateEventListener extends EventListener {
	public void NewStatusUpdate(NewStatusUpdateEvent evt);
}
