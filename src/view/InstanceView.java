package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

import client.ControllerInstance;

public class InstanceView {
	
	private InfoInstance info = new InfoInstance();
	ArrayList<String> maps = new ArrayList<String>();
	
	private JFrame frame;
	private ImagePanel panel;
	
	private ControllerInstance controller;
	
	public ControllerInstance getController() {
		return controller;
	}

	public void setController(ControllerInstance controller) {
		this.controller = controller;
	}

	// buttons
	private JButton buttonCreate;
	private JButton buttonJoin;
	
	class ImagePanel extends JPanel {
	    private Image image;
	    public ImagePanel(Image image) {
	        this.image = image;
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this);
	    }
	}

	
	 public InstanceView(ControllerInstance controller){
		
		this.controller = controller;
		 
		this.frame = new JFrame("Bomberman - Menu");
		
		frame.setMinimumSize(new Dimension(800, 600));
		Dimension windowSize = frame.getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();
		int dx = centerPoint.x - windowSize.width / 2 ;
		int dy = centerPoint.y - windowSize.height / 2;
		frame.setLocation(dx, dy);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
		
	 }
	 
	 public ArrayList<String> getMaps() {
		return maps;
	}

	public void setMaps(ArrayList<String> maps) {
		this.maps = maps;
	}

	public void init() {
			BufferedImage myImage = null;
			try {
				myImage = ImageIO.read(new File("images/home.png"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.panel = new ImagePanel(myImage);
			frame.setContentPane(panel);
			panel.setLayout(null);
			
			
			//buttons
			this.buttonCreate = new JButton("New game");
	        buttonCreate.setFont(new Font("Calibri", Font.PLAIN, 14));
	        buttonCreate.setBackground(new Color(0x2dce98));
	        buttonCreate.setForeground(Color.white);
	        buttonCreate.setBounds(150,450,200,50);
	        panel.add(buttonCreate); 
	        buttonCreate.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent evt) {
	               create();
	            }	
	        });
			
	        this.buttonJoin = new JButton("Join game");
	        buttonJoin.setFont(new Font("Calibri", Font.PLAIN, 14));
	        buttonJoin.setBackground(new Color(0x2dce98));
	        buttonJoin.setForeground(Color.white);
	        buttonJoin.setBounds(800 - 150 - 200,450,200,50);
	        panel.add(buttonJoin); 
	        
	        buttonJoin.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent evt) {
	               join();
	            }	
	        });
			

	    	
	        // menu
	        JMenuBar menuBar = new JMenuBar();
	        JButton login = new JButton( "Login" );
	        login.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent evt) {
	               connexion();
	            }	
	        });
	        
	        menuBar.add(login);
	        frame.setJMenuBar(menuBar);
			
	     
	        frame.setVisible(true);
		
	 }
	 
	 public InfoInstance getInfo() {
		return info;
	}

	public void connexion() {
		 
		 JTextField login = new JTextField(info.login);
	        JTextField password = new JPasswordField(info.password);
	        Object[] message = {
	            "Login:", login,
	            "Password:", password
	        };

	        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
	        if (option == JOptionPane.OK_OPTION) {
	            info.login = login.getText();
	            info.password = password.getText();
	        } 
	 }
	 
	 
	 public void  create() {
		 
		 
		 
		 if (info.login == null && info.password == null) {
			 JOptionPane.showMessageDialog(frame, "Please enter password and login before", "Warning", JOptionPane.INFORMATION_MESSAGE);
			 return;
		 }
		controller.initConnexion();
		
		panel.remove(buttonJoin);

		panel.remove(buttonCreate);
		buttonJoin = null;
		buttonCreate = null;
	    
	    panel.invalidate();
	    panel.repaint();
		
		// label
		JLabel lb = new JLabel("Choose a map :");
		lb.setForeground(Color.white);
		lb.setBounds(150,450,300,50);
		panel.add(lb);
		
		// combobox
	    JComboBox cb = new JComboBox<String>( Arrays.copyOf(maps.toArray(), maps.size(), String[].class)); 
	    cb.setSelectedIndex(0);
	    cb.setBounds(300,450,300,50);
	    panel.add(cb);
	    
	    // button 
	    JButton validate = new JButton("Confirm");
	    validate.setBounds(600,450,100,50);
	    panel.add(validate);
	    
	    
	    validate.addActionListener(new ActionListener(){
	    	

			@Override
			public void actionPerformed(ActionEvent e) {
				info.map = (String)cb.getName();
				
			}
	    	
	    });
	    
	    
	   
	    cb.addActionListener(new ActionListener() {  
	        public void actionPerformed(ActionEvent e) {  
	        	info.map = (String) cb.getItemAt(cb.getSelectedIndex());
	        	getController().prepare();
	        
	        }     
	
	    });
	    
	    panel.setVisible(true);
	    panel.revalidate();
	    panel.repaint();
	  


	    
	 }

	 void join () {
		 
		 
		 
		 if (info.login == null && info.password == null) {
			 JOptionPane.showMessageDialog(frame, "Please enter password and login before", "Warning", JOptionPane.INFORMATION_MESSAGE);
			 return;
		 }
		controller.initConnexion();
		panel.remove(buttonJoin);

		panel.remove(buttonCreate);
		buttonJoin = null;
		buttonCreate = null;
	    
	    panel.invalidate();
	    panel.repaint();
	    
	    
	    SpinnerModel model = new SpinnerNumberModel(0,0,Long.MAX_VALUE, 1);  
	    JSpinner spinner = new JSpinner(model);
	    spinner.setBounds(250,450,300,50);
	    panel.add(spinner);
	    
	    
	    JButton validate = new JButton("Confirm");
	    validate.setBounds(550,450,100,50);
	    panel.add(validate);
	    
		JLabel lb = new JLabel("Enter the game id : ");
		lb.setForeground(Color.white);
		lb.setBounds(100,450,300,50);
		panel.add(lb);
	    
	    validate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				info.id = ((Double) model.getValue()).longValue();
				getController().prepare();
				
			}
	    	
	    });
	    
	    
	    panel.setVisible(true);
	    panel.revalidate();
	    panel.repaint();
	  
	 
	 }
	 
	 public void error(String error) {
		 
		 JOptionPane.showMessageDialog(frame,error, "Server connexion error", JOptionPane.ERROR_MESSAGE);
		 init();
	 }
	 
	 public void displayId(String id) {
		 JOptionPane.showMessageDialog(frame,"The game id is "+id, "Game id", JOptionPane.INFORMATION_MESSAGE);
		 controller.runGame();
		 
	 }
	 
	 public void validate() {
		 frame.dispose();
		 frame = null;
	 }

}
