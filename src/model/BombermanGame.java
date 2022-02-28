package model;

import java.util.ArrayList;
import java.util.Iterator;
import agent.Agent;
import agent.AgentFactory;
import agent.Bombe;
import agent.BomberMan;
import agent.BomberManFactory;
import agent.EnnemyFactory;
import item.Item;
import item.RandomFactoryItem;
import utils.AgentAction;
import utils.ColorAgent;
import utils.InfoAgent;
import utils.StateBomb;

public class BombermanGame extends Game  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6677330981822223866L;
	private final static String racine = "layouts/";
	private InputMap map;
	private ArrayList<Agent> ennemies = new ArrayList<Agent>();
	private ArrayList<BomberMan> bombermen = new ArrayList<BomberMan>();
	private ArrayList<Bombe> bombes = new ArrayList<Bombe>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private boolean breakable_walls[][];
	
	

	
	private static int[] determinePosition(Agent agent,AgentAction action) {
		int x = agent.getX();
		int y = agent.getY();
		switch(action) {
			case MOVE_DOWN:
				++y;
				break;
			case MOVE_UP:
				--y;
				break;
			case MOVE_LEFT:
				--x;
				break;
			case MOVE_RIGHT:
				++x;
				break;
			default:
				break;
		
		}
		int couple[] = {x,y};
		return couple;
	}

	public BombermanGame(int maxturn, long time,String niveau) {
		super(maxturn, time);
		
		try {
			this.map = new InputMap(racine + niveau);
		} catch (Exception e) {
			e.printStackTrace();
		}
		initializeGame();
		
		
		
	}
	
	public String serialize () {
		char[][] game = new char[map.getSizeX()][map.getSizeY()];
		for (int i = 0;i<game.length;++i) {
			for (int j=0;j<game[0].length;++j)
			{
				
				game[i][j] = ' ';
				
				if (breakable_walls[i][j]) {
					game[i][j] = '+';
				}
				if (map.get_walls()[i][j]) {
					game[i][j] = '#';
				}
				
			}
		}
			
		for (BomberMan bm : bombermen) {
			game[bm.getX()][bm.getY()] = bm.getColor().toString().charAt(0);
			
		}
		for (Agent ag : ennemies) {
			game[ag.getX()][ag.getY()] = 'E';
		}

		
		String result = "";
		for (int i = 0;i<game[0].length;++i) {
			for (int j = 0;j<game.length;++j) {
				result += game[j][i];
			}
			result += '\n';
		}
		
		return result;
		
	
	}
	
	public BomberMan search_color (ColorAgent color) {
		for (BomberMan bm : bombermen ) {
			if (bm.getColor().equals(color)) {
				return bm;
			}
		}
		return null;
		
	}
	

	
	
	
	public void addAgent(int x,int y,char type) {
		AgentFactory factory;
		if (type == 'B') {
			factory = new BomberManFactory();
			bombermen.add((BomberMan) factory.createAgent(x, y, type,this));
		}
		else {
			factory = new EnnemyFactory();
			ennemies.add(factory.createAgent(x, y, type,this));
		}
	}
	
	public boolean isLegalMove(Agent agent,AgentAction action) {

		int[] couple = determinePosition(agent, action);
		int x = couple[0];
		int y = couple[1];

		boolean inboard =  (x > 0 && y > 0 && x < map.getSizeX() && y < map.getSizeY());
		if (inboard) {
			if (agent.canFly()) {
				return true;
			}
			else {
				return !breakable_walls[x][y] && !map.get_walls()[x][y];
			}
			
		}
		return false;
	}
	public void moveAgent(Agent agent,AgentAction action) {
		int[] couple = determinePosition(agent, action);
		int x = couple[0];
		int y = couple[1];
		agent.setX(x);
		agent.setY(y);
	}
	
	public void takeMoves() {
		for (Agent agent : ennemies) {
			AgentAction action = agent.executeAction();
			if (isLegalMove(agent, action)) {
				moveAgent(agent, action);
			}
			
		}
		for (BomberMan agent : bombermen) {
			AgentAction action = agent.executeAction();
			if (isLegalMove(agent, action)) {
				if (action == AgentAction.PUT_BOMB && agent.canPoseBomb()) {
					putBomb(agent);
				}
				else {
					moveAgent(agent, action);
				}
			}
			
		}
	}
	
	public void takeDeath() {
		Iterator<BomberMan> it = bombermen.iterator();
		while (it.hasNext()) { 
			BomberMan bomb = it.next();
			for (Agent enn :ennemies) {
				if(bomb.getX() == enn.getX() && bomb.getY() == enn.getY()
						&& !bomb.isInvincible()) {
					it.remove();
				}
			}
		}
	}
	
	public void takeBombs() {
		Iterator<Bombe> it = bombes.iterator();
		while (it.hasNext()) { 
			Bombe bomb = it.next();
			if (bomb.getStateBomb() == StateBomb.Boom) {
				it.remove();
				continue;
			}
			
			bomb.next();
			if (bomb.getStateBomb() == StateBomb.Boom) {
				
				Iterator<Agent> ite = ennemies.iterator();
				while (ite.hasNext()) { 
					Agent enn = ite.next();
					if (bomb.isexplode(enn.getX(),enn.getY())) {
						ite.remove();
					}
				}
				
				Iterator<BomberMan> itb = bombermen.iterator();
				while (itb.hasNext()) { 
					BomberMan  bm = itb.next();
					if (bomb.isexplode(bm.getX(),bm.getY())) {
						itb.remove();
					}
				}
				
				for(int offset=-bomb.getRange();offset<=bomb.getRange();++offset) {
					int x_offset = bomb.getX() + offset;
					int y_offset = bomb.getY() + offset;
					if (x_offset >= map.getSizeX())
						x_offset = map.getSizeX() - 1; 
					if (y_offset >= map.getSizeY())
						y_offset = map.getSizeY() - 1;
					if (y_offset < 0)
						y_offset = 0;
					if (x_offset < 0)
						x_offset = 0;
				
					//set item if destruction of a wall.
					
					if (breakable_walls[x_offset][bomb.getY()]) {
						Item item = RandomFactoryItem.create(x_offset,bomb.getY());
						if (item != null)
							items.add(item);
						breakable_walls[x_offset][bomb.getY()] = false;
					}
					
					if (breakable_walls[bomb.getX()][y_offset]) {
						Item item = RandomFactoryItem.create(bomb.getX(),y_offset);
						if (item != null)
							items.add(item);
						breakable_walls[bomb.getX()][y_offset] = false;
					}
					
					
				}
			}
		}
	}
	public void takeItems() {
		for (BomberMan bm: bombermen) {
			Iterator<Item> ite = items.iterator();
			while (ite.hasNext()) { 
				Item item = ite.next();
				if(item.getX() == bm.getX() && item.getY() == bm.getY()) {
					item.takeItem(bm);
					ite.remove();
				}
			}
			
		}
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void putBomb(BomberMan agent) {
		Bombe b = new Bombe(agent.getX(),agent.getY(),agent.getBombRange());
		bombes.add(b);
	}
	
	
	public boolean[][] initializeBreakable_walls() {
		boolean [][] myInt = new boolean[map.getStart_breakable_walls().length][];
		for(int i = 0; i < map.getStart_breakable_walls().length; i++)
		    myInt[i] = map.getStart_breakable_walls()[i].clone();
		return myInt;
	}
	
	@Override
	public void initializeGame() {
		BomberManFactory.inizialise();
		
		breakable_walls = initializeBreakable_walls();
		items.clear();
		ennemies.clear();
		bombermen.clear();
		bombes.clear();

		for (InfoAgent agent : map.getStart_agents()) {
			addAgent(agent.getX(),agent.getY(),agent.getType());
		}
	}

	@Override
	public void takeTurn() {
		takeMoves();
		takeDeath();
		takeBombs();
		takeItems();
	}
	@Override
	public boolean gameContinue() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		
	}
	

	public boolean[][] getBreakable_walls() {
		return breakable_walls.clone();
	}


	public ArrayList<BomberMan> getBombermen() {
		return bombermen;
	}

	public ArrayList<Agent> getEnnemies() {
		return ennemies;
	}

	public ArrayList<Bombe> getBombes() {
		return bombes;
	}


	public InputMap getMap() {
		return map;
	}
	
	
}
