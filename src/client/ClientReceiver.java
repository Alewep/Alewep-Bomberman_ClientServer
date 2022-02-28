package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import model.BombermanGame;

import view.ViewBombermanGame;

public class ClientReceiver extends Thread {
	private Socket socket;
	private ClientSender sender;
	private ViewBombermanGame view;
	
	public ClientReceiver(Socket socket) {
		super();
		this.socket = socket;
		
	}
	
	
	public void run () {
		
		
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(socket.getInputStream());
			BombermanGame model = (BombermanGame) in.readObject();
			if (model != null) {
				view = new ViewBombermanGame(model);
				this.sender = new ClientSender(socket);
				view.getFrame().addKeyListener(this.sender);
				while (model != null) {
				
					view.updateByModel(model);
					
					model = (BombermanGame) in.readObject();
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
			
		
	}
	
	
	
	
}
