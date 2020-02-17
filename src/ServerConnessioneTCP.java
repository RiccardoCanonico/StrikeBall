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
        /*BufferedReader in;
        DataOutputStream out;*/
        String risposta = "bom bom";
        String richiesta = null;
        boolean connected = true;
        
        public ServerConnessioneTCP(int port, int timer) {
        	this.port = port;
        	this.timer = timer;
        }
        public void connect() {
	        try{
	        	 // il server si mette in ascolto sulla porta voluta
	            sSocket = new ServerSocket(port);
	        	while(connected) {
		            Countdown c=new Countdown(timer);
		            System.out.println("In attesa di connessioni!");
		            sSocket.setSoTimeout(timer);
		            c.start();
		            connection = sSocket.accept();
		            c.connected=true;
		            ServerThread sThread = new ServerThread(connection);
		            System.out.println("Connessione stabilita!");
		            System.out.println("Socket server: " + connection.getLocalSocketAddress());
		            System.out.println("Socket client: " + connection.getRemoteSocketAddress());
		            sThread.start();
		            c.interrupt();
		            
		            /*in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		            out = new DataOutputStream(connection.getOutputStream());*/
	        	}	            
	        }
	        catch(IOException e){
	            System.err.println("Errore di I/O (metodo connect)");	            
	        }
        }
        /*public void talk() {
			try {
				richiesta = in.readLine();
	            System.out.println(richiesta);
	            out.writeBytes(risposta);
	              
	            out.close();
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
        }*/
        
}
