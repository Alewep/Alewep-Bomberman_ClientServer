package agent;

import java.io.Serializable;

import parameters.ParametersVelocity;
import utils.StateBomb;

public class Bombe {

	private static final long serialVersionUID = -896103582685580136L;
	private int x,y,range;
	private int state;
	private int velocity;
	
	
	public Bombe(int x,int y,int range) {
		this.x = x;
		this.y = y;
		this.range = range;
		this.state = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void next() {
		if (velocity <= 0 ) {
			++ state;
			velocity = ParametersVelocity.bomb;
		}
		else {
			--velocity;
		}
	}
	

	public StateBomb getStateBomb() {

		StateBomb[] temp = StateBomb.values();
		if (state < temp.length)
			return StateBomb.values()[state];
		else return StateBomb.Boom;
	}
	
	public int getRange() {
		return range;
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	
	public boolean isexplode(int xa, int ya) {
		return  ((xa >= x - range) && (xa <= x + range) && (ya == y)) 
				|| ((ya >= y - range) && (ya <= y + range) && (xa == x));
	}

}
