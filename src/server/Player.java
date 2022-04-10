package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import agent.Bombe;
import agent.BomberMan;
import model.BombermanGame;
import parameters.ParametersVelocity;

public class Player {
	
	private int id = 0;
	private String login;
	private ServerPlayerReceiver receiver;
	private ServerPlayerSender sender;
	private BombermanGame model;
	private Game game;
	ObjectInputStream in;
	ObjectOutputStream out;
	

	public int getId() {
		return id;
	}


	public Player(Game game,ObjectInputStream in,ObjectOutputStream out,BombermanGame model, int id) {
		
		this.game = game;
		this.model = model;
		this.id = id;
		this.in = in;
		this.out = out;
		
	}
	
	public void listener() {
		System.out.println("listerner");
		receiver = new ServerPlayerReceiver(in, this.model,this);
		sender = new ServerPlayerSender(this.model,out,this);
		receiver.start();
		sender.start();
		
	}
	
	public void removeInGame() {
		game.removePlayer(this);
	}
	
}
