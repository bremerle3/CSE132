package studio4.misc;

public class Sleeper {
	
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		}
		catch(Exception e) {
			throw new Error("Cannot happen "+e);
		}
	}

}
