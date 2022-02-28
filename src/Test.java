import controller.ControllerBombermanGame;
import model.BombermanGame;

public class Test {

	public static void main(String[] args) {
		
//		SimpleGame game = new SimpleGame(10,1000);
//		ControllerSimpleGame controller = new ControllerSimpleGame(game);
		
		BombermanGame game = new BombermanGame(40000,1000,"alone.lay");
		ControllerBombermanGame controller = new ControllerBombermanGame(game);
		
		
	}

}

