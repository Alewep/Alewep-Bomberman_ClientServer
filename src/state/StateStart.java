package state;
import view.ViewCommand;

public class StateStart extends ViewCommandState {
	
	public StateStart(ViewCommand viewCommand) {
		super(viewCommand);
		
	}
	public void hideButton() {
		viewCommand.restart.setEnabled(false);
		viewCommand.play.setEnabled(true);
		viewCommand.step.setEnabled(true);
		viewCommand.pause.setEnabled(false);
		
	}
	public void restart() {
		//nothing
	}
	public void play() {
		viewCommand.getController().play();
		viewCommand.state = new StatePlay(viewCommand);
		
	}
	public void step() {
		viewCommand.getController().step();
		viewCommand.state = new StateByStep(viewCommand);
	}
	public void pause() {
		//nothing
	}
};
	