package agent;

import agentStrategie.RandomStrategy;
import model.BombermanGame;
import utils.ColorAgent;

public class BomberMan extends Agent{
	
	private static final long serialVersionUID = -241903135964910968L;
	private ColorAgent color;
	private int bombRange = 1;
	
	public BomberMan(int x, int y, ColorAgent color,BombermanGame game) {
		super(x, y);
		this.color = color;
		this.strategy = new RandomStrategy();
	}

	@Override
	public char getType() {
		return 'B';
		
	}
	@Override
	public boolean canPoseBomb() {
		// TODO Auto-generated method stub
		return !this.isSick();
	}
	
	@Override
	public int getBombRange() {
		// TODO Auto-generated method stub
		return bombRange;
	}
	
	public ColorAgent getColor() {
		return color;
	}


	public void setBombRange(int bombRange) {
		this.bombRange = bombRange;
	}

	@Override
	public boolean isEnnemy() {
		// TODO Auto-generated method stub
		return false;
	}





}
