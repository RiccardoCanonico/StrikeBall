
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;

/**
 *
 * @author Riccardo Canonico
 */
public class ClientConnessioneTCP {
    /**
     * @param args the command line arguments
     */
    static String mess = "mr.sandman";
    static String servermess;
    //oggetto da usare per realizzare la connessione TCP
    Socket connection = null;
    //nome o IP del server
    String serverAddress = "localhost";
    //porta del server in ascolto
    int port = 2501;
    DataOutputStream out;
    BufferedReader in;
    
    //apertura della connessione al server sulla porta specificata
    public void connect() {
		try{
		    connection = new Socket(serverAddress, port);
		    System.out.println("Connessione aperta");
		    out = new DataOutputStream(connection.getOutputStream());
		    in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		}
		catch(ConnectException e){
		    System.err.println("Server non disponibile!");
		}
		catch(UnknownHostException e1){
		    System.err.println("Errore DNS!");
		}		
		catch(IOException e2){//
		    System.err.println(e2);
		    e2.printStackTrace();
		}
    }
    public void talk() {
    	try {
			out.writeBytes(mess + "\n");
			servermess = in.readLine();
	        System.out.println("Risposta dal server: "+servermess);
		} 
    	catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	finally {
		    try {
			      if (connection!=null)
			        {
			            connection.close();
			            System.out.println("Connessione chiusa!");
			        }
				}
		    catch(IOException e){
		        System.err.println("Errore nella chiusura della connessione!");
		    }
    	}
	}
}