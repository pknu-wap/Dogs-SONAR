package Exgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import soundCapture.*;

//<<<<<<< HEAD
//
//
//=======
//>>>>>>> de26c473eba3f55040f960cf95134fdb8d15ea81
public class ExGame extends JFrame{
	ImageIcon iicon[];
    JButton btn[];
	BorderLayout layout = new BorderLayout();
	LevelMeter capture;
	Reactor actor;
	ExGame(){
		super("Exgame");
		btn = new JButton[2];
		setLayout(layout);
		iicon = new ImageIcon[2];
        iicon[0]=new ImageIcon(new ImageIcon("demon.jpg").getImage().getScaledInstance(150, 200, Image.SCALE_FAST));
        iicon[1]=new ImageIcon(new ImageIcon("angel.png").getImage().getScaledInstance(150, 200, Image.SCALE_FAST));
        btn[0]=new JButton(iicon[0]);
        btn[1]=new JButton(iicon[1]);
        btn[0].setSize(150,200);
        btn[1].setSize(150,200);
        add(btn[0],BorderLayout.WEST);
        add(btn[1],BorderLayout.EAST);
        actor=new Reactor(btn[0],btn[1]);
        capture=new LevelMeter(actor,0.01,3);
		setSize(300,200);
		setVisible(true);
	}
	public static void main(String args[]) {
		new ExGame();
		
		}
	class Reactor implements SoundHandler{
	    JButton me,enemy;
	    public Reactor(JButton me,JButton enemy) {
	        this.me=me;
	        this.enemy=enemy;
	        
	    }
	    @Override
	    public void action(double now,double peak) {
	        if(now>50) {
	            me.setVisible(false);
	            enemy.setVisible(true);
	            System.out.println("switched to enemy");
	        }else {
	            me.setVisible(true);
	            enemy.setVisible(false);
	            System.out.println("switched to main");
	        }
	            
	    }
	    
	}
}
