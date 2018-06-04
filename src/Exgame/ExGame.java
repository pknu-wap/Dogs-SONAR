package Exgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import soundCapture.*;

interface soundHandler{
    void action(double now,double peak);
}

public class ExGame extends JFrame{
	
	ImageIcon iicon[];
	JButton btn[];
  
	ExGame(){
		super("Exgame");
		iicon = new ImageIcon[2];
		iicon[0]=new ImageIcon("æ«∏∂.jpg");
		iicon[1]=new ImageIcon("¡÷¿Œ∞¯.png");
		btn = new JButton[2];
		btn[0]=new JButton(iicon[0]);
		btn[1]=new JButton(iicon[1]);
		setVisible(true);
		JPanel pan = new JPanel();
		pan.add(btn[0]);
		pan.add(btn[1]);
		getContentPane().add(pan,"Center");
		setSize(300,200);
		setVisible(true);
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
