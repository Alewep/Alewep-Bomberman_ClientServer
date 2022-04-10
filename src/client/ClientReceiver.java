package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.BombermanGame;
import view.PanelBomberman;
import view.ViewBombermanGame;

public class ClientReceiver extends Thread {
	
	private ClientSender sender;
	private ViewBombermanGame view;
	ObjectInputStream in;
	ObjectOutputStream out;
	public ClientReceiver(ObjectInputStream in,ObjectOutputStream out) {
		super();
		this.in = in;
		this.out = out;
		
		
	}
	
	
	public void run () {
		
		
		
		try {
			PanelBomberman panel = (PanelBomberman) in.readObject();
			if (panel != null) {
				view = new ViewBombermanGame(panel);
				this.sender = new ClientSender(out);
				view.getFrame().addKeyListener(this.sender);
				while (panel != null) {
					
					view.updateUI(panel);
					
					panel = (PanelBomberman) in.readObject();
					
				}
				
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(out!=null)
					out.close();
				if(out!=null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
	}
	
	
	
	
}
