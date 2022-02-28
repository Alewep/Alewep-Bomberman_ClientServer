package controller;


import model.BombermanGame;
import view.ViewBombermanGame;
import view.ViewCommand;

public class ControllerBombermanGame extends AbstractController{	
	private ViewCommand viewCommand;
	private ViewBombermanGame viewSimpleGame;

	public ControllerBombermanGame(BombermanGame game) {
		super(game);
		viewSimpleGame = new ViewBombermanGame(game);
		viewCommand = new ViewCommand(game,this);

	}

}
