package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.BombermanGame;
import parameters.ParametersVelocity;

public class Issuer {
	
	
	private ArrayList<Game> games = new ArrayList<Game>();
	
	public Issuer() {
		
		
			
			try(ServerSocket serveurSocket = new ServerSocket(5000)) {
				
				while(true) {
				
					Socket socket = serveurSocket.accept();
					IssuerDialog dialog = new IssuerDialog(socket,this);
					dialog.start();
					
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
	}
	
	public synchronized Game getGameid(long id) {
		for (Game g : games) {
			if (g.getId() == id) {
				return g;
			}
		}
		return null;
		
	}
	
	public synchronized void addGame(Game game) {
		games.add(game);
		
	}
	public synchronized void removeGame(Game game) {
		games.remove(game);
		
	}


	
		
	

}
