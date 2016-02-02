package madeoff;

/**
 * This is the Account class you saw in CSE 131 with
 * one small change.  There is a <I>sleep</I> call in the middle
 * of the methods to expand the amount of time the method takes when
 * it changes an account balance.
 * 
 * In a correct implementation, the sleep calls would not change
 * the effect the method calls have an accounts and their balances:
 * Money would be transferred between accounts with no loss of funds.
 * 
 * For an incorrect implementation, the sleep calls serve to show
 * race conditions more frequently and reliably.  Even without the sleep
 * calls, an incorrect implementation will fail at some point, but it's harder
 * to cause this to happen while we're looking.
 *
 */

public class Account {
	// instance variables:
	private String owner;
	private int balance;

	// constructor:
	public Account(String owner, int initialBalance) {
		// initialize the instance variables:
		balance = initialBalance;
		this.owner = owner;
	}

	public int getBalance() {
		return balance;
	}

	/**
	 * Do not change this method EXCEPT to add or take away the <I>synchronized</I> keyword
	 * to the method declaration.
	 * @param amount
	 */
	public synchronized void deposit(int amount) {
		if (amount <= 0)
			throw new IllegalArgumentException("amount must be positive");
		int newBal = balance + amount;
		sleep();
		balance = newBal;
	}

	/**
	 * Do not change this method EXCEPT to add or take away the <I>synchronized</I> keyword
	 * from the method declaration.
	 * @param amount
	 * @return
	 */
	public synchronized boolean withdraw(int amount) {
		if (amount <= 0)
			throw new IllegalArgumentException("amount must be positive");
		if (amount <= balance) {
			int newBal = balance - amount;
			sleep();
			balance = newBal;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Do not change this method EXCEPT to add or take away the <I>synchronzied</I> keyword
	 * from the method declaration.
	 * @param amount
	 * @param dest
	 * @return
	 */
	public boolean transfer(int amount, Account dest) {
		if (withdraw(amount)) {
			dest.deposit(amount);
			return true;
		} else
			return false;
	}
	
	public String toString() {
		return owner + " with balance " + balance;
	}
	
	/**
	 * Sleep for a little bit of time
	 */
	public static void sleep() {
		try {
			Thread.sleep(5);	// 5 milliseconds
		}
		catch (Throwable t) {
			//
			// This should never happen
			//
			throw new Error("This should never happen " + t);

		}
	}

}