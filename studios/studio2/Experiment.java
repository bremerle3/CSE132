package studio2;
import java.io.*;

public class Experiment implements Runnable {

	private DataOutputStream out;

	/**
	 * Sets up the experiment by opening the file
	 * @param fname Filename to be written
	 */
	public Experiment(String fname) {
		try {
			File file = new File(fname);
			out = new DataOutputStream(new FileOutputStream(file));
		}
		//
		// Translate the file I/O error into an
		//   unexpected exception
		//
		catch (Throwable t) {
			throw new Error("Error opening file: "+t);
		}

	}

	/**
	 * Run the experiment
	 */
	public void run() {
		try {
			// 
			// Experiment by writing out different
			//  primitive types below
			//
			out.writeByte(32);
		}
		catch (Throwable t) {
			throw new Error("Error writing file: "+t);
		}
	}

	public OutputStream getOutputStream() {
		return out;
	}



	public static void main(String[] args) {

		Experiment w = new Experiment("datafiles/studio2/exper.bin");
		w.run();
		//
		// We next close the file to make
		//   sure everything is written to it stably
		//
		try {
			w.getOutputStream().close();
		} catch (IOException e) {
			System.out.println("Close issue: " + e);
		}	
		//
		// Let's see what we wrote
		//
		Dumpster d = new Dumpster("datafiles/studio2/exper.bin");
		while (!d.isDone()) {
			d.dumpNextByte();
		}
	}


}
