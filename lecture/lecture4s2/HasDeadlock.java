package lecture4s2;

public class HasDeadlock {

	public static void main(String[] args) {
		Object thing1 = new Object();
		Object thing2 = new Object();

		Thread t1 = new Thread() {
			public void run() {
				System.out.println("Thread 1 starts");
				snooze(50);
				synchronized(thing2) {
					System.out.println("Thread 1 got lock on thing2");
					snooze(50);
					synchronized(thing1) {
						System.out.println("Thread 1 got lock on thing1");
						snooze(50);

					}
				}
				System.out.println("Thread 1 finishes");

			}

		};

		Thread t2 = new Thread() {
			public void run() {
				System.out.println("Thread 2 starts");
				snooze(50);
				synchronized(thing1) {
					System.out.println("Thread 2 got lock on thing1");
					snooze(50);
					synchronized(thing2) {
						System.out.println("Thread 2 got lock on thing2");
						snooze(50);

					}
				}

				System.out.println("Thread 2 finishes");

			}
		};

		t1.start();
		t2.start();

	}

	private static void snooze(int millis) {
		try {
			Thread.sleep(millis);
		} catch(Throwable t) {

		}
	}

}
