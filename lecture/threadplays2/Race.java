package threadplays2;

public class Race {
	
	private int c;

	public static void main(String[] args) {
		// anonymous inner classes for my threads
		Race r = new Race();
		r.c = 0;
		
		Thread t1 = new Thread() { // anonymous extension of Thread
			public void run() {
				int oldval = r.c;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// FIXME Auto-generated catch block
					e.printStackTrace();
				}
				int newval = oldval + 4;
				r.c = newval;
			}
		};

		Thread t2 = new Thread() { // anonymous extension of Thread
			public void run() {
				int oldval = r.c;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// FIXME Auto-generated catch block
					e.printStackTrace();
				}
				int newval = oldval + 5;
				r.c = newval;
			}
		};
		
		t1.start();
		t2.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}  // waits for t1 to finish
		try {
			t2.join();
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}  // wait for t2
		System.out.println("Done value of c is " + r.c);
	}

}
