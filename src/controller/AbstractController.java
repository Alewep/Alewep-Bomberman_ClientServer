package controller;
import model.Game;

public abstract class AbstractController {
	
	private Game game;
	
	AbstractController(Game game) {
		this.game = game;
	}
	public void restart() {
		game.pause();
		game.init();
	}
	public void step() {
		game.step();
	}
	public void play() {
		game.lauch();
	}
	public void pause() {
		game.pause();
	}
	//number of turn per seconds
	public void setSpeed(double speed){
		game.setTime(Math.round((1/speed)*1000));
	}
	
	

}
