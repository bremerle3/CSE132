package studio4.view;

import java.awt.Graphics;

/**
 * Take in a {@link Graphics} and draw on it
 *
 */
public interface Scribbler {

	/**
	 * Draw something on the supplied {@link Graphics}.
	 * Do NOT call repaint or you'll get recursion without
	 * end.
	 * @param g The thing on which to draw
	 */
	public void draw(Graphics g);
}
