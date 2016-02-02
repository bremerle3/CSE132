package lecture3s1;

public class Demo {

	private String name;
	public Demo(String name) {
		this.name = name;
	}

	public synchronized void update(Demo other) {
		System.out.println("About to update " + name);
		other.inquire();
		System.out.println("Updated " + name);
	}

	public synchronized void inquire() {
		System.out.println("You are inquiring about " + name);
	}

	public static void main(String[] args) {
		Demo alice = new Demo("alice");
		Demo bob = new Demo("bob");

		Thread t1 = new Thread() {
			public void run() {
				alice.update(bob);
			}
		};
		t1.start();
		Thread t2 = new Thread() {
			public void run() {
				bob.update(alice);
			}
		};
		t2.start();
	}

}
