package lecture3s2;

public class HasDeadlock {

	private String name;

	public HasDeadlock(String name) {
		this.name = name;
	}

	public synchronized void update(HasDeadlock other) {
		sleep(1000);
		other.inquire();
		System.out.println("You are now updating " + this.name);
	}

	public synchronized void inquire() {
		System.out.println("You are inquring about " + this.name);
		sleep(1000);
	}

	public static void main(String[] args) {
		HasDeadlock bob = new HasDeadlock("Bob");
		HasDeadlock alice = new HasDeadlock("Alice");
		Thread t1 = new Thread() {
			public void run() {
				bob.update(alice);
			}
		};
		t1.start();
		Thread t2 = new Thread() {
			public void run() {
				alice.update(bob);
			}
		};
		t2.start();

	}

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
	}

}
