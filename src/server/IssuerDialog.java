package server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import agent.BomberMan;
import agentStrategie.StrategyManuel;
import model.BombermanGame;
import model.InputMap;
import parameters.ParametersVelocity;
import view.InfoInstance;

public class IssuerDialog extends Thread {
	
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private Issuer issuer;
	public IssuerDialog(Socket clientSocket,Issuer issuer) {
		this.clientSocket = clientSocket;
		this.issuer = issuer;
	}
	
	public  void run() {
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		try {
			File file = new File("layouts");
			out = new ObjectOutputStream(clientSocket.getOutputStream());
		
			ArrayList<String> list = new ArrayList<String> (Arrays.asList(file.list()));
			out.writeObject(list);
		
			in = new ObjectInputStream(clientSocket.getInputStream());
			InfoInstance info = (InfoInstance) in.readObject();
			
			if (info.map != null) {
				
				
				
				
				BombermanGame model = new BombermanGame(4000,ParametersVelocity.game,info.map);
				Game game = new Game(model,issuer);
				
				
				issuer.addGame(game);
				Player added = game.addPlayer(out,in);
				if( added != null) {
					out.writeObject("ok");
					out.writeObject(Long.toString(game.getId()));
					String response  = (String) in.readObject();
					if(response.equals("start")) {
						added.listener();
						model.lauch();
					}
					else {
						issuer.removeGame(game);
					}
					
					
				}
				else {
					out.writeObject("Impossible to create this Game");
				}
				
				
			}
			
			else {
				
				Game game  = issuer.getGameid(info.id);
				if (game == null) {
					
					out.writeObject("The game with the id "+info.id+" doesn't exist");
					out.close();
					in.close();
					clientSocket.close();
				}
				else {
					
					Player added = game.addPlayer(out,in);
					if(added != null) {
						out.writeObject("ok");
						out.writeObject(Long.toString(game.getId()));
						String response  = (String) in.readObject();
						if(response.equals("start")) {
							added.listener();
						}
						else {
							issuer.removeGame(game);
						}
						
					}
					else {
						out.writeObject("The game is full");
					}
				}
				
			}
			
			
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			try {
				if(out != null)
					out.close();
				if(in != null)
					in.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		
		
		
	}

}
