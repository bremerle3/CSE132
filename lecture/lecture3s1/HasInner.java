package lecture3s1;

public class HasInner {
	private int mysecret = 132;
	private int mylocal;
	
	public void foo() {
		
		mylocal = 42;
		
		// mylocal = mylocal + 0;
		
		Thread t1 = new Thread() {
			public void run() {
				System.out.println(" Your secret is " + mysecret);
				System.out.println("your local val was "  + mylocal);
				mylocal = mylocal + 0;
				mysecret = mysecret + 133;
			}
		};
		
		
		
	}

}
