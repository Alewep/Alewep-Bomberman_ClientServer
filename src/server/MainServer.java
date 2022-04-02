package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.BombermanGame;
import parameters.ParametersVelocity;
import view.ViewBombermanGame;


public class MainServer {

	public static void main(String[] args) {
		
		
		try {
			
			ServerSocket serveurSocket = new ServerSocket(5000);
			Socket clientSocket = serveurSocket.accept();
			BombermanGame model = new BombermanGame(400000,ParametersVelocity.game,"niveau1.lay");
			ServerReceiver receiver = new ServerReceiver(clientSocket, model, 0);
			ServerSender sender = new ServerSender(model,clientSocket);
			receiver.start();
			sender.start();
			receiver.join();
			sender.join();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
