package lecture4s1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// class that is interested in watching objects PCS
//   and it will remark or log about that
public class Logger {
	
	public void addInterested(PropertyChangeSupport pcs) {
		// add a logging method as a listener to that pcs
		
		pcs.addPropertyChangeListener(
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						System.out.println("   Logger saw " + evt.getPropertyName());
						
					}
					
				}
				);
	}
	
	public void addAging(PropertyChangeSupport pcs) {
		pcs.addPropertyChangeListener("age",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						System.out.println("    Happy birthday!");
						System.out.println("    You were " + evt.getOldValue());
						System.out.println("    Now you are " + evt.getNewValue());
						
					}
			
		}
				);
	}

}
