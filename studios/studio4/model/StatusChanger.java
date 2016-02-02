package studio4.model;

import java.beans.PropertyChangeSupport;
import java.util.Random;

import static studio4.misc.Sleeper.sleep;

/**
 * A class to propagate status changes via firePropertyChange.
 * When the status changes, up to maxTime time is spent simulating a delay
 * due to the status change.
 *
 */

abstract public class StatusChanger {

	public static boolean logging = true;
	public static int     DELAY   = 200;
	
	final protected PropertyChangeSupport pcs;
	final protected Random rand;
	final protected int maxTime;
	
	/**
	 * 
	 * @param maxTime maximum time delaying when status changes
	 */
	public StatusChanger(int maxTime) {
		this.maxTime = maxTime;
		this.rand = new Random();
		pcs = new PropertyChangeSupport(this);

	}
	
	abstract public String getName();

	public PropertyChangeSupport getPCS() {
		return pcs;
	}

	/**
	 * announce the message, sleep a little
	 * @param message
	 */
	public void status(String message) {
		if (logging) {
			System.out.println(getName() + ": " + " now " + message);
		}
		//
		// The use of null below pushes out the message no matter what
		//   You could use the second and third parameters to
		//   cause a message to be pushed only if something has
		//   changed.
		//
		pcs.firePropertyChange(message, null, null);
		//
		// Take some time to do this thing
		//
		if (maxTime > 0) sleep((rand.nextInt(maxTime)+1)*1000);	
		else sleep(DELAY);
	}

}