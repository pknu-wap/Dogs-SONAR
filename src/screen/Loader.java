package screen;

import javax.swing.*;

public class Loader extends JPanel { 
	static ImageIcon loadStart=new ImageIcon("tenor.gif");
	static ImageIcon load=new ImageIcon("tenor.gif");
	static ImageIcon loadEnd=new ImageIcon("tenor.gif");
	JPanel loadingScreen;
	public Loader() {
		loadingScreen=new JPanel();
		this.add(loadingScreen);
		loadingScreen.setVisible(false);
		
	}
	
	public Loader(boolean loadScreenOn) {
		 
	}
	
	public void LoadIn() {
		
	}
	
	public void LoadOut() {
		
	}
}
