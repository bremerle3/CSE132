package lecture4s2;

public class Dog extends Animal {
	
	private int age;
	
	public Dog() {
		this.age = 0;
	}

	@Override
	public void run() {
		while(true) { // this dog lives forever
			double r = Math.random();  // 0 <= r < 1
			if (r < 0.33) {
				bark();
			}
			else if (r < 0.66) {
				snooze();
			}
			else {
				age();
			}
		
		}
		
	}

	@Override
	public String getName() {
		return "Dan the Dog";
	}
	
	public void bark() {
		this.act("bark");
	}
	
	public void snooze() {
		this.act("snooze");
	}
	
	public void age() {
		// want to fire a message, but I also want to convey the old
		//   age and the new age of this dog
		int newAge = this.age + 1;
		//
		// this kind of PCS message both says what we are doing
		//   (aging)
		// but also passes info about the old and new values
		//
		this.getPCS().firePropertyChange("age", this.age, newAge);
		this.age = newAge;
	}

}
