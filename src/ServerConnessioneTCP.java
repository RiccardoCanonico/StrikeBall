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
    public static void main(String[] args) {
        // porta del server maggiore di 1024 
        int port=2501;
        //oggetto ServerSocket necessario per accettare richieste dal client
        ServerSocket sSocket = null;
        //oggetto da usare per realizzare la connessione TCP
        Socket connection;
        int timer=10000;
        Countdown c=new Countdown(timer);
        BufferedReader in;
        DataOutputStream out;
        try{
            // il server si mette in ascolto sulla porta voluta
            sSocket = new ServerSocket(port);
            System.out.println("In attesa di connessioni!");
            sSocket.setSoTimeout(timer);
            c.start();
            connection = sSocket.accept();
            c.connected=true;
            System.out.println("Connessione stabilita!");
            System.out.println("Socket server: " + connection.getLocalSocketAddress());
            System.out.println("Socket client: " + connection.getRemoteSocketAddress());
            c.interrupt();
            
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new DataOutputStream(connection.getOutputStream());
            
            String str = in.readLine();
            System.out.println(str);
            
            String strRisp = "ciao";
            out.writeBytes(strRisp);
            
            out.close();
        }
        catch(IOException e){
            System.err.println("Errore di I/O!");
            
        }

        //chiusura della connessione con il client
        try {
            if (sSocket!=null) 
                sSocket.close();
        } 
        catch (IOException ex) {
            System.err.println("Errore nella chiusura della connessione!");
        }
        System.out.println("Connessione chiusa!");
      }
}
