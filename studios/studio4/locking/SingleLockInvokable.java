package studio4.locking;


/**
 * For a single object, its lock can be obtained and a Runnable then run.
 * But also, just for a single object, a unique ID for that object can be returned.
 *
 */
public interface SingleLockInvokable extends LockInvokable {
	
	/**
	 * Return a unique number for the object that will be locked
	 * @return
	 */
	public int getUniqueID();

}
