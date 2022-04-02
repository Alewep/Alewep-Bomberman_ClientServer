package view;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JFrame;

import agent.Agent;
import agent.Bombe;
import item.Item;
import model.BombermanGame;
import observer.*;
import utils.AgentAction;
import utils.ColorAgent;
import utils.InfoAgent;
import utils.InfoBomb;
import utils.InfoItem;
import utils.StateBomb;

public class ViewBombermanGame implements Observer {
	
	BombermanGame bombermanGame = null;
	PanelBomberman panelBomberman = null;
	JFrame frame = null;
	
	private void createFrame() {
		
		this.frame = new JFrame();
		frame.setTitle("Game");
		frame.setSize(new Dimension(panelBomberman.getSizeX() * 60 ,panelBomberman.getSizeY() * 60));
		Dimension windowSize = frame.getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		int dx = centerPoint.x - windowSize.width / 2 ;
		int dy = centerPoint.y - windowSize.height / 2 - 350;
		frame.setLocation(dx, dy);
		frame.setContentPane(panelBomberman);
		frame.setVisible(true);
		
	}
	public ViewBombermanGame(BombermanGame bombermanGame) {
		this(bombermanGame,true);
	}
	
	public ViewBombermanGame(BombermanGame bombermanGame, boolean createFrame) {
		this.bombermanGame = bombermanGame;
		initPanel();
		bombermanGame.addObserver(this);
		
		if (createFrame) {
			createFrame();
		}
		
	}
	
	
	public ViewBombermanGame(PanelBomberman panelBomberman) {
		this.panelBomberman = panelBomberman;
		createFrame();
	}
	
	
	public BombermanGame getBombermanGame() {
		return bombermanGame;
	}


	


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	public JFrame getFrame() {
		return frame;
	}


	public void initPanel() {
		panelBomberman = new PanelBomberman(
				bombermanGame.getMap().getSizeX(),
				bombermanGame.getMap().getSizeY(),
				bombermanGame.getMap().get_walls(),
				bombermanGame.getMap().getStart_breakable_walls(),
				bombermanGame.getMap().getStart_agents()); 
	}
	@Override
	public void update() {
		ArrayList<InfoBomb> bombs = new ArrayList<InfoBomb>();
		for(Bombe b:bombermanGame.getBombes()) {
			bombs.add(new InfoBomb(b.getX(),b.getY(),b.getRange(),b.getStateBomb()));
		}
		
		ArrayList<InfoAgent> agents = new ArrayList<InfoAgent>();
		for (Agent a:bombermanGame.getEnnemies()) {
			
			agents.add(new InfoAgent(a.getX(),a.getY(),a.getPredAction(),a.getType(), 
					a.getColor(),a.isInvincible(),a.isSick()));
			
		}
		for (Agent a:bombermanGame.getBombermen()) {
			agents.add(new InfoAgent(a.getX(),a.getY(),a.getPredAction(),a.getType(), 
					a.getColor(),a.isInvincible(),a.isSick()));
		}
		ArrayList<InfoItem> items = new ArrayList<InfoItem>();
		for(Item item:bombermanGame.getItems()) {
			items.add(new InfoItem(item.getX(),item.getY(),item.getType()));
		}
		
		panelBomberman.updateInfoGame(bombermanGame.getBreakable_walls(), agents,items, bombs);
		panelBomberman.updateUI();
	}
	
	public void updateUI(PanelBomberman panelBomberman) {
		this.panelBomberman.updateInfoGame(
				panelBomberman.getBreakable_walls(),
				panelBomberman.getListInfoAgents(),
				panelBomberman.getListInfoItems(),
				panelBomberman.getListInfoBombs()
		);
	
		this.panelBomberman.updateUI();
		
	}

	public PanelBomberman getPanelBomberman() {
		return panelBomberman;
	}

	public void setPanelBomberman(PanelBomberman panelBomberman) {
		this.panelBomberman = panelBomberman;
	}



}
