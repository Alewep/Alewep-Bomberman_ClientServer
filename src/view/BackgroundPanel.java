package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

class BackgroundPanel extends Panel
{
    // The Image to store the background image in.
    Image img;
    ImageIcon imgIcon;
    public BackgroundPanel(String background) throws IOException
    {
        // Loads the background image and stores in img object.
        img = ImageIO.read(new File(background));
        imgIcon = new ImageIcon(img);
    }

    public void paint(Graphics g)
    {
    	
        g.drawImage(img, 0, 0, null);
        super.paint(g);
    }
    
    
}