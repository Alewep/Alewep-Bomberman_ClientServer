package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import model.BombermanGame;
import utils.AgentAction;
import view.ViewBombermanGame;

public class ClientSender extends Thread implements KeyListener{
	
    private static final String KW_ACTION = "action";
    private PrintWriter out;
    private Socket socket;



	public ClientSender(Socket socket) throws IOException {
		super();
		this.socket = socket;
		
		this.out = new PrintWriter (socket.getOutputStream());
		
	}


	@Override
	public void keyReleased(KeyEvent event) {
		//nothing to do
		
	}

	
	public void keyPressed(KeyEvent event) {
		
        String action = null;
        switch(event.getKeyCode()) {
            case KeyEvent.VK_Z:
                action = AgentAction.MOVE_UP.name();
                break;
            case KeyEvent.VK_Q:
                action = AgentAction.MOVE_LEFT.name();
                break;
            case KeyEvent.VK_D:
                action = AgentAction.MOVE_RIGHT.name();
                break;
            case KeyEvent.VK_S:
                action = AgentAction.MOVE_DOWN.name();
                break;
            case KeyEvent.VK_SPACE:
            	action = AgentAction.PUT_BOMB.name();
            
        }
        if (action != null ) {
            System.out.println(action);
            out.println(KW_ACTION + " " +action);
            out.flush();
        }
		
	}
	public void keyTyped(KeyEvent event) {
		// nothing to do 
		
	}

	
	
	
}
