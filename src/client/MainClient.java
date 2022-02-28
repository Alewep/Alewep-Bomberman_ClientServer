package client;
import java.io.IOException;
import java.net.Socket;

public class MainClient {

	public static void main(String[] args) {
		
		try (Socket socket = new Socket("localhost",5000)) {
			
			ClientReceiver receiver = new ClientReceiver(socket);
			receiver.start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
