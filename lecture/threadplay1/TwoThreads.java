package threadplay1;

public class TwoThreads {

	public static void main(String[] args) {
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();

		//		t1.run();   // runs t1 NOT concurrently because we said .run()
		t1.start(); // this runs in a separate thread, so we can continue
		//		t2.run();   // runs t2 NOT concurrently
		t2.start();

		// if we aren't careful, this line is reached too early,
		//   while the other threads are starting, so we....
		try {
			t1.join();   // join with t1 -->  wait until its run method returns
			t2.join();
		}
		catch (Throwable t)  { // we get here if anything is thrown
			throw new Error("Oops, the impossible happened");
		}
		System.out.println("Over, everybody is up");
	}

}
