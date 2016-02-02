package lecture3s1;

public class HasRaces2 {

	private int val = 100;
	private String s1 = new String("hello");
	private String s2 = new String("hello");


	// saying synchronized on a method declaration
	// is exactly the same as putting synchronized(this).... around
	//  all of its code
	public synchronized void bump10() {
		// val = val + 10;
		// slowing down the above:
		//  saying synchronized (this) means that
		//    only one thread can hold "this" monitor at time
		int oldVal = val;
		// sleep to show a problem that happens
		sleep(2000);
		int newVal = oldVal + 10;
		this.val = newVal;
		//                          ... to here execute "atomically"
		//     meaning, all at once, or not yet at all...as one complete package

		System.out.println("Bumped by 10");
	}

	public synchronized void bump20() {
		// val = val + 20;
		int oldVal = val;
		// sleep here too
		sleep(2000);
		int newVal = oldVal + 20;
		val = newVal;
		
		// if there is something really long about to happen...
		//  that doesn't need the lock
		//  then I should have used the form of synchronized..see HasRaces

		System.out.println("Bumped by 20");
	}

	public static void main(String[] args) throws InterruptedException {
		HasRaces2 hr = new HasRaces2();
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

		t1.join();
		t2.join();
		System.out.println("Value at end is " + hr.val);
	}

	private static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
	}

}
