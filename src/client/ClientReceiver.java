package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import model.BombermanGame;
import view.PanelBomberman;
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
		
		
		
		try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream()) ){
			PanelBomberman panel = (PanelBomberman) in.readObject();
			if (panel != null) {
				view = new ViewBombermanGame(panel);
				this.sender = new ClientSender(socket);
				view.getFrame().addKeyListener(this.sender);
				while (panel != null) {
				
					view.updateUI(panel);
					
					panel = (PanelBomberman) in.readObject();
					
				}
				System.out.println("fermeture");
				socket.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
	}
	
	
	
	
}
