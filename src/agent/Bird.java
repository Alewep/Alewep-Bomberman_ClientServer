package agent;

import agentStrategie.StrategyBird;
import model.BombermanGame;

public class Bird extends Ennemy {

	private static final long serialVersionUID = 7685568627642978625L;

	public Bird(int x, int y,BombermanGame game) {
		super(x, y,game);
		this.strategy = new StrategyBird(game,this);
	}
	
	@Override
	public char getType() {
		// TODO Auto-generated method stub
		return 'V';
	}
	
	public boolean canFly() {
		return true;
	}

}
