package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import model.BombermanGame;
import utils.AgentAction;
import view.ViewBombermanGame;

public class ClientSender extends Thread implements KeyListener{
	
    private static final String KW_ACTION = "action";
    private ObjectOutputStream out;

	public ClientSender(ObjectOutputStream out) throws IOException {
		super();
		
		
		this.out = out;
		
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
            try {
				out.writeObject("action "+action);
				out.reset();
			} catch (IOException e) {
				e.printStackTrace();
				try {
					if (out != null)
						out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
            
        }
		
	}
	public void keyTyped(KeyEvent event) {
		// nothing to do 
		
	}

	
	
	
}
