package studio4.model;

import studio4.locking.DoubleLock;
import studio4.locking.LockPub;
import static studio4.misc.Sleeper.sleep;

public class Phil extends Actor {

	private String name;
	final public LockPub l1, l2;
	
	final private DoubleLock dlock; // will be used in studio
	
	public Phil(String name, Chop one, Chop two) {
		super("Phil " + name, 2);
		this.name = name;
		l1 = new LockPub(one, "Phil " + name);
		l2 = new LockPub(two, "Phil " + name);
		//
		// We'll use dlock in studio,
		//    once you have implemented DoubleLock properly
		// But it's OK to go ahead and construct it as follows:
		dlock = new DoubleLock(l1, l2);
	}

	/**
	 * Goes from hungry -->  eating --->  studying
	 * Cannot eat unless it has a lock on both of its Chop sticks
	 */
	public synchronized void perform() {
		
		//
		// This is the Runnable that will be executed
		//   once both locks (chopstics) are obtained:
		//
		final Runnable eating = 
			new Runnable() {
			public void run() {
				status("eating");
				sleep(300);  // slow eater
				status("full");
			}
		};



		status("hungry");
		//
		// Once you have DoubleLock written, 
		//     replace the following code...
		//
		l1.lockInvoke(
				new Runnable() {
					public void run() {
						sleep(5);
						l2.lockInvoke(
								
								eating
								
						);
					}
				}
		);
		//
		//  ... by the much simpler:
		//  dlock.lockInvoke(eating);
		//
		//  we can then make DoubleLock smart about obtaining both locks
		//

		status("studying");
	}

	public String toString() {
		return "Phil " + name;
	}

}
