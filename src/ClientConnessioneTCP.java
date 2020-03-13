
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
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
    static String mess;
    static String servermess;
    //oggetto da usare per realizzare la connessione TCP
    Socket connection = null;
    //nome o IP del server
    String serverAddress = "localhost";
    //porta del server in ascolto
    int port;
    DataOutputStream out;
    DataInputStream in;
    BufferedReader tastiera;
    boolean exit = true;
    
    public ClientConnessioneTCP(int port){
    	this.port = port;   	
    }
    
    //apertura della connessione al server sulla porta specificata
    public Socket connect() {
		try{
		    connection = new Socket(serverAddress, port);
		    System.out.println("Connessione aperta");
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
		return connection;
    }
    //Metodo per la comunicazione con server
    public void talk() {
	    try{	
	    	while(exit) {
		    		//Definizione stream per la comunicazione
		    		tastiera = new BufferedReader(new InputStreamReader(System.in));
		    		out = new DataOutputStream(connection.getOutputStream());
				    in = new DataInputStream(connection.getInputStream());
				    
		    		//Invio messaggio
				    System.out.println("Inserisci il messaggio da mandare al server: \n");
				    mess = tastiera.readLine();
					out.writeUTF(mess + "\n");
					
					//Ricezione risposta server
					servermess = in.readUTF();
			        System.out.println("Risposta dal server: "+servermess);
			        if(mess.equals("FINE")) {
			        	exit = false;
			        }
				}
		    }
		    catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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