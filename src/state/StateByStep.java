package state;
import view.ViewCommand;

public class StateByStep extends ViewCommandState {
	StateByStep(ViewCommand viewCommand) {
		super(viewCommand);
	}
	public void restart() {
		viewCommand.getController().restart();
		viewCommand.state = new StateByStep(viewCommand);
	}
	public void play() {
		
		viewCommand.getController().play();
		viewCommand.state = new StatePlay(viewCommand);
	}
	public void step() {
		viewCommand.getController().step();
	}
	public void pause() {
		// nothing
	}
	public void hideButton() {
		viewCommand.restart.setEnabled(false);
		viewCommand.play.setEnabled(true);
		viewCommand.step.setEnabled(true);
		viewCommand.pause.setEnabled(false);
		
		
	}
}