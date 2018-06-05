package Exgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import soundCapture.*;

interface soundHandler{
    void action(double now,double peak);
}

public class ExGame extends JFrame{
	int random = (int)(Math.random()*10);
	ImageIcon iicon[];
	JButton btn[];
	BorderLayout layout = new BorderLayout();
	
	ExGame(){
		super("Exgame");
		setLayout(layout);
		iicon = new ImageIcon[2];
		iicon[0]=new ImageIcon("악당.jpg");
		iicon[1]=new ImageIcon("주인공.png");
		btn = new JButton[2];
		btn[0]=new JButton(iicon[0]);
		btn[1]=new JButton(iicon[1]);
		if(random>5) {
			add(btn[0],BorderLayout.WEST);}
		else
			add(btn[1],BorderLayout.EAST);
		setSize(300,200);
		setVisible(true);
		System.out.println(random);
	}
	public static void main(String args[]) {
		new ExGame();
		}
	class ExampleSoundHandler implements soundHandler{

	    @Override
	    public void action(double now,double peak) {
	        System.out.println("now : "+(int)now+" peak : "+(int)peak);
	    }
	    
	}
}
