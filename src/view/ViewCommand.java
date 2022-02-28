package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.AbstractController;
import model.Game;
import observer.Observer;
import state.StateStart;
import state.ViewCommandState;

public class ViewCommand implements Observer{

	private JFrame jFrame;
	
	//model
	private Game game;
	
	//controller
	private AbstractController controller;

	//update attributes
	private JLabel numberofturn;
	
	// state of buttons
	public ViewCommandState state;
	
	//buttons
	public JButton restart,play,pause,step;
	
	//slider for control fps
	public JSlider slider;


	
	public ViewCommand(Game game,AbstractController controller) {
		
		
		this.game = game;
		this.controller = controller;
		
		display();
		
		state = new StateStart(this);
		
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				state.restart();
			}
		});
		
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				state.play();
			}
		});
		
		
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				state.pause();
			}
		});
		
		step.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				state.step();
			}
		});
		
	
		 slider.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent event) {
		    	controller.setSpeed(slider.getValue());
		  }});
		
	
		
	}
	public void display() {
		jFrame = new JFrame();
		jFrame.setTitle("Game");
		jFrame.setSize(new Dimension(1000, 300));
		Dimension windowSize = jFrame.getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		int dx = centerPoint.x - windowSize.width / 2 ;
		int dy = centerPoint.y - windowSize.height / 2 + 300;
		jFrame.setLocation(dx, dy);
		//definition of components :
		//-> label for number of turn 
		numberofturn = new JLabel("Turn :"+game.getTurn());
		//-> buttons
		restart = new JButton(new ImageIcon("ressources/icons/icon_restart.png"));
		play = new JButton(new ImageIcon("ressources/icons/icon_play.png"));
		pause = new JButton(new ImageIcon("ressources/icons/icon_pause.png"));
		step = new JButton(new ImageIcon("ressources/icons/icon_step.png"));
		//-> Jsider title
		JLabel label = new JLabel("Number of turn per seconds ");
		//-> Jslider
		slider = new JSlider(JSlider.HORIZONTAL,1,10,1);
		
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		//placement 
		Container pane = jFrame.getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(restart,c);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(play,c);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(pause,c);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 3;
		c.gridy = 0;
		pane.add(step,c);
				
		c.fill = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(label,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		pane.add(slider,c);
		
		c.fill = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 2;
		c.gridheight = 2;
		c.gridx = 2;
		c.gridy = 1;
		pane.add(numberofturn,c);

		//display
		jFrame.setVisible(true);
	}

	public void update() {
		numberofturn.setText("Turn :"+game.getTurn());
		
	}

	public AbstractController getController() {
		return controller;
	}
}
