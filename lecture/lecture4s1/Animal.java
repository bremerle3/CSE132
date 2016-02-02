package lecture4s1;

import java.beans.PropertyChangeSupport;

abstract public class Animal implements Runnable {
	
	// the pcs object is where we publish
	//   all of our messages
	private PropertyChangeSupport pcs;
	
	public Animal() {
		this.pcs = new PropertyChangeSupport(this);
	}
	
	// This class doesn't know what the life of a given
	//   Animal will be like, so we'll make that abstract
	// abstract means that if a class wants to be instantiable,
	//  then it must implement all the abstract methods
	public abstract void run();
	public abstract String getName();
	
	public PropertyChangeSupport getPCS() {
		return this.pcs;
	}
	
	// send a message to the world on our pcs object
	//  that the action has happened
	// sleep for 2 seconds, simulate the action
	public void act(String action) {
		System.out.println(getName() + " is " + action + "ing");
		//
		// send the message to the world, but there's no other info
		//   associated with it, so we pass null and null
		pcs.firePropertyChange(action, null, null);
		sleep(2);
	}
	
	private static void sleep(int secs) {
		try {
			Thread.sleep(1000 * secs);
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
	}

}
