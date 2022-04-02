package server;

import java.net.ServerSocket;
import java.util.ArrayList;

import model.BombermanGame;
import parameters.ParametersVelocity;

public class Issuer {
	
	
	private ArrayList<Game> games = new ArrayList<Game>();
	
	public Issuer() {
		
		while (true) {
			
			ServerSocket serveurSocket = new ServerSocket(5000);
			BombermanGame model = new BombermanGame(400000,ParametersVelocity.game,"niveau1.lay");
			Player player = new Player(serveurSocket,model,0);
			
			
		}
		
	}

}
