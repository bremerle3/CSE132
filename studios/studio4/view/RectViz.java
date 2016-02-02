package studio4.view;

import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.util.HashMap;
import java.util.Map;


/** This class can draw a rectangle within a specified area of an outer {@link JPanel}.
 * The rectagle changes color based on the signals and colors provided. */
public class RectViz implements Scribbler {

	final private JPanel outer;
	private Color color;
	private String id;
	final private double dx, dy, dwidth, dheight;

	/** Constructor with parameters as follows
	 * @param pcs This class must register as a {@link PropertyChangeListener} for each signal provided 
	 * @param dx fraction of outer.getWidth() for x coordinate
	 * @param dy fraction of outer.getHeight() for y coordinate
	 * @param dwidth fraction of outer.getWidth() for width
	 * @param dheight fraction of outer.getHeight() for height
	 * @param signals array of Strings, names of signals to be received
	 * @param colors array of Colors, one for each signal, the color to set the rectagle
	 * @param initColor the starting Color for the rectangle
	 */
	public RectViz(PropertyChangeSupport pcs, 
			double dx, double dy, 
			double dwidth, double dheight, 
			JPanel outer, 
			String[] signals, Color[] colors, 
			Color initColor) {
		this(pcs, dx, dy, dwidth, dheight, outer, genMap(signals,colors), initColor);
	}

	private static Map<String,Color> genMap(String[] signals, Color[] colors) {
		if (signals.length != colors.length) 
			throw new IllegalArgumentException("signals and colors must have same length");
		Map<String,Color> ans = new HashMap<String,Color>();
		for (int i=0; i < signals.length; ++i) {
			ans.put(signals[i], colors[i]);
		}
		return ans;
	}

	public RectViz(PropertyChangeSupport pcs, 
			double dx, double dy, 
			double dwidth, double dheight, 
			JPanel outer, 
			Map<String,Color> map, 
			Color initColor) {

		this.id = "";
		this.color = initColor;
		this.dx = dx;
		this.dy = dy;
		this.dwidth = dwidth;
		this.dheight = dheight;
		this.outer = outer;
		//
		// For each String (event name), register the color we should change
		//   when that event happens
		//
		for (String s : map.keySet()) {
			register(pcs, s, map.get(s));
		}   
	}

	/**
	 * Handy method to avoid int casts all over the place
	 * @param frac
	 * @param n
	 * @return product of frac and n, int-style
	 */
	private static int mpy(double frac, double n) {
		return (int)(frac * n);
	}

	/** 
	 * Set the instance variable for the color and call outer.repaint().  That in turn will call
	 * draw in this class, because paintComponent is overriden in the outer {@link JPanel}. */
	protected void setColor(Color c) {
		color = c;
		outer.repaint();
	}

	/**
	 * Set the id String for this visualization.
	 * Calls outer.repaint() to force the change to show itself.
	 * @param s
	 */
	protected void setText(String s) {
		id = s;
		outer.repaint();
	}

	/** Can be overriden to change the way the color is chosen */
	protected Color getColor() { return color; }

	/** A very useful helper method.  
	 * Using the pcs, add a ProeprtyChangeListener that 
	 * simply sets the color and text of the view.
	 */
	protected final void register(PropertyChangeSupport pcs, final String s, final Color c) {
		PropertyChangeListener react = new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent e) {
				setColor(c);
				setText(e.getSource().toString()+" "+s);
			}
			
		};
		
		pcs.addPropertyChangeListener(s, react);
	}

	/**
	 * We end up here by 
	 * outer.repaint() is called for resize window or when we need to refresh
	 *    --> outer.paintComponent(Graphics) is called to repaint the outer JPanel
	 *    --> draw(Graphics)  is called because we override paintComponent in {@link ScribblePanel}
	 *  
	 * Given the supplied {@link Graphics} object
	 * <UL>
	 * <LI> Issue <code>g</code> a setColor command for the appropriate color,
	 * <LI> Issue a fillRect command to fill a rectangle of the appropriate size
	 * </UL>
	 * Do not issue a repaint on the outer panel!  That would cause this
	 * code to run indefinitely.
	 **/
	public void draw(Graphics g) {
		int x = mpy(dx, outer.getWidth());
		int y = mpy(dy, outer.getHeight());
		int width = mpy(dwidth, outer.getWidth());
		int height = mpy(dheight, outer.getHeight());
		//
		// our current color
		//
		g.setColor(getColor());
		g.fillRect(x, y, width, height);
		//
		// and our message
		//
		g.setColor(Color.BLACK);
		g.setFont(new Font("Sans Serif", Font.BOLD, 12));
		g.drawString(id, x, y);
	}

}
