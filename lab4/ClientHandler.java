package lab4;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class ClientHandler extends Thread {

	//boolean isNumber = false;
	final ObjectInputStream  dis; 
    final ObjectOutputStream dos; 
    final Socket s; 
    int [][] matrix ;
    int size;
    Object recieve;
    ExecutorService executor;
    List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
    public ClientHandler(Socket s, ObjectInputStream  dis, ObjectOutputStream dos)  
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
    } 
	@Override
    public void run()  
    { 
        String received =""; 
        String toreturn; 
        while (true)  
        { 
            try { 
                // Ask user what he wants 
                dos.writeObject("Type Send \n"+ "Type Start to start calculations.\n"+ "Type Result to get results.\n"+
                            "Type Exit to terminate connection."); 
                  
                // receive the answer from client 
                try {
                    recieve = dis.readObject();
                    //System.out.println(objects);
                } catch (ClassNotFoundException e) {             
                    e.printStackTrace();
                }
                if(recieve instanceof String) {
                	received = (String)recieve;
                	System.out.println("Received String  " + received);
                }else {
                	matrix = (int[][])recieve;
                	System.out.println("Received matrix  " + matrix.length);
                	dos.writeObject("Received matrix with size " + matrix.length);
                	size=matrix.length;
                	/*for(int i = 0; i < size; i++) {
        				for(int l = 0; l < size; l++) {
        					System.out.print(matrix[i][l] + " ");
        		    	}
        		    	System.out.println();
        		    }
        	    System.out.println();*/
        	    received =""; 

                }
                //System.out.println("Received: " + received);
                //int in =0;
                
                if(received.equals("Exit")) 
                {  
                    System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
                //if(isNumber) {
            		//continue;
            		//}
                if(received.equals("Start")) {
                	//int sz = size;
            		/*Random rand = new Random();
            		matrix = new int[size][size];
            	    for(int i = 0; i < size; i++) {
            	    	for(int j = 0; j < size; j++) {
            	    		matrix[i][j] = rand.nextInt(200);
            	    	}
            	    }*/
            	    /*for(int i = 0; i < size; i++) {
        				for(int l = 0; l < size; l++) {
        					System.out.print(matrix[i][l] + " ");
        		    	}
        		    	System.out.println();
        		    }*/
            	    //System.out.println();
            	    int q=4;
            	    double s = (double)size/(double)q;
            	    //CalculationThread threads[]  = new CalculationThread[q];
            		int k = size - (int)((double)(q-1)*s);
            		int j = 0;	
            		executor = Executors.newFixedThreadPool(q);
                    for (int i = 0; i < q; i++) {
                    	Worker worker = new Worker(matrix, size, j, (int)(i*s+k));
                    	futures.add(executor.submit(worker));
                      }
                    dos.writeObject("Start calculating");
            		/*for(int i=0; i<q; i++) {
            		    threads[i] = new CalculationThread(matrix, size, j, (int)(i*s+k));
            		  	j =  (int)((double)i*s+k);
            		 }
            		 for(CalculationThread thread: threads) {
            		  	thread.start();
            		 }
            		 for(CalculationThread thread: threads) {
            		  	thread.join();
            		 }
            		 for(int i = 0; i < size; i++) {
            				for(int l = 0; l < size; l++) {
            					System.out.print(matrix[i][l] + " ");
            		    	}
            		    	System.out.println();
            		    }*/
            		// break;
            		 /*StringBuilder sb = new StringBuilder();
            			for (int i = 0; i < size; i++) {
            			    sb.append("");
            			    for (int n = 0; n < size; n++) {
            			        sb.append(matrix[i][n] + " ");
            			    }
            			    sb.append("\n");
            			}
            			toreturn = sb.toString();
            		 dos.writeUTF(toreturn);*/
                }
                if(received.equals("Result")) {
                	if(!futures.get(0).isDone() && !futures.get(1).isDone() && !futures.get(2).isDone() && !futures.get(3).isDone()) {
                		dos.writeObject("Not yet");
        				
        			}
        			if(futures.get(0).isDone() && futures.get(1).isDone() && futures.get(2).isDone() && futures.get(3).isDone()) {
        				System.out.println("Done");
        				//shut down executor service
        				/*for(int i = 0; i < size; i++) {
        					for(int l = 0; l < size; l++) {
        						System.out.print(matrix[i][l] + " ");
        			    	}
        			    	System.out.println();
        			    }*/
            			dos.writeObject("Done");
        				executor.shutdown();
        				//break;
        			}
                }
                
            } catch (IOException e) { 
                e.printStackTrace(); 
                break;
            } /*catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			} */ 
        } 
          
        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace();
        } 
    } 
}
