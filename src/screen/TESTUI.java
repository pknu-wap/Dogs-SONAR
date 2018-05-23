package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

public class TESTUI {
	public static void main(String args[]) throws IOException {//TODO main method
		GraphicViewer gV1=new GraphicViewer(100,500,500);
		gV1.setLocation(new Point(0,0));
		ExampleJFrame frame=new ExampleJFrame();
		BufferedImg danos=new BufferedImg("danos.jpg",0, 0, 500, 500);
		ExampleButton garageDoor=new ExampleButton("inside.png", 42, 178, 420, 225,"Yess!",new Font("Consolas",Font.BOLD,100),Color.red);
		BufferedImg garageBack=new BufferedImg("outside.png", 0, 0, 500, 500);
		frame.add(gV1);
		gV1.addButton(danos);
		gV1.addButton(garageBack);
		gV1.addButton(garageDoor);
		frame.setVisible(true);
	}
}
class ExampleButton extends BufferedImg{
	public ExampleButton(String imgSrc, int locX, int locY, int sizeWidth, int sizeHeight, String content, Font font,
            Color textColor) throws IOException {
        super(imgSrc, locX, locY, sizeWidth, sizeHeight, content, font, textColor);
        // TODO Auto-generated constructor stub
    }
    Random rand=new Random();
	boolean isOpened=false;
    @Override
	public void run() {
		float k1=1f;
		float v1=0.05f;
		int v2=10;
		if(!isOpened) {
			for(;k1>0;k1-=v1) {
				this.setAlpha(this.getAlpha()-v1);
				this.setY(this.getY()-v2);
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			isOpened=true;
		}else {
			for(;k1>0;k1-=v1) {
				this.setAlpha(this.getAlpha()+v1);
				this.setY(this.getY()+v2);
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
