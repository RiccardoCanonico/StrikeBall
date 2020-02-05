import java.io.*;
import java.net.*;

public class serverUDP {
	public static void main(String args[]) {
		try {
			DatagramSocket sSocket = new DatagramSocket(2503);
			
			boolean connect = true;
			byte[] bufferIn = new byte[1024];
			byte[] bufferOut = new byte[1024];
			
			while(connect) {
				DatagramPacket recPacket = new DatagramPacket(bufferIn, bufferIn.length);
				sSocket.receive(recPacket);
				
				String received = new String(recPacket.getData());
				int numCaratteri = recPacket.getLength();
				System.out.println(received);
				
				InetAddress IPClient = recPacket.getAddress();
				int porta = recPacket.getPort();
				
				
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
