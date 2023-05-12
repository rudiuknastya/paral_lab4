package lab4;

import java.util.concurrent.Callable;

public class Worker implements Callable<Integer> {

	private int[][] matrix;
	private int size;
	private int start;
	private int end;
	public Worker (int[][] matrix, int size, int start, int end) {
		this.matrix = matrix;
		this.size = size;
		this.start = start;
		this.end = end;
	}
	public Integer call() {
		int min =0, newJ, temp;
		int pos = size - start -1;
	      for(int i=start; i<end; i++) {
	    	  min = matrix[i][0];
	    	  newJ = 0;
	    	  for(int j=0; j<size-1; j++) {
	    		  if(min > matrix[i][j+1]){
	    			  min = matrix[i][j+1];
	    			  newJ = j+1;
	    		  }
	    	  }
	    	  temp = matrix[i][pos];
	    	  matrix[i][pos] = min;
	    	  matrix[i][newJ] = temp;
	    	  pos--;
	      }
	      try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return min;
    }
}
