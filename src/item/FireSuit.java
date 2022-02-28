package item;

import agent.Agent;
import utils.ItemType;

public class FireSuit extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2557980669578582730L;

	public FireSuit(int x, int y) {
		super(x, y);
	}

	@Override
	public ItemType getType() {
		return ItemType.FIRE_SUIT;
	}

	@Override
	public void takeItem(Agent agent) {
		agent.setInvincible(50);
		
	}

}
