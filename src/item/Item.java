package item;

import java.io.Serializable;

import agent.Agent;
import utils.ItemType;

public abstract class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3359640316413677963L;
	protected int x,y;

	public Item(int x, int y) {
		super();
		this.setX(x);
		this.setY(y);
	}
	
	
	public abstract ItemType getType();
	public abstract void takeItem(Agent agent);


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}

}
