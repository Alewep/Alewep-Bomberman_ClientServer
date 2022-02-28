package state;
import view.ViewCommand;

public class StatePlay extends ViewCommandState {
		
		StatePlay(ViewCommand viewCommand) {
			super(viewCommand);
		}
		public void hideButton() {
			viewCommand.restart.setEnabled(true);
			viewCommand.play.setEnabled(false);
			viewCommand.step.setEnabled(false);
			viewCommand.pause.setEnabled(true);
			
		}
		public void restart() {
			viewCommand.getController().restart();
			viewCommand.state = new StateByStep(viewCommand);
		}
		public void play() {
			//nothing
		}
		public void step() {
			//nothing
		}
		public void pause() {
			viewCommand.getController().pause();
			viewCommand.state = new StateByStep(viewCommand);
		}
	}

