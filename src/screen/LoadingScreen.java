package screen;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoadingScreen extends JPanel{ 
	static ImageIcon load=new ImageIcon("danos.jpg");
	JLabel imageLabel;
	public LoadingScreen(int width,int height) {
	    load.setImage(load.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		imageLabel=new JLabel(load);
		imageLabel.setSize(width,height);
		imageLabel.setBackground(new Color(255,255,255,0));
		this.add(imageLabel);
		this.setLocation(0,0);
		this.setSize(width,height);
		imageLabel.setOpaque(false);
		this.setVisible(true);
	}
	public void LoadIn() {
		
	}
	
	public void LoadOut() {
		
	}
}