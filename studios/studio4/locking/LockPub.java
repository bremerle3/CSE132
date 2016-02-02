package studio4.locking;

import javax.swing.*;

import studio4.model.StatusChanger;

import java.awt.*;
import java.beans.*;



/** Lock with publisher to notify about status of the lock.  It is expected that there may
 * be many LockPub objects for a single object that is to be locked.  This class tracks the
 * status in terms of acquisition of the lock.
 *
 * {@link PropertyChangeSupport} messages sent:
 * <DL>
 *  <DT> request
 *  <DD> The lock has been requested 
 *  <DT> granted
 *  <DD> The lock has been granted.  This thread has obtained the lock on <code>o</code>.
 *  <DT> avail
 *  <DD> The lock is available. This is the LockPub's initial state.
 * </DL>
 */
public class LockPub extends StatusChanger implements SingleLockInvokable {

	private Object o;
	private String id;

	/** @param o The object for which a lock will be obtained.
	 */
	public LockPub(Object o, String s) {
		super(1);
		this.o = o;
		this.id = s;
	}
	

	/**
	 * Returns a unique number for "o"
	 */
	public int getUniqueID() {
		return 0;   // FIXME
	}

	public PropertyChangeSupport getPCS() { return pcs; }

	/** Request the lock.  Once it is granted, run the parameter.  When the runnable
	 *  returns, make the lock available again.  This method is synchronized, but it
	 *  is not expected that multiple threads would use the same LockPub even though
	 *  they are after the same lock.
	 */
	public synchronized void lockInvoke(Runnable r) {
		request();
		try {
			synchronized (o) {
				granted();
				r.run();
			}
		} finally {
			avail();
		}
	}

	private void avail()   { status("avail");   }
	private void request() { status("request"); }
	private void granted() { status("granted"); }


	public String toString() {
		return "Lock " + id + " for " + o;
	}

	@Override
	public String getName() {
		return this.toString();
	}
}


