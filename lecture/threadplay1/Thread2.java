package threadplay1;

public class Thread2 extends Thread { // need to say that

	
	@Override
	public void run() { // this is what happens concurrently when it's time
		System.out.println("Thread 2 feels drowsy");
		System.out.println("Therad 2 About to sleep for 3 seconds");
		//  sleep
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread 2 woke up");
		
	}
}
