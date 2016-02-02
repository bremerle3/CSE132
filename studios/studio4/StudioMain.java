package studio4;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JFrame;

import studio4.model.Chop;
import studio4.model.Phil;
import studio4.view.LockViz;
import studio4.view.PhilViz;
import studio4.view.ScribblePanel;


public class StudioMain {

	private Phil[] scholars;
	private Chop[] chops;
	ScribblePanel scholarPanel;

	public StudioMain(int numScholars) {
		scholars = new Phil[numScholars];
		chops    = new Chop[numScholars];
		String[] names = new String[numScholars];

		JFrame frame = new JFrame("Dining Philosophers");
		frame.setSize(800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		scholarPanel = new ScribblePanel();
		ScribblePanel locksPanel = new ScribblePanel();

		//
		// Split the frame into two parts:  scholars on top, locks on bottom
		//
		frame.setLayout(new GridLayout(2,1));	// 2x1 layout:  2 rows, 1 column
		frame.add(scholarPanel);				// will go on top since it's added first
		frame.add(locksPanel);					// will go on bottom

		//
		// Make the requisite number of chopsticks, one for each philosopher
		//
		for (int i=0; i < numScholars; ++i) {
			chops[i] = new Chop(i);
			names[i] = ""+(char)('A' + i);
		}

		// Make each Philosopher, and supply the chopsticks he/she might need
		//
		for (int i=0; i < numScholars; ++i) {
			scholars[i]= new Phil(names[i], chops[i], chops[(i+1)%numScholars]);
			PhilViz pv = new PhilViz(scholars[i], scholarPanel, i, numScholars);
			scholarPanel.addScribbler(pv);
			LockViz lv1 = new LockViz(scholars[i].l1, locksPanel, 2*i, 2*numScholars);
			LockViz lv2 = new LockViz(scholars[i].l2, locksPanel, 2*i+1, 2*numScholars);
			locksPanel.addScribbler(lv1);
			locksPanel.addScribbler(lv2);
		}
		frame.setVisible(true);

	}


	/**
	 * Uses the setup to run the Dining Philosophers problem
	 */
	public void runAll() {
		Random r = new Random();
		// Let the feast begin
		//
		for (int i=0; i < 10; ++i) {
			final int randomP = r.nextInt(scholars.length); 
			// Pick a random Philosopher [0..scholars.length)
			// use r.nextInt(int) to pick the one

			//	Make a new Thread for the philosopher to act, and .run or .start it
			new Thread(
					new Runnable() {
						public void run() {
							scholars[randomP].perform();							
						}
					}
			).run();
		}

	}


	public static void main(String[] args) {
		StudioMain main = new StudioMain(2);
		main.runAll();
	}


}
