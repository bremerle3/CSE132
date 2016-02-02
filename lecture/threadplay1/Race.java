package threadplay1;

public class Race {

	private int c;

	public static void main(String[] args) {

		Race r = new Race();

		r.c = 0;

		Thread t1 = new Thread() { // anonymous extension
			@Override
			public void run() {
				int oldCVal = r.c;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// FIXME Auto-generated catch block
					e.printStackTrace();
				}
				int newval = oldCVal + 1;
				r.c = newval;
			}
		};

		Thread t2 = new Thread() { // anonymous extension
			@Override
			public void run() {
				int oldCVal = r.c;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// FIXME Auto-generated catch block
					e.printStackTrace();
				}
				int newval = oldCVal + 1;
				r.c = newval;
			}
		};

		t1.start();   // run both in parallel
		t2.run();
		try {
			t1.join();
			t2.join();
		} catch(Throwable t) {
			// life on the edge, do nothing
		}
		
		System.out.println("And the value of c is " + r.c);

	}

}
