package agent;

import model.BombermanGame;


public abstract class AgentFactory  {
	public abstract Agent createAgent(int x,int y, char type,BombermanGame game);
}