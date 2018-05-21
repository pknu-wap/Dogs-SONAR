package soundCapture;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class TestUI extends JFrame implements Runnable,soundHandler{
    LevelMeter v;
    JPanel background;
    JPanel control;
    JPanel standard;
    Thread t;
    public static void main(String args[]) {
        TestUI t=new TestUI();
    }

    public TestUI() {
        t=new Thread(this);
        v=new LevelMeter(this,0.1,3);
        standard=new JPanel();
        standard.setSize(40,2);
        standard.setBackground(Color.black);
        standard.setLocation(0,502);
        this.setSize(40, 600);
        background=new JPanel();
        this.setBackground(Color.white);
        background.setLocation(0,0);
        background.setLayout(null);
        background.add(standard);
        background.setSize(1600,500);
        background.setBackground(new Color(60,60,60));
        control=new JPanel();
        control.setSize(40,20);
        control.setBackground(new Color(186,85,211));
        background.add(control);   
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
                control.setLocation(0, 500-(int)(1000*v.getNow()));
            }catch(Exception e) {}

        }
    }

    @Override
    public void action(double[] pack) {
        System.out.println("Event generated average : "+(int)pack[0]+",peak : "+(int)pack[1]);
    }

}
