package lecture4s1;

public class Main {

	public static void main(String[] args) {
		Dog dan = new Dog();
		Cat claire = new Cat();
		Neighbor grump = new Neighbor(dan.getPCS());
		grump.addNoiseMaker("meow", claire.getPCS());
		
		Logger logger = new Logger();
		logger.addInterested(dan.getPCS());
		logger.addInterested(claire.getPCS());
		logger.addAging(dan.getPCS());
		// dan.run();
		// let's make dan run in a separte Thread
		
		new Thread(dan).start();
		
		// Now, we get to here and can run the cat too
		
		// for now, dan.run() is an infinite loop
		// so we never get past this... unless...
		
		claire.run();

	}

}
