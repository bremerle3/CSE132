package madeoff;

import java.util.Random;

public class Bank {

	private final static int 
	NUMACCOUNTS = 3,		/* How many accounts are involved? 					*/
	INITIALBALANCE=100,		/* The initial amount in each account 				*/
	NUMTRANSACTIONS=1000,   /* How many transfer transactions will we conduct?	*/
	TRANSFERAMOUNT=10;		/* What is the amount of each transfer?				*/

	//
	// A random sequence is used to pick accounts for the transfers
	//
	private Random random;
	private Account[] accounts;
	private Thread[] transactions;

	public Bank() {
		random = new Random();
		accounts = new Account[NUMACCOUNTS];
		transactions = new Thread[NUMTRANSACTIONS];
		//
		// Initialize each account with the appropriate bal
		//
		for (int i=0; i < NUMACCOUNTS; ++i) {
			accounts[i] = new Account("Customer " + i, INITIALBALANCE);

		}
		verifyAssets();
	}

	private void runTransactions() {
		System.out.println("Starting tranactions....");
		verifyAssets();
		for (int i=0; i < NUMTRANSACTIONS; ++i) {
			//
			// Pick two accounts at random. Doesn't matter if they are the same account.
			//
			final Account acct1 = pickRandomAccount();
			final Account acct2 = pickRandomAccount();
			//
			//  Remember the Thread so we can join to it later
			//
			transactions[i] = new Thread(){
				public void run() {
					acct1.transfer(TRANSFERAMOUNT, acct2);
				}
			};
			
			
			
			
			//
			//  Either .run or .start the transaction
			//     .run will run it and wait for it to finish
			//     .start will cause it to run asynchronously
			//
			transactions[i].start();
		}
		System.out.println("All transactions started.");
		System.out.println("Waiting for transactions to finish....");
		//
		// Wait for all activity to cease
		//
		for (int i=0; i<NUMTRANSACTIONS; ++i) {
			try {
				transactions[i].join();
			} catch (InterruptedException e) {
				throw new Error("This should never happen " + e);
			}
		}
		System.out.println("All transactions finished.");
		verifyAssets();
		System.out.println("Done.");

	}

	private void verifyAssets() {
		int total = 0;
		for (Account a : accounts) {
			total += a.getBalance();
		}

		if (total != NUMACCOUNTS * INITIALBALANCE)
			throw new Error("\n   Bad bank balance: " + total + " dollars in bank, expected " + NUMACCOUNTS*INITIALBALANCE
					+ ".  Someone MADEOFF with some $$!");
		System.out.println("Balance of " + (NUMACCOUNTS*INITIALBALANCE) + " verified.");
	}

	/**
	 * Pick a random account
	 * @return an integer in the range [0, NUMACCOUNTS)
	 */
	private Account pickRandomAccount() {
		return accounts[random.nextInt(NUMACCOUNTS)];
	}

	public static void main(String[] args) {
		final Bank bank = new Bank();
		bank.runTransactions();
	}


}
