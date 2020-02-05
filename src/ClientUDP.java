import java.io.*;
import java.net.*;

public class ClientUDP {
	public static void main(String args[]) {
		int port = 2503;
		try {
			InetAddress IPServer = InetAddress.getByName("localhost");
			byte[] bufferOUT = new byte[1024];
			byte[] bufferIN = new byte[1024];
			
			DatagramSocket cSocket = new DatagramSocket();
			String send = "ciao";
			bufferOUT = send.getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPServer, port);
			cSocket.send(sendPacket);
			
			DatagramPacket recPacket = new DatagramPacket(bufferIN, bufferIN.length);
			cSocket.receive(recPacket);
			String rec = new String(recPacket.getData());
			
			int numCaratteri = recPacket.getLength();
			System.out.println(rec);
		} 
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
