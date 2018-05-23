package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class TESTUI {
	public static void main(String args[]) {//TODO main method
		GraphicViewer gV1=new GraphicViewer(100,500,500);
		gV1.setLocation(new Point(0,0));
		GraphicViewer gV2=new GraphicViewer(100,500,500);
		gV2.setLocation(new Point(100,100));
		ExampleJFrame frame=new ExampleJFrame();
		BufferedImg danos=new BufferedImg("danos.jpg",42, 176, 416, 229, "", new Font("Consolas",Font.BOLD,20), Color.white);
		ExampleButton garageDoor=new ExampleButton("inside.png", 42, 176, 416, 229,"",new Font("Consolas",Font.BOLD,20),Color.red);
		BufferedImg garageBack=new BufferedImg("outside.png", 0, 0, 500, 500, "", new Font("Consolas",Font.BOLD,20), Color.white);
		frame.add(gV1);
		gV1.addButton(danos);
		gV1.addButton(garageBack);
		gV1.addButton(garageDoor);
		frame.setVisible(true);
	}
}
class ExampleButton extends BufferedImg{
	public ExampleButton(String imgSrc, int locX, int locY, int sizeWidth, int sizeHeight, String content, Font font,
            Color textColor) {
        super(imgSrc, locX, locY, sizeWidth, sizeHeight, content, font, textColor);
        // TODO Auto-generated constructor stub
    }
    Random rand=new Random();
	boolean isOpened=false;
    @Override
	public void run() {
		int k=240;
		int v=50;
		if(!isOpened) {
			for(;k>0;k-=v) {
				this.setY(this.getY()-v);
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			isOpened=true;
		}else {
			for(;k>0;k-=v) {
				this.setY(this.getY()+v);
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
