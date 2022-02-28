package agent;

import agentStrategie.RandomStrategy;
import model.BombermanGame;

public class Ennemy extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3467050285899246741L;

	public Ennemy(int x, int y,BombermanGame game) {
		super(x, y);
		this.strategy = new RandomStrategy();
		// TODO Auto-generated constructor stub
	}

	@Override
	public char getType() {
		// TODO Auto-generated method stub
		return 'E';
	}

	@Override
	public int getBombRange() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canPoseBomb() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBombRange(int range) {
		// TODO Auto-generated method stub
		//nothing
	}

	@Override
	public boolean isEnnemy() {
		// TODO Auto-generated method stub
		return true;
	}


}
