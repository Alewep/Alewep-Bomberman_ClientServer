package agentStrategie;

import java.io.Serializable;

import agent.Agent;
import agent.BomberMan;
import model.BombermanGame;
import utils.AgentAction;

public class StrategyBird implements StrategyAgent ,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5951067098989889462L;

	private static final int rangeActivation = 3;
	
	private BombermanGame game;
	private Agent agent;
	
	
	public StrategyBird(BombermanGame game, Agent agent) {
		this.game = game;
		this.agent = agent;
	}

	
	public AgentAction executeAction() {
		for(BomberMan bm : game.getBombermen()) {
			if(Math.abs(bm.getX() - agent.getX()) <= rangeActivation
					&& Math.abs(bm.getY() - agent.getY()) <= rangeActivation ) {
				
				if(bm.getY() - agent.getY() < 0)
					return AgentAction.MOVE_UP;
				if (bm.getY() - agent.getY() > 0)
					return AgentAction.MOVE_DOWN;
				if(bm.getX() - agent.getX() < 0)
					return AgentAction.MOVE_LEFT;
				if (bm.getX() - agent.getX() > 0)
					return AgentAction.MOVE_RIGHT;
				
				
				
			}
		}
		return AgentAction.STOP;
	}


}
