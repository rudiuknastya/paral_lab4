package lab4;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class ClientHandler extends Thread {

	//boolean isNumber = false;
	final ObjectInputStream  inpt; 
    final ObjectOutputStream outpt; 
    final Socket s; 
    private int [][] matrix ;
    private Object recieve;
    private ExecutorService executor;
    Future<Long> future;
    Worker worker;
    Object res ;
    public ClientHandler(Socket s, ObjectInputStream  inpt, ObjectOutputStream outpt)  
    { 
        this.s = s; 
        this.inpt = inpt; 
        this.outpt = outpt; 
    } 
	@Override
    public void run()  
    { 
        String received =""; 
        while (true)  
        { 
            try { 
            	outpt.writeObject("Type Send to send matrix\n"+ "Type Start to start calculations.\n"+ "Type Result to get results.\n"+
                            "Type Exit to terminate connection."); 
                try {
                    recieve = inpt.readObject();
                    received = (String)recieve;
                    //System.out.println(objects);
                } catch (ClassNotFoundException e) {             
                    e.printStackTrace();
                }
                System.out.println("Received String  " + received);
                
                if(received.equals("Exit")) 
                {  
                    System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
                else if(received.equals("Send")){
                    outpt.writeObject("Enter matrix size");
                    try {
                    res = inpt.readObject();
                    }catch (ClassNotFoundException e) {             
                        e.printStackTrace();
                    }
                    matrix = (int[][])res;
                	System.out.println("Received matrix with size " + matrix.length);
                	outpt.writeObject("Received matrix with size " + matrix.length);
                  
                }
                else if(received.equals("Start")) {
            		executor = Executors.newSingleThreadExecutor();
                    	worker = new Worker(matrix);
                    	future = executor.submit(worker);
                    	outpt.writeObject("Start calculating");
                }
                else if(received.equals("Result")) {
                	if(!future.isDone()) {
                		outpt.writeObject("Not ready yet. Enter Result later again");
        				
        			}
        			if(future.isDone()) {
        				System.out.println("Done");
        				outpt.writeObject(matrix);
        				executor.shutdown();
        			}
                }
                else if(!received.equals("Matrix")) {
                	outpt.writeObject("Wrong word try again");
                }
                
            } catch (IOException e) { 
                e.printStackTrace(); 
                break;
            } 
        } 
          
        try
        {  
            this.inpt.close(); 
            this.outpt.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace();
        } 
    } 
}
