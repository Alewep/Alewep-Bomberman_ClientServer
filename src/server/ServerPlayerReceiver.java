package server;


import java.io.*;
import java.net.Socket;

import agent.BomberMan;
import agentStrategie.StrategyManuel;
import model.BombermanGame;
import utils.AgentAction;


public class ServerPlayerReceiver extends Thread {

	
	private BombermanGame model;
	private BomberMan bomberman;
	private StrategyManuel strategy;
	private ObjectInputStream in;
	private Player player;

	public ServerPlayerReceiver(ObjectInputStream in, BombermanGame model,Player player) {
		super();
		this.in = in;
		this.model = model;
		this.player = player;
		this.bomberman = model.getBombermen().get(player.getId());
		this.strategy = new StrategyManuel();
		this.bomberman.setStrategy(strategy);
	}
	
	
	public void run() {
		
		try {
			
			String msg = (String) in.readObject();
			
			while (msg != null) {
				
				String[] msg_split = msg.split(" ");
					
				
				// On définie l'action du bomwberman
				
				if ( msg_split.length >= 2  && msg_split[0].equals("action")) {
					try {
						
						AgentAction action = AgentAction.valueOf(msg_split[1].toUpperCase());
						//System.out.println("action:" + action);
						strategy.setLast_action(action);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					
				}
	
					
				msg = (String) in.readObject();
			}
			
			
		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		finally {
			player.removeInGame();
			try {
				if (in != null)
					in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	


	
	
	
	
	
	
}
