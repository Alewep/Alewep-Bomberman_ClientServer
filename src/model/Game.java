package model;
import java.io.Serializable;
import java.util.Vector;

import observer.Observable;
import observer.Observer;

public abstract class Game implements Runnable, Observable,Serializable {
	
	private static final long serialVersionUID = 7141325611942256230L;
	
	private int turn=0;
	private int maxturn;
	private boolean isRunning=false;
	private transient Thread thread;
	private long time;
	
	
	public abstract void initializeGame();
	public abstract void takeTurn();
	public abstract boolean gameContinue();
	public abstract void gameOver();
	
	//for Observable
	private Vector<Observer> observers = new Vector<Observer>();
	
	public Game(int maxturn,long time) {
		this.maxturn = maxturn;
		this.time = time;

	}
	
	public void lauch() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void init() {
		turn = 0;
		isRunning = false;
		initializeGame();
		notifyObserver();
	}
	
	public void step() {
		
		++turn;
		if (gameContinue() && turn < maxturn) takeTurn();
		else {
			isRunning = false;
			gameOver();
		}
		notifyObserver();
	}
	
	public void pause() {
		isRunning = false;
	}
	
	public void run() {
		
		while (isRunning) {
			step();
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void addObserver(Observer o) {
		observers.add(o);
		
	}

	public void deleteObserver(Observer o) {
		observers.remove(o);
		
	}

	public void notifyObserver() {
		for (Observer obs :observers) {
			obs.update();
		}
		
	}

	
	// getters
	public int getTurn() {
		return turn;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
	
	
	
}
