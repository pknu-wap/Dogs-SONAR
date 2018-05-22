package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class TESTUI {
	public static void main(String args[]) {//TODO main method
		GraphicViewer gV=new GraphicViewer(100);
		ExampleJFrame frame=new ExampleJFrame();
		BufferedImg danos=new BufferedImg("danos.jpg",42, 176, 416, 229);
		ExampleButton garageDoor=new ExampleButton(new BufferedImg("inside.png", 42, 176, 416, 229),"",new Font("Consolas",Font.BOLD,20),Color.red);
		BufferedImg garageBack=new BufferedImg("outside.png", 0, 0, 500, 500);
		frame.add(gV);
		gV.addImage(danos);
		gV.addImage(garageBack);
		gV.addButton(garageDoor);
		frame.setVisible(true);
	}
}
class ExampleButton extends BufferedButton{
	Random rand=new Random();
	boolean isOpened=false;
	public ExampleButton(BufferedImg btnImg, String content, Font font,Color textColor) {
		super(btnImg, content, font,textColor);

	}
	@Override
	public void run() {
		int k=200;
		int v=50;
		if(!isOpened) {
			for(;k>0;k-=v) {
				this.boxData.setY(this.boxData.getY()-v);
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			isOpened=true;
		}else {
			for(;k>0;k-=v) {
				this.boxData.setY(this.boxData.getY()+v);
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			isOpened=false;
		}
	}
}
class ExampleJFrame extends JFrame{
	public ExampleJFrame() {
		this.setTitle("custom canvas test");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(510,540);
	}
}
