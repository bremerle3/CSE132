package studio2;

//
// Include Java I/O libraries
//
import java.io.*;

//
// Follow the studio instructions to complete this class
//
public class Dumpster {
	
	public Dumpster(String fname) {
		System.out.println("Dumpster output:");
		
	}
	
	/**
	 * read the next byte from the DataInputStream
	 * and print it out as a byte value
	 */
	public void dumpNextByte() {
		
	}
	
	/**
	 * Are we done with the file?
	 * @return true iff there is no more to be read
	 */
	public boolean isDone() {
		return true; // fix
	}
	
	
	public static void main(String[] args) {
		//
		// Run this on test.txt and test.bin
		//
		Dumpster r = new Dumpster("datafiles/studio2/test.txt");
		while (!r.isDone()) {
			r.dumpNextByte();
		}
	}
	

}
