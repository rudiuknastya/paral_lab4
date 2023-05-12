package lab4;

public class CalculationThread extends Thread{

	private int[][] matrix;
	private int size;
	private int start;
	private int end;
	public CalculationThread (int[][] matrix, int size, int start, int end) {
		this.matrix = matrix;
		this.size = size;
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run() {
		int min, newJ, temp;
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
		
	}
}
