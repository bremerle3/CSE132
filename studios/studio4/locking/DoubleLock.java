package studio4.locking;

import static studio4.misc.Sleeper.sleep;


/**
 * To be implemented in studio * @author cytron
 *
 */
public class DoubleLock implements LockInvokable {
	
	private LockInvokable lock1, lock2;


	public DoubleLock(SingleLockInvokable o1, SingleLockInvokable o2) {
		this.lock1 = o1;
		this.lock2 = o2;
	}

	/**
	 * While holding both locks, run r.  Sleep a bit before getting the second lock.
	 */
	public void lockInvoke(final Runnable r) {
		// FIXME
		sleep(50);
	}

}


