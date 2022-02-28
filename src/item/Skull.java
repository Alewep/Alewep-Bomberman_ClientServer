package item;

import agent.Agent;
import utils.ItemType;

public class Skull extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6828472590594147585L;
	static final int agentTurnSick = 50;

	public Skull(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void takeItem(Agent agent) {
		agent.setSick(agentTurnSick);
		
	}

	@Override
	public ItemType getType() {
		// TODO Auto-generated method stub
		return ItemType.SKULL;
	}

}
