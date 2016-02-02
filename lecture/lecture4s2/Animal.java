package lecture4s2;

import java.beans.PropertyChangeSupport;

abstract public class Animal implements Runnable {
	
	
	private PropertyChangeSupport pcs;

	public Animal() {
		// the pcs object is where we post our activity
		//   this is exclusive to this object, an Animal
		this.pcs = new PropertyChangeSupport(this);
	}
	// I want run to be abstract, so that a Mouse, Cat, Dog, etc.
	//  can define its own life via run()
	
	// the following line obligates any instantiable extension of Animal
	//   to provide a run method
	public abstract void run();
	
	public PropertyChangeSupport getPCS() {
		return this.pcs;
	}
	
	// let's obligate an extension to provide its name
	
	public abstract String getName();
	
	public void act(String action) {
		System.out.println("Animal " + getName() + " acting " + action);
		// to act, we are going to post the activity on pcs
		//  and then sleep for 2 seconds -- just to slow things down
		//  we provide null, null below -- to signify that there is
		//    no other information associated with the action
		pcs.firePropertyChange(action, null, null);
		sleep(2);
	}
	
	public static void sleep(int secs) {
		try {
			Thread.sleep(secs * 1000);
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
	}

}
