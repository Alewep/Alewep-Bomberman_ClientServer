package agent;


import model.BombermanGame;

public class Rajiron extends Ennemy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2371215140327949681L;

	public Rajiron(int x, int y,BombermanGame game) {
		super(x, y,game);
	}
	
	@Override
	public char getType() {
		// TODO Auto-generated method stub
		return 'R';
	}
}
