package lab4;
import java.io.*;
import java.net.*;
public class Server {

	 public static void main(String[] args) throws IOException  
	    {  // server is listening on port 5056 
	        ServerSocket ss = new ServerSocket(5056); 
	          
	        while (true)  
	        { 
	            Socket s = null; 
	              
	            try 
	            { 
	                s = ss.accept();  
	                System.out.println("A new client is connected : " + s); 
	                  
	                
	                ObjectInputStream  inpt = new ObjectInputStream (s.getInputStream()); 
	                ObjectOutputStream  outpt = new ObjectOutputStream(s.getOutputStream()); 
	                  
	                System.out.println("Assigning new thread for this client"); 
	  
	                Thread t = new ClientHandler(s, inpt, outpt); 
	  
	                t.start(); 
	                  
	            } 
	            catch (Exception e){ 
	                s.close(); 
	                ss.close();
	                e.printStackTrace(); 
	            } 
	        } 
	    } 
}
