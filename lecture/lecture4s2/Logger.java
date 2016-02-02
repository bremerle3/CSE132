package lecture4s2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Logger {

	public Logger() {

	}

	// interested in all activity on the pcs
	public void addInterest(PropertyChangeSupport pcs) {
		pcs.addPropertyChangeListener(
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						System.out.println("    Logger sees " + evt.getPropertyName());

					}

				}
				);

	}

	public void addAgingInterest(PropertyChangeSupport pcs) {
		pcs.addPropertyChangeListener("Age",
				new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("   Happy birthday!");
				System.out.println("   You were " + evt.getOldValue() + " years old");
				System.out.println("   and now you are " + evt.getNewValue());

			}
		}
				);
	}
}
