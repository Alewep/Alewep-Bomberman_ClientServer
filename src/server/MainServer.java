package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.BombermanGame;


public class MainServer {

	public static void main(String[] args) {
		
		
		try {
			
			ServerSocket serveurSocket = new ServerSocket(5000);
			Socket clientSocket = serveurSocket.accept();
			BombermanGame model = new BombermanGame(400000,1000/15,"niveau1.lay");
			
			model.lauch();
			ServerReceiver receiver = new ServerReceiver(clientSocket, model, 0);
			ServerSender sender = new ServerSender(model,clientSocket);
			receiver.start();
			sender.start();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
