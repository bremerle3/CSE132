package studio4.puzzler;

import studio4.locking.LockInvokable;


public class MultipleLock implements LockInvokable {

	private Object[] things;

	public MultipleLock(Object[] thingsToLock) {
		things = thingsToLock;
	}

	/**
	 * After holding a lock on each Object in things, run r.
	 * You have to complete this method.
	 */
	public void lockInvoke(Runnable r) {
	}

}
