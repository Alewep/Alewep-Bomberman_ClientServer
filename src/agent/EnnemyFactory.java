package agent;

import model.BombermanGame;

public class EnnemyFactory extends AgentFactory {

	@Override
	public Agent createAgent(int x, int y, char type,BombermanGame game) {
		switch (type) {
		case 'V':
			return new Bird(x,y,game);
		
		case 'R':
			return new Rajiron(x,y,game);
		case 'E':
			return new Ennemy(x,y,game);
		default:
			return null;
		}
	}


	

}
