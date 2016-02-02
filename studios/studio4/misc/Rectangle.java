package studio4.misc;

/**
 * Used in lecture, not in studio
 * An example of a Rectangle whose quality with another oen
 *   is based on having exactly the same width and height.
 *   
 * The practice quiz changed this to test whether the two
 *    Rectangles have the same perimeter.
 *
 */
public class Rectangle {
	private int x, y, width, height;
	java.awt.Color c;
	public Rectangle(int x, int y, int width, int height) {
		this.x     = x;     this.y      = y; 
		this.width = width; this.height = height;
	}
	
	//
	// What is thread-unsafe about the following method?
	// How do you fix that in this class?
	// Should you fix it in this class?
	// What are other solutions to the thread unsafety?
	//
	public synchronized void setDimens(int width, int height) {
		this.width  = width;
		this.height = height;
	}
	
	// What about the following methods?  synch or not synch?
	
	public synchronized int getWidth() {
		return width;
	}
	
	public synchronized int getHeight() {
		return height;
	}

	/**
	 * if they have the same width and height
	 */
	public boolean equals(Object o) {
		// eclipse would generate some contractual stuff here
		if (o == null) return false;
		if (this==o) return true;
		if (this.getClass() != o.getClass()) return false;
		// the money
		
		Rectangle other = (Rectangle)o;
		// can I get to other's height and width
		return (this.width == other.width &&
				this.height == other.height);
	}

	public int hashCode() {
		return 31 + 2*this.width + this.height;
	}
}
