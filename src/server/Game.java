package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.BombermanGame;
import parameters.ParametersVelocity;

public class Game {
	private static long current_id = 0;
	
	private long id = 0;
	private ArrayList<Player> players = new ArrayList<Player>();
	private BombermanGame model;
	
	
	public Game(String map) {
		model = new BombermanGame(4000,ParametersVelocity.game,map);
		id = current_id;
		++ current_id;
	}
	
	public void addPlayer(ServerSocket serverSocket) {
		int id = players.size();
		players.add(new Player(serverSocket,model,id));
	}
	
}

