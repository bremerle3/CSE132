package lecture4s1;

public class Deadlocks {

	public static void main(String[] args) {
		Object thing1 = new Object();
		Object thing2 = new Object();
		
		Thread t1 = new Thread() {
			public void run() {
				report("Thread 1 starts");
				synchronized(thing1) {
					report("Thread 1 got lock on thing1");
					synchronized(thing2) {
						report("Thread 1 got lock on thing2");
						
					}
				}
				
				
				report("Thread 1 finishes");
				
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				report("Thread 2 starts");
				synchronized(thing2) {
					report("Thread 2 got lock on thing2");
					synchronized(thing1) {
						report("Thread 2 got lock on thing1");
						
					}
				}

				
				report("Thread 2 finishes");

				
			}
		};
		
		t1.start();
		t2.start();

	}
	
	private static void report(String message) {
		System.out.println(message);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
	}

}
