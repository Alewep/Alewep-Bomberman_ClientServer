package agentStrategie;

import java.io.Serializable;
import java.util.Random;

import utils.AgentAction;

public class RandomStrategy implements StrategyAgent,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7944542976341732427L;

	@Override
	public AgentAction executeAction() {
		AgentAction[] actions = AgentAction.values();
		int rnd = new Random().nextInt(actions.length);
		return actions[rnd];
		
	}

}
