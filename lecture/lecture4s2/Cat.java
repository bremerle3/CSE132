package lecture4s2;

public class Cat extends Animal {

	@Override
	public void run() {
		for (int i=0; i < 10; ++i) {
			meow();
			snooze();
		}
		
	}

	@Override
	public String getName() {
		return "Claire the cat";
	}
	
	public void meow() {
		act("meow");
	}
	
	public void snooze() {
		act("snooze");
	}

}
