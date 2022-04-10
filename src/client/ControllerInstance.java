package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

import view.InstanceView;

@SuppressWarnings("unchecked")
public class ControllerInstance {
	private InstanceView view;
	private Socket socket = null;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	
	
	public ControllerInstance() {
		
		view = new InstanceView(this);
		
	}
	
	public void initConnexion() {
		
		try  {
			socket = new Socket("localhost",5000);
			in = new ObjectInputStream(socket.getInputStream());
			ArrayList<String> maps = (ArrayList<String>) in.readObject();
			view.setMaps(maps);
		} catch (IOException | ClassNotFoundException e ) {
			e.printStackTrace();
			try {
				if (socket!=null)
					socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		
		
	}
	
	
	
	public void prepare() {
		
		try  {
			out = new ObjectOutputStream(socket.getOutputStream());
			
			out.writeObject(view.getInfo());
			
			String reponse = (String) in.readObject();
			if (reponse.equals("ok")) {
				reponse = (String) in.readObject();
				view.displayId(reponse);
			}
			else {
				view.error(reponse);
			}
		
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		
		
		
		
		
	}
	
	public void runGame() {
		
		try {
			out.writeObject("start");
		} catch (IOException e) {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		view.validate();
		ClientReceiver receiver = new ClientReceiver(in,out);
		receiver.start();
		
	}
	

	

}
