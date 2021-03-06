import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread{    
	//oggetto da usare per realizzare la connessione TCP 
	 Socket connection;
     DataInputStream in;
     DataOutputStream out;
     String risposta = "ciao anche a te";
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
     
     // metodo per la comunicazione con client
     public void talk() {
    	try {
    		// definizione stream per la comunicazione
			in = new DataInputStream(connection.getInputStream());
			out = new DataOutputStream(connection.getOutputStream());
			while(connected) {
				// lettura richiesta server
				richiesta = in.readUTF();
				if(richiesta.equals("FINE")) {
					connected = false;
				}
				else {
					System.out.println("Richiesta client: "+richiesta);
					// invio risposta al client
		            out.writeUTF(risposta);
				}
			}
		} 
    	catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			try {
				if(connection != null) {
					out.close();
			        in.close();
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
