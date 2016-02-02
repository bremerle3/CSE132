package lecture3s2;

public class HasRace {

	private int val = 0;

	// putting synchronized in the method declaration
	// surrounds all code in the method with  synchronized(this)
	public synchronized void bump10() {
		// slow things down
		// what's below is just explicit operations from the
		//   one-liner that is commented out
		// if not available, we wait until it is
		int oldVal = val;
		sleep(2000);
		int newVal = oldVal + 10;
		val = newVal;

		// at this point, the monitor is released
		System.out.println("Bumped by 10");
		// val = val + 10;
	}

	public synchronized void bump20() {
		int oldVal = val;
		sleep(2000);
		int newVal = oldVal + 20;
		val = newVal;
		
		// some other really long and big thing going on here
		//  that doesn't need the lock
		// .. then, I would surround the block of code I care about
		//  with synchronized(this) instead of using the whole method to
		//   to be synchronized

		System.out.println("Bumped by 20");

		// val = val + 20;
	}

	public static void main(String[] args) throws InterruptedException {
		HasRace hr = new HasRace();
		Thread t1 = new Thread() {
			public void run() {
				hr.bump10();
			}
		};
		t1.start();
		Thread t2 = new Thread() {
			public void run() {
				hr.bump20();
			}
		};
		t2.start();
		// if we fail to wait for the threads to finish, we saw 0
		// so we wait:
		t1.join();
		t2.join();
		System.out.println("We get " + hr.val);
	}

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch(Throwable t) {

		}
	}

}
