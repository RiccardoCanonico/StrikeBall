import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread{    
     ServerSocket sSocket = null;     
     Socket connection;
     BufferedReader in;
     DataOutputStream out;
     String risposta = "bom bom";
     String richiesta = null;
     boolean connected = true;
     
     public ServerThread(Socket connection) {
    	 this.connection=connection;
     }
     
     public void run() {
    	 try {
    		 talk();
    	 }
    	 catch(Exception e2) {
    		 System.err.println("Errore sul metodo run");
    	 }
     }
     
     public void talk() {
    	try {
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			out = new DataOutputStream(connection.getOutputStream());
		} 
    	catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			richiesta = in.readLine();
            System.out.println("Richiesta client: "+richiesta);
            out.writeBytes(risposta);
              
            out.close();
            in.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Errore di I/O");
		}
		finally {
			try {
				if(connection != null) {
					connection.close();
					System.out.println("Connessione chiusa!");
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Errore nella chiusura della connessione!");
			}				
		}
    	 
     }
}
