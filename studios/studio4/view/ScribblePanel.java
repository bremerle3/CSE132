package studio4.view;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;


public class ScribblePanel extends JPanel {
	
	private Set<Scribbler> scribblers;
	
	public ScribblePanel() {
		scribblers = new HashSet<Scribbler>();
	}
	
	public ScribblePanel addScribbler(Scribbler s) {
		scribblers.add(s);
		return this;
	}
	
	/**
	 * This is called whenever we resize the panel, or ask it
	 * to repaint itself, or hide or uncover part of the panel
	 * with another window, etc.
	 */
	public void paintComponent(Graphics g) {
		//
		// Paint the background -- this call is necessary!
		//
		super.paintComponent(g);
		//
		// Give each scribbler a chance to draw on
		//   this panel
		//
		for (Scribbler s : scribblers) {
			s.draw(g);
		}
	}

}
