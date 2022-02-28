package agent;

import java.io.Serializable;

import agentStrategie.RandomStrategy;
import agentStrategie.StrategyAgent;
import utils.AgentAction;
import utils.ColorAgent;

public abstract class Agent implements Serializable {
	private static final long serialVersionUID = -3796514410810327233L;
	private int x;
	private int y;
	private int invincible;
	private int sick;
	private AgentAction predAction;
	protected StrategyAgent strategy;
	
	public Agent(int x, int y) {
		this.x=x;
		this.y=y;
		this.invincible = 0;
		this.sick = 0;
		this.strategy = new RandomStrategy();
	}

	
	public int getX() {
		return x;
	}

	

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}

	public boolean isInvincible() {
		return invincible > 0;
	}


	public void setInvincible(int invincible) {
		this.invincible = invincible;
	}


	public boolean isSick() {
		return sick > 0;
	}
	
	public AgentAction executeAction() {
		if (sick > 0) {
			--sick;
		}
		if(invincible > 0) {
			--invincible;
		}
		predAction = strategy.executeAction();
		return predAction;
	}
	
	public int getSick() {
		return sick;
	}


	public void setSick(int sick) {
		this.sick = sick;
	}


	public AgentAction getPredAction() {
		return predAction;
	}

	public boolean canFly() {
		return false;
	}
	
	public ColorAgent getColor() {
		return ColorAgent.DEFAULT;
	}
	
	public StrategyAgent getStrategy() {
		return strategy;
	}


	public void setStrategy(StrategyAgent strategy) {
		this.strategy = strategy;
	}


	public abstract char getType();
	public abstract int getBombRange();
	public abstract void setBombRange(int range);
	public abstract boolean canPoseBomb();
	public abstract boolean isEnnemy();

}
