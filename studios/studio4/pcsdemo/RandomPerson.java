package studio4.pcsdemo;

import studio4.model.Actor;

/**
 * A random person, who may be
 *   taking a walk, watching TV, taking a nap, or playing guitar
 * @author cytron
 *
 */
public class RandomPerson extends Actor {

	private final double pWalk, pTV, pNap, pGuitar;

	/**
	 * 
	 * @param name
	 * @param pWalk
	 * @param pTV
	 * @param pNap
	 * @param pGuitar
	 */
	public RandomPerson(String name, double pWalk, double pTV, double pNap, double pGuitar) {
		super(name, 7);
		this.pWalk = pWalk;
		this.pTV = pTV;
		this.pNap = pNap;
		this.pGuitar = pGuitar;
	}
	
	/**
	 * The status names below are important, as they are used to connect
	 * this subject with any observer who may care about the given status.
	 * 
	 * In other words, "strolling" is the event name, not "taking a walk"
	 */

	@Override
	public void perform() {
		double r = Math.random();
		if (r < pWalk) {
			status("strolling");
		}
		else if (r < pWalk+pTV) {
			status("watching TV");
		}
		else if (r < pWalk+pTV+pNap){
			status("snoozing");
		}
		else {
			status("playing guitar");
		}
	}

	public String toString() {
		return name;
	}
}
