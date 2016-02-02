package studio4.puzzler;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Random;



public class MultLockTest {

	private boolean allLocksHeld;

	@Test
	public void checkLocks() {
		
		//
		// Generate at least 10 things, but make it a bit random
		//
		int numThings = 50 + new Random().nextInt(50);
		final Object[] things = new Object[numThings];
		for (int i=0; i < numThings; ++i) {
			things[i] = new Object();
		}
		
		// Make the implementation prove it had all locks
		this.allLocksHeld = false;
		
		MultipleLock ml = new MultipleLock(things);
		ml.lockInvoke(
				new Runnable() {
					public void run() {
						boolean ans = true;
						//
						//  Check that the Thread holds a lock on each thing
						//
						for (Object thing : things) {
							if (!Thread.holdsLock(thing))
								ans = false;
						}
						//
						//   Record the answer so we can test outside
						//
						allLocksHeld = ans;
					}

				}
		);
		//
		// This will fail if all locks were not held when the Runnable ran
		//
		assertTrue(allLocksHeld);
	}

}
