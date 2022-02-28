package agentStrategie;

import java.io.Serializable;

import utils.AgentAction;

public class StrategyManuel implements StrategyAgent,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AgentAction lastAction = AgentAction.MOVE_RIGHT;
	
	

	public AgentAction executeAction() {
		
		AgentAction temp = lastAction;
		lastAction = AgentAction.STOP;
		return temp;
	}






	public void setLast_action(AgentAction last_action) {
		this.lastAction = last_action;
	}
	
	
	

}
