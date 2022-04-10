package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import agent.BomberMan;
import agentStrategie.StrategyManuel;
import model.BombermanGame;
import utils.AgentAction;
import utils.InfoAgent;
import view.ViewBombermanGame;

public class ServerPlayerSender extends Thread {
	private static final long refresh_time = 10;
	private ViewBombermanGame view;
	private ObjectOutputStream out;
	private Player player;
	
	
	
	
	
	
	public ServerPlayerSender(BombermanGame model, ObjectOutputStream out,Player player) {
		super();
		this.view = new ViewBombermanGame(model,false);
		this.out = out;
		this.player = player;
	
	}





	public void run () {
			
			try {
			        
				
				
				while (true) {
					
					//System.out.println(view.getBombermanGame().getBombermen().get(0).getX());
					out.writeObject(view.getPanelBomberman());
					out.reset();
					
					sleep(refresh_time);
				}
				
			} catch (IOException | InterruptedException e1) {
				
				e1.printStackTrace();
			}
			finally {
				player.removeInGame();
				
				try {
					if(out!=null)
						out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		
		
		
	}

}
