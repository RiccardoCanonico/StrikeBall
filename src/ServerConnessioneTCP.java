/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Riccardo Canonico
 */
public class ServerConnessioneTCP {

    /**
     * @param args the command line arguments
     */
        // porta del server maggiore di 1024 
        private int port;
        private int timer;
        //oggetto ServerSocket necessario per accettare richieste dal client
        ServerSocket sSocket = null;
        //oggetto da usare per realizzare la connessione TCP
        Socket connection;
        boolean connected = true;
        
        public ServerConnessioneTCP(int port, int timer) {
        	this.port = port;
        	this.timer = timer;
        }
        public void connect() {
	        try{
	        	 // il server si mette in ascolto sulla porta voluta
	            sSocket = new ServerSocket(port);
	            System.out.println("In attesa di connessioni!");
	        	while(connected) {
		            Countdown c=new Countdown(timer);
		            // definizione timeout server
		            sSocket.setSoTimeout(timer);
		            // avvio thread per timeout
		            //c.start();
		            connection = sSocket.accept();
		            //c.connected=true;
		            // definizione thread per la comunicazione
		            ServerThread sThread = new ServerThread(connection);
		            System.out.println("Connessione stabilita!");
		            // aggiungere il socket in un ArrayList per poter gestire i giocatori connessi al gioco
		            System.out.println("Socket server: " + connection.getLocalSocketAddress());
		            System.out.println("Socket client: " + connection.getRemoteSocketAddress());
		            // avvio thread
		            sThread.start();
		            //c.interrupt();
	        	}	            
	        }
	        catch(IOException e){
	            System.err.println("Errore di I/O (metodo connect)");	            
	        }
        }
        
}
