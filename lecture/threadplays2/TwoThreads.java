package threadplays2;

public class TwoThreads {
	
	public static void main(String[] args) {
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();
		
//		// when we say "run" on a thread, it just runs sequentially
//		t1.run(); // run the first thread
		t1.start();  // this runs t1 in a separate thread, so 
		             // it runs concurrently with this thread
		t2.run(); // run the second thread
		System.out.println("Done starting the threads");
	}

}
