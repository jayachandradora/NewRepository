package threaddemo;
//JAVA program to demonstrate execution on Cyclic Barrier

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//It will wait each task to complete for processing final out put.

class Computation1 implements Runnable {
	public static int product = 0;

	public void run() {
		product = 2 * 3;
		try {
			CyclicBarrierTest.newBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}

class Computation2 implements Runnable {
	public static int sum = 0;

	public void run() {
		// check if newBarrier is broken or not
		System.out.println("Is the barrier broken? - " + CyclicBarrierTest.newBarrier.isBroken());
		sum = 10 + 20;
		try {
			CyclicBarrierTest.newBarrier.await(3000, TimeUnit.MILLISECONDS);

			// number of parties waiting at the barrier
			System.out.println("Number of parties waiting at the barrier " + "at this point = "
					+ CyclicBarrierTest.newBarrier.getNumberWaiting());
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}

public class CyclicBarrierTest implements Runnable {

	public static CyclicBarrier newBarrier = new CyclicBarrier(3);

	public static void main(String[] args) {
		
		System.out.println("Ascii " + ((int)"vishal".charAt(0)) %(16));
		
		// parent thread
		CyclicBarrierTest test = new CyclicBarrierTest();
		Thread t1 = new Thread(test);
		t1.start();
	}

	public void run() {
		System.out.println("Number of parties required to trip the barrier = " + newBarrier.getParties());
		System.out.println("Sum of product and sum = " + (Computation1.product + Computation2.sum));

		// objects on which the child thread has to run
		Computation1 comp1 = new Computation1();
		Computation2 comp2 = new Computation2();

		// creation of child thread
		Thread t1 = new Thread(comp1);
		Thread t2 = new Thread(comp2);
		
		// moving child thread to runnable state
		t1.start();
		t2.start();

		try {
			CyclicBarrierTest.newBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

		// barrier breaks as the number of thread waiting for the barrier
		// at this point = 3
		System.out.println("Sum of product and sum = " + (Computation1.product + Computation2.sum));

		// Resetting the newBarrier
		newBarrier.reset();
		System.out.println("Barrier reset successful");
	}
}
