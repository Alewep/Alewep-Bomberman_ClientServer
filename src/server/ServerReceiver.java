package server;


import java.io.*;
import java.net.Socket;

import agent.BomberMan;
import agentStrategie.StrategyManuel;
import model.BombermanGame;
import utils.AgentAction;


public class ServerReceiver extends Thread {

	private Socket socket;
	private BombermanGame model;
	private BomberMan bomberman;
	private StrategyManuel strategy;

	public ServerReceiver(Socket socket, BombermanGame model,int numBomberman) {
		super();
		this.socket = socket;
		this.model = model;
		this.bomberman = model.getBombermen().get(numBomberman);
		this.strategy = new StrategyManuel();
		this.bomberman.setStrategy(strategy);
	}
	
	
	public void run() {
		
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String msg = in.readLine();
			
			while (msg != null) {
				System.out.println("msg: " + msg);
				String[] msg_split = msg.split(" ");
					
				
				// On définie l'action du bomwberman
				
				if ( msg_split.length >= 2  && msg_split[0].equals("action")) {
					try {
						
						AgentAction action = AgentAction.valueOf(msg_split[1].toUpperCase());
						System.out.println("action:" + action);
						strategy.setLast_action(action);
					}
					catch (Exception e) {
						e.printStackTrace();
					}
					
				}
	
					
				msg = in.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


	
	
	
	
	
	
}
