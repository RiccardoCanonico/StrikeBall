import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {
	public static void main(String[] args) {
		ServerConnessioneTCP server = new ServerConnessioneTCP(2507, 60000);
		server.connect();
		//server.talk();
	}
}
