package soundCapture;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class TestUI extends JFrame implements Runnable,soundHandler{
	LevelMeter v;
	JPanel background;
	JPanel[] control;
	JPanel standard;
	Thread t;
	public static void main(String args[]) {
		TestUI t=new TestUI();
	}
	
	public TestUI() {
		t=new Thread(this);
		v=new LevelMeter(this,0.1,3);
		standard=new JPanel();
		standard.setSize(2000,2);
		standard.setBackground(Color.black);
		standard.setLocation(0,502);
		
		this.setSize(1600, 600);
		background=new JPanel();
		control=new JPanel[1];
		this.setBackground(Color.white);
		background.setLocation(0,0);
		background.setLayout(null);
		background.add(standard);
		background.setSize(1600,500);
		background.setBackground(new Color(60,60,60));
		for(int i=0;i<control.length;i++) {
		    control[i]=new JPanel();
	        control[i].setSize(40,2);
	        control[i].setBackground(new Color(186,85,211));
	        background.add(control[i]);   
		}
		this.add(background);
		t.start();
		setVisible(true);
		
	}
	public void p() {
	    
	}
	@Override
	public void run() {
		while(true) {
		    try {
		        Thread.sleep(50);
		        for(int i=0;i<control.length;i++) {
		            control[i].setLocation(4*i, 500-(int)(1000*v.now));
		        }
		    }catch(Exception e) {}
		       
		}
	}

    @Override
    public void action(double[] pack) {
        System.out.println("Event generated average : "+(int)pack[0]+",peak : "+(int)pack[1]);
    }
	
}
