package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import agent.BomberMan;
import model.BombermanGame;
import parameters.ParametersVelocity;

public class Game {
	private static long current_id = 0;
	
	private long id = 0;
	public long getId() {
		return id;
	}
	private ArrayList<Player> players = new ArrayList<Player>();
	private BombermanGame model;
	private Issuer issuer;
	
	public Game(BombermanGame model,Issuer issuer) {
		this.model = model;
		id = current_id;
		++ current_id;
		
		this.issuer = issuer;
	}
	

	
	public synchronized Player addPlayer(ObjectOutputStream out,ObjectInputStream in) {
		// recherche si des bomberman son non alloué.
		// ajoute le Player avec l'id du bomberman dans le cas si c'est la cas
		for (int i =0;i<model.getBombermen().size();++i) {
			
				
			if (!playerIdExist(i)) {
				Player p = new Player(this,in,out,model,i);
				players.add(p);
				return p;
			}
			
		}
		return null;
	}
	
	
	public synchronized boolean playerIdExist(int id) {
		
		for (Player player:players) {
			if (player.getId() == id) {
				return true;
			}
		}
		return false;
		
	}
	public synchronized void removePlayer(Player p) {
		players.remove(p);
		
		// verifie si la liste des joueurs est vide dans ce cas on supprime la partie de la liste des parties
		if (players.isEmpty()) {
			issuer.removeGame(this);
		}
		
	}
	
	
	
}

