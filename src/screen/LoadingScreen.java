package screen;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoadingScreen extends JPanel{
    static ImageIcon originalBack=new ImageIcon("outside.png");
	static ImageIcon original=new ImageIcon("inside.png");
	JLabel outsideLabel;
	JLabel insideLabel;
	public LoadingScreen(int width,int height) {
	    outsideLabel=new JLabel(new ImageIcon(originalBack.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
	    insideLabel=new JLabel(new ImageIcon(original.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		outsideLabel.setSize(width,height);
		insideLabel.setSize(width,height);
		insideLabel.setOpaque(true);
		outsideLabel.setOpaque(true);
		this.add(outsideLabel);
		this.add(insideLabel);
		this.setLocation(0,0);
		this.setSize(width,height);
		this.setVisible(true);
	}
	public void LoadIn() {
		
	}
	
	public void LoadOut() {
		
	}
}