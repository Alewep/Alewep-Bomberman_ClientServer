package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.BombermanGame;
import parameters.ParametersVelocity;

public class Player {
	
	private long id = 0;
	private Socket clientSocket;
	private ServerReceiver receiver;
	private ServerSender sender;
	private BombermanGame model;
	private Game game;
	
	public Player(ServerSocket serveurSocket,BombermanGame model, int id) {
		
		
	 try {
		 
			clientSocket = serveurSocket.accept();
			receiver = new ServerReceiver(clientSocket, model, id);
			sender = new ServerSender(model,clientSocket);
			receiver.start();
			sender.start();
			receiver.join();
			sender.join();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
