package lecture3s1;

public class HasRaces {

	private int val = 100;
	private String s1 = new String("hello");
	private String s2 = new String("hello");

	public void bump10() {
		// val = val + 10;
		// slowing down the above:
		//  saying synchronized (this) means that
		//    only one thread can hold "this" monitor at time
		synchronized(s1) {  // obtain the lock on this object
			//                         Want to see from here....
			int oldVal = val;
			// sleep to show a problem that happens
			sleep(2000);
			int newVal = oldVal + 10;
			this.val = newVal;
			//                          ... to here execute "atomically"
			//     meaning, all at once, or not yet at all...as one complete package
		}
		System.out.println("Bumped by 10");
	}

	public void bump20() {
		// val = val + 20;
		synchronized(s2) {
			int oldVal = val;
			// sleep here too
			sleep(2000);
			int newVal = oldVal + 20;
			val = newVal;
		}
		System.out.println("Bumped by 20");
	}

	public static void main(String[] args) throws InterruptedException {
		HasRaces hr = new HasRaces();
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
