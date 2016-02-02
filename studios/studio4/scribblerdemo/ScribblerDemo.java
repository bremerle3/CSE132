package studio4.scribblerdemo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JFrame;

import studio4.view.LockViz;
import studio4.view.PhilViz;
import studio4.view.ScribblePanel;
import studio4.view.Scribbler;


public class ScribblerDemo {

	final ScribblePanel panel1, panel2;

	public ScribblerDemo() {
		JFrame frame = new JFrame("Scribblers Demo");
		frame.setSize(800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel1 = new ScribblePanel();
		panel2 = new ScribblePanel();
		frame.setLayout(new GridLayout(2,1));	// 2x1 layout:  2 rows, 1 column
		frame.add(panel1);						// will go on top since it's added first
		frame.add(panel2);				     	// will go on bottom
		frame.setVisible(true);

		panel1.addScribbler(
				new Scribbler() {

					@Override
					public void draw(Graphics g) {
						g.fillRect(0, 0, 10, 20);
						g.drawString("Hello", 100, 100);
						g.setColor(Color.RED);
						g.drawString("Long string near the top", 0, 10);
						g.setColor(Color.BLUE);
						g.fillOval(30, 0, 30, 30);

					}

				}
		);
		
		panel2.addScribbler(
				new Scribbler() {
					public void draw(Graphics g) {
						int width = panel2.getWidth();
						int height = panel2.getHeight();
						for (int i=0; i < 10; ++i) {
							for (int j=0; j < 10; ++j) {
								int x = i*width/10;
								int y = j*height/10;
								int w = width/10;
								int h = height/10;
								g.setColor(Color.getHSBColor((i*j)%256/256.0f, 1.0f, 1.0f));
								g.drawOval(x, y, w, h);
								g.setColor(Color.BLACK);
								g.drawString("("+i+","+j+")", x, y);
							}
						}
					}
				}
		);

	}



	public static void main(String[] args) {
		new ScribblerDemo();
	}


}
