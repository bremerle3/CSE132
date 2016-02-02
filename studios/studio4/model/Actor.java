package studio4.model;

abstract public class Actor extends StatusChanger {

	protected final String name;
	
	public Actor(String name, int maxTime) {
		super(maxTime);
		this.name = name;
	}

	public abstract void perform();
	
	public String getName() {
		return name;
	}
	
}