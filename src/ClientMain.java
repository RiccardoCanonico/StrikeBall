import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientMain {
	public static void main(String[] args) {
		ClientConnessioneTCP client1 = new ClientConnessioneTCP(2507);
		client1.connect();
		client1.talk();		
	}
}
