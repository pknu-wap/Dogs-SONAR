package screen;

import javax.swing.*;

public class Loader extends JPanel { 
	static ImageIcon loadStart=new ImageIcon("test.gif");
	static ImageIcon load=new ImageIcon("tesk.gif");
	static ImageIcon loadEnd=new ImageIcon("test.gif");
	JLabel imageLabel;
	public Loader() {
		imageLabel=new JLabel();
		this.add(imageLabel);
		this.setVisible(false);
		
	}
	public void LoadIn() {
		imageLabel.setIcon(loadStart);
		
	}
	
	public void LoadOut() {
		
	}
}
