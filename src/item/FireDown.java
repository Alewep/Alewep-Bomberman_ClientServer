package item;

import agent.Agent;
import utils.ItemType;

public class FireDown extends Item{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2370356268141523049L;

	public FireDown(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemType getType() {
		return ItemType.FIRE_DOWN;
	}

	@Override
	public void takeItem(Agent agent) {
		agent.setBombRange(agent.getBombRange() - 1);
	}

}
