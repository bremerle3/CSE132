package studio4.pcsdemo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import studio4.model.Actor;



public class PCSDemo {

	public final RandomPerson[] people;

	public PCSDemo() {

		RandomPerson
		//                                    Walk   TV    Nap  Gtr
		rockstar = new RandomPerson("Rocky",  0.40, 0.10, 0.10, 0.40),
		hermit   = new RandomPerson("Hermy",  0.50, 0.00, 0.50, 0.00),
		sleepy   = new RandomPerson("Morty",  0.00, 0.00, 0.90, 0.10);

		people = new RandomPerson[] { rockstar, hermit, sleepy };

		// FIXME A paparazza cares if the rockstar is walking around
		Observer pararazza = new Observer("Photographer");
		pararazza.addSubject(rockstar, "strolling");

		// FIXME The tooth fairy cares when any RandomPerson is sleeping
		Observer toothFairy = new Observer("Tooth Fairy");
		for (RandomPerson p : people) {
			toothFairy.addSubject(p, "snoozing");
		}

		// FIXME A groupy obsesses over the rockstar
		Observer groupy = new Observer("Groupy");
		groupy.addObsession(rockstar);


		// FIXME Dead people should not play the guitar
		//   OK, Grateful Dead can, but not this guy

		sleepy.getPCS().addPropertyChangeListener(
				"playing guitar",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						throw new Error("Dead people don't play guitar well");
						
					}

				}
		);


	}

	/*
	 * Let our random people lead a life of their own
	 */
	public void run() {

		for (RandomPerson p : people) {
			startLife(p);
		}

	}

	private void startLife(final Actor a) {
		System.out.println("Now begins the story of " + a);
		new Thread() {
			public void run() {

				// These actors need to repeat their performances indefinitely
				while (true) {
					a.perform();
				}

			}
		}.start();
	}

	public static void main(String[] args) {

		PCSDemo demo = new PCSDemo();
		demo.run();

	}

}
