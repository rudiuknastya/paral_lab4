package lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int sz=10;
		Random rand = new Random();
		int [][]matrix = new int[sz][sz];
	    for(int i = 0; i < sz; i++) {
	    	for(int j = 0; j < sz; j++) {
	    		matrix[i][j] = rand.nextInt(200);
	    	}
	    }
	    for(int i = 0; i < sz; i++) {
				for(int l = 0; l < sz; l++) {
					System.out.print(matrix[i][l] + " ");
		    	}
		    	System.out.println();
		    }
	    System.out.println();
	    
	    Object ob = matrix;
	    Object a =3;
	    Object s = "Hi";
	    System.out.println(ob instanceof Integer);
	    System.out.println(a instanceof Integer);
	    System.out.println(s instanceof String);
	    //if(ob.)
		/*int q=4;
		double s = (double)sz/(double)q;
		int k = sz - (int)((double)(q-1)*s);
		int j = 0;	
		List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
		//Thread threads[]  = new Thread[q];
		//Future future = new Future(new Worker(matrix, sz, j, (int)(i*s+k)));
		ExecutorService executor = Executors.newFixedThreadPool(q);
        for (int i = 0; i < q; i++) {
        	Worker worker = new Worker(matrix, sz, j, (int)(i*s+k));
        	futures.add(executor.submit(worker));
          }
		while(true) {
			if(!futures.get(0).isDone() && !futures.get(1).isDone() && !futures.get(2).isDone() && !futures.get(3).isDone()) {
				System.out.println("Not yet");
				
			}
			if(futures.get(0).isDone() && futures.get(1).isDone() && futures.get(2).isDone() && futures.get(3).isDone()) {
				System.out.println("Done");
				//shut down executor service
				 for(int i = 0; i < sz; i++) {
     				for(int l = 0; l < sz; l++) {
     					System.out.print(matrix[i][l] + " ");
     		    	}
     		    	System.out.println();
     		    }
				executor.shutdown();
				break;
			}
			
		}*/
		/*int sz=1000;
		Random rand = new Random();
		int [][]matrix = new int[sz][sz];
	    for(int i = 0; i < sz; i++) {
	    	for(int j = 0; j < sz; j++) {
	    		matrix[i][j] = rand.nextInt(200);
	    	}
	    }
		//System.out.print(matrix.getClass());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sz; i++) {
		    sb.append("");
		    for (int j = 0; j < sz; j++) {
		        sb.append(matrix[i][j] + " ");
		    }
		    sb.append("\n");
		}
		String s=sb.toString();
		System.out.println(s);*/
		
		/*String[] numbers = s.split(" ");
		//int m[][]=new int[sz][sz];
		for(String i: numbers) {
			System.out.println(i);
		}
		System.out.println(numbers.length);
		System.out.println(numbers[15]);*/
		/*int k = 0;
		for(int i = 0; i < sz; i++) {
			for(int l = 0; l < sz; l++) {
				if(numbers[k].equals("\n") == false) {
					m[i][l] =Integer.parseInt(numbers[k]);
					//k++;
				}
				k++;
	    	}
	    }
		for(int i = 0; i < sz; i++) {
			for(int l = 0; l < sz; l++) {
				System.out.print(m[i][l] + " ");
	    	}
	    	System.out.println();
	    }*/
		//System.out.println(s[0]);
		/*boo
		 * lean isNumber = false;
		String s ="1 2";
		int i=0;
		while(i<3) {
		i++;
		try {
		int in=Integer.parseInt(s); 
		isNumber = true;
		System.out.println("Accept number");
		} catch(NumberFormatException e) {
		}
		if(isNumber) {
		continue;
		}
		System.out.println(i);
		//i++;
		}*/
	}
	}

