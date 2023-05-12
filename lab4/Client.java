package lab4;

import java.io.*;
import java.net.*;
import java.util.Random;


public class Client {
	 private Socket socket = null; 
	    private ObjectOutputStream out = null;
	    private ObjectInputStream in = null;
	    private BufferedReader reader = null;
	    private int [][] matrix ;
	    int size =500;
	    // constructor to put ip address and port 
	    public Client(String address, int port) 
	    { 
	        // establish a connection 
	        try
	        { 
	            socket = new Socket(address, port); 
	            System.out.println("Connected"); 
	  
	            // takes input from terminal 
	            reader = new BufferedReader(new InputStreamReader(System.in)); 
	  
	            // sends output to the socket 
	            out = new ObjectOutputStream(socket.getOutputStream());
	            in = new ObjectInputStream(socket.getInputStream());
	    
	            // print the initial data
	            System.out.println(in.readObject());
	        } 
	        catch(UnknownHostException u) 
	        { 
	            System.out.println(u); 
	        } 
	        catch(IOException i) 
	        { 
	            System.out.println(i); 
	        } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	  

	        // string to read message from input 
	        String line = ""; 
	        Object ob;
	        // keep reading until "Exit" is input 
	        while (!line.equals("Exit")) 
	        { 
	            try
	            { 
	            	line = reader.readLine();
	                if(line.equals("Send")) {
	                	Random rand = new Random();
	                	matrix = new int[size][size];
	                	for(int i = 0; i < size; i++) {
	                		for(int j = 0; j < size; j++) {
	                	    	matrix[i][j] = rand.nextInt(200);
	                	    }
	                	 }
	                	out.writeObject(matrix);
	                }else {
	                out.writeObject(line);
	                }
	               System.out.println(in.readObject());
	               System.out.println(in.readObject());
	            } 
	            catch(IOException i) 
	            { 
	                System.out.println(i); 
	            } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	        } 
	  
	        // close the connection 
	        try
	        { 
	            reader.close(); 
	            out.close(); 
	            socket.close(); 
	        } 
	        catch(IOException i) 
	        { 
	            System.out.println(i); 
	        } 
	    } 
	  
	    public static void main(String args[]) 
	    { 
	        Client client = new Client("127.0.0.1", 5056); 
	    } 
} 
