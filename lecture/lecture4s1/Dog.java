package lecture4s1;

public class Dog extends Animal {
	
	private int age;
	
	public Dog() {
		this.age = 0;
	}

	@Override
	public void run() {
		while (true) {
			double r = Math.random();
			if (r < 0.33) {
				bark();
			}
			else if (r < 0.66) {
				snooze();
			}
			else {
				// some third thing
				age();
			}
		}
		
	}

	@Override
	public String getName() {
		return "Dan the dog";
	}
	
	public void bark() {
		act("bark");
	}
	
	public void snooze() {
		act("snooze");
	}
	
	public void age() {
		int oldAge = this.age;
		int newAge = this.age + 1;
		this.getPCS().firePropertyChange("age", oldAge, newAge);
		this.age = newAge;
	}
	
	

}
