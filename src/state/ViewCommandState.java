package state;

import view.ViewCommand;

public abstract class ViewCommandState {
		protected ViewCommand viewCommand;
		
		ViewCommandState(ViewCommand viewCommand){
			this.viewCommand = viewCommand;
			hideButton();
		}
		public abstract void hideButton();
		public abstract void restart();
		public abstract void play();
		public abstract void step();
		public abstract void pause();
		
	};
