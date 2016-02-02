package lecture4s1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Neighbor {

	private PropertyChangeListener complain;

	public Neighbor(PropertyChangeSupport pcs) {

		//
		// the name of the message must agree exactly with
		//   what is published!
		this.complain = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("  Wish that animal would stop making noise!!!");

			}

		};
		pcs.addPropertyChangeListener("bark", 
				complain
				);

	}

	public void addAnotherbarker(PropertyChangeSupport pcs) {
		pcs.addPropertyChangeListener("bark",complain);
	}
	
	public void addNoiseMaker(String noise, PropertyChangeSupport pcs) {
		pcs.addPropertyChangeListener(noise,complain);
	}


}
