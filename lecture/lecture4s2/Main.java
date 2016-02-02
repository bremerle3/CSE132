package lecture4s2;

public class Main {

	public static void main(String[] args) {
		Dog dan = new Dog();
		Logger logger = new Logger();
		logger.addInterest(dan.getPCS());
		logger.addAgingInterest(dan.getPCS());
		// when these are not in separate threads, dan the dog gets
		//  all the attention and we never get past dan.run()
		//  because it is an infinite loop
//		dan.run();
//		Cat claire = new Cat();
//		logger.addInterest(claire.getPCS());
//		claire.run();
		new Thread(dan).start();
		Cat claire = new Cat();
		logger.addInterest(claire.getPCS());
		claire.run();


	}

}
