package studio4.view;

import java.awt.Color;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import javax.swing.JPanel;

import studio4.model.Phil;


public class PhilViz extends RectViz {

	//
	// Plan for a total number of philosophers, and establish the
	//    viz for philosopher i
	///
	public PhilViz(Phil phil, JPanel outer, int i, int total) {
		super(phil.getPCS(), 1.0*i/total, 0.10, 0.9/total, 0.9/total, outer, 
				new String[] { "studying", "hungry", "eating", "full" },
				new Color[] { Color.BLUE, Color.RED, Color.GREEN, Color.CYAN }, 
				Color.BLACK);
	}
	

}
