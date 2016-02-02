package lecture4s1;

public class Cat extends Animal {

	@Override
	public void run() {
		for (int i=0; i < 10; ++i) {
			meow();
		}
		
	}

	@Override
	public String getName() {
		return "Claire the cat";
	}
	
	public void meow() {
		act("meow");
	}
	

}
