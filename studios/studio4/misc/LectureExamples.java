package studio4.misc;

public class LectureExamples {
	//
	// Moral of Studio 3:
	// Hold a lock only when you need it
	//    to achieve reliable results on variables shared by Threads
	// Release the lock as soon as you can
	// Avoid holding locks on more than one object at a time
	//
	// Because of the above, and because obtaining a lock has some cost,
	//  most code is written single-threaded, so that obtaining locks is
	//  left to those who use such code in a multithreaded environment.
	//
	// Early Java was more lock-crazy.  Vector is thread-safe (Java 1.0),
	//   but ArrayList (same functionality) is not thread-safe (Java 1.1)
	//
	// True or false:  immutable objects are always thread-safe
	// 
	
	private int balance = 0;
	
	/**
	 * Because the following whole method is synchronized, the Thread
	 *   will hold its lock on "this" until the method returns.
	 * If this is the earliest method to obtain the lock for the Thread,
	 *   then when the thread returns, the lock will be released.
	 */
	public synchronized void foo() {
		//  can only get here once the Thread
		//  holds the lock on "this" object
		//
		balance = balance + 10; // would be unsafe without a lock
		bar();
	}
	
	private synchronized void bar() {
		// since foo is the only method to
		// call bar, and the Thread must already
		// have the lock on "this" to get into foo(), no Thread
		// will ever wait to get into bar().
		// With bar() private, we really don't have to make it synchronized
		//  although no harm comes from having it that way.
		// If bar() were public, we'd have to make it synchronized to get
		//  thread-safe behavior on balance
		//
		balance = balance + 1; // unsafe without a lock
	}
	
	public void sameAsFoo() {
		// this method behaves just as foo does, in the sense that
		// no Thread can pass the next line without
		// a lock on "this".  
		//
		// This syntax shows more accurately what
		// happens when such a method is entered.
		//
		// This method is exactly the same as putting a
		// synchronized keyword on the method declaration
		synchronized(this) {
			balance = balance + 10; // unsafe without a lock
			//
			// All other code for the method could go here
			//
		}
		// No code here
	}
	
	/*
	 * This method holds the lock only while needed.
	 * Generally, it's a good idea to hold the lock only
	 * when you need it.
	 */
	public void moreFlexible() {
		// Do some stuff here
		// that needs no lock
		// x is a local variable, so no other thread can touch it
		int x = 3 + 5;
		// Now get the lock
		synchronized(this) {
			// Thread must have lock on "this" at this point
			balance = balance + 20; // unsafe without a lock
		}
		// Now the lock is released
		//
		// do more stuff
		int z = x - balance;
	}
	
	//
	//  This models what we did in studio when we made
	//  the transfer method synchronized
	//
	public void canDeadlockTransfer(LectureExamples other) {
		//
		// Get our first lock
		//
		synchronized(this) {
			// while holding that lock, get the other lock as well
			synchronized(other) {
				this.balance = this.balance - 20;   // unsafe without "this" lock
				other.balance = other.balance + 20; // unsafe without "other" lock
			}
		}
		//
		// The above scenario can deadlock -- do you see how and why?
		//
	}
	
	/*
	 * As we learned in studio, it's nice to hold just one lock
	 * at a time, only for as long as we need it
	 */
	
	public void nonDeadlockingTransfer(LectureExamples other) {
		//
		// Do some stuff that doesn't need a lock
		//
		int dep = 3*4+7;
		synchronized(this) {
			balance = balance - dep; // unsafe without "this" lock
		}
		//
		// At this point, the Thread's lock on "this" should
		//   be released, assuming it did not have the lock
		//   before hitting this synchronized block.
		//
		// do some more stuff that needs no lock
		//
		dep = dep * 2;
		//
		// Now get the other lock
		//
		synchronized(other) {
			//
			// Here the Thread holds a lock only on "other"
			other.balance = other.balance + dep; // unsafe without "other" lock
		}
		//
		// At this point, the Thread's lock on "other" should be released
		//
		//
		// This method can never deadlock. Do you see why?
		//
	}

}
