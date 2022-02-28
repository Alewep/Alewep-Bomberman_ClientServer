package agent;

import model.BombermanGame;
import utils.ColorAgent;

public class BomberManFactory extends AgentFactory {
	private static final ColorAgent[] colors = ColorAgent.values();
	private static int currentColor = 0;
	
	public static void inizialise() {
		currentColor = 0;
	}
	
	public  Agent createAgent(int x,int y,char type,BombermanGame game) {
		if (currentColor >= colors.length) {
			currentColor = 0;
		}
		
		Agent agent = new BomberMan(x,y,colors[currentColor],game);
		++currentColor;
		return agent;
	}
	

	

}
