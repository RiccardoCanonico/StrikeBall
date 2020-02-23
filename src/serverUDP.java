import java.io.*;
import java.net.*;

public class serverUDP {
	public static void main(String args[]) {
		try {
			// definizione DatagramSocket per connettersi ad una porta
			DatagramSocket sSocket = new DatagramSocket(2503);
			
			boolean connect = true;
			// definizione array di byte
			byte[] bufferIn = new byte[1024];
			byte[] bufferOut = new byte[1024];
			
			// ciclo per la comunicazione
			while(connect) {
				// definizione DatagramPacket che conterrà il messaggio da ricevere
				DatagramPacket recPacket = new DatagramPacket(bufferIn, bufferIn.length);
				
				// ricezione e lettura della richiesta del client
				sSocket.receive(recPacket);
				String received = new String(recPacket.getData());
				int numCaratteri = recPacket.getLength();
				System.out.println(received);
				
				InetAddress IPClient = recPacket.getAddress();
				int porta = recPacket.getPort();
				
				// definizione pacchetto e invio della risposta al client
				String send = "ciao anche a te";
				bufferOut = send.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(bufferOut, bufferOut.length, IPClient, porta);
				sSocket.send(sendPacket);
				
			}
		} 
		catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
