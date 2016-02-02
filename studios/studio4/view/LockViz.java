package studio4.view;

import java.awt.Color;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import javax.swing.JPanel;

import studio4.locking.LockPub;


public class LockViz extends RectViz {

	public LockViz(LockPub lock, JPanel outer, int i, int total) {
		super(lock.getPCS(), 1.0*i/total, 0.10, 0.9/total, 0.9/total, outer, 
				new String[] { "avail", "request", "granted" },
				new Color[] { Color.WHITE, Color.ORANGE, Color.GREEN}, 
				Color.BLACK);
	}



}