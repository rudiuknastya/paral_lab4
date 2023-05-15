package lab4;

import java.util.concurrent.Callable;

public class Worker implements Callable<Long> {

	private int[][] matrix;
	private CalculationThread threads[];
	public Worker (int[][] matrix) {
		this.matrix = matrix;
	}
	public Long call() {
		int size = matrix.length;
		int q=4;
		threads= new CalculationThread[q];
		double s = (double)size/(double)q;
		int k = size - (int)((double)(q-1)*s);
		int j = 0;
		for(int i=0; i<q; i++) {
		    threads[i] = new CalculationThread(matrix, size, j, (int)(i*s+k));
		  	j =  (int)((double)i*s+k);
		 }
		long start = System.nanoTime();
		 for(CalculationThread thread: threads) {
		  	thread.start();
		 }
		 for(CalculationThread thread: threads) {
		  	try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 long finish = System.nanoTime();
		 long timeElapsed = finish - start;
        return timeElapsed / 1000000;
    }
}
