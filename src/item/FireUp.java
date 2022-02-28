package item;

import agent.Agent;
import utils.ItemType;

public class FireUp extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1727871152425208722L;

	public FireUp(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemType getType() {
		// TODO Auto-generated method stub
		return ItemType.FIRE_UP;
	}

	@Override
	public void takeItem(Agent agent) {
		agent.setBombRange(agent.getBombRange() + 1);
	}

}
