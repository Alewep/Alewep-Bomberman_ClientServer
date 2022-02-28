package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import agent.BomberMan;
import agentStrategie.StrategyManuel;
import model.BombermanGame;
import utils.AgentAction;

public class ServerSender extends Thread {
	private static final long refresh_time = 10;
	private BombermanGame model;
	private Socket socket;
	
	
	
	
	
	public ServerSender(BombermanGame model, Socket socket) {
		super();
		this.model = model;
		this.socket = socket;
	}





	public void run () {
			
			try {
			        
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				//PrintWriter out = new PrintWriter(socket.getOutputStream());
				while (true) {
					//out.println(model.serialize());
					
					out.writeObject(model);
					out.reset();
					
					sleep(refresh_time);
				}
				
			} catch (IOException | InterruptedException e1) {
				
				e1.printStackTrace();
			}
		
		
		
	}

}
