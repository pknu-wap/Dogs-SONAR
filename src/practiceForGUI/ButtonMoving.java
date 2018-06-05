package practiceForGUI;

import javax.swing.JButton;
import javax.swing.JFrame;



class Frame extends JFrame {
	public Frame(){
		setSize(500,500);
		setVisible(true);
		setTitle("buttons");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}

public class ButtonMoving {

	
	public static void main(String[] args) throws InterruptedException {
		Frame f = new Frame();


		
		JButton bt1 = new JButton("1");
		JButton bt2 = new JButton("2");
		JButton bt3 = new JButton("3");
		JButton bt4 = new JButton("4");
		
		while(true)
		{
		int num = (int) (Math.random()*10+1);
		int speed = (int) (Math.random()*10+1);
			
		if(num==1)
		{for(int i=0; i<500; i++)
		{bt1.setBounds(i, 0, 100,100);
		f.add(bt1);
		Thread.sleep(speed);
		}}
		else if(num==2)
		{for(int i=0; i<500; i++)
		{bt2.setBounds(i, 100, 100,100);
		f.add(bt2);
		Thread.sleep(speed);
		}}
		else if(num==3)
		{for(int i=0; i<500; i++)
		{bt3.setBounds(i, 200, 100,100);
		f.add(bt3);
		Thread.sleep(speed);
		}}
		else if(num==4)
		{for(int i=0; i<500; i++)
		{bt4.setBounds(i, 300, 100,100);
		f.add(bt4);
		Thread.sleep(speed);
		}}}
		
		
	}


}
