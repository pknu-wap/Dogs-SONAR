package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class GraphicViewer extends Canvas implements Runnable,MouseListener{
	int size;
	BufferedImage bf;
	BufferedImg[] imgBuffer;
	BufferedButton[] btnBuffer;
	boolean[] isImgBuf;
	boolean[] isBtnBuf;
	boolean firstPainted=false;
	Thread thr;
	public GraphicViewer(int bufferSize) {
		this.addMouseListener(this);
		this.size=bufferSize;
		this.setSize(1000,1000);
		this.setBackground(Color.white);
		imgBuffer=new BufferedImg[size];
		btnBuffer=new BufferedButton[size];
		isImgBuf=new boolean[size];
		isBtnBuf=new boolean[size];
		for(int i=0;i<size;i++) {
			isImgBuf[i]=false;
			isBtnBuf[i]=false;
		}
	}

	public int addButton(BufferedButton btn) {
		for(int i=0;i<size;i++) {
			if(!isBtnBuf[i]) {
				btnBuffer[i]=btn;
				isBtnBuf[i]=true;
				btn.setId(i);
				return i;
			}
		}
		return -1;
	}
	public BufferedButton getButtonById(int index) {
		return btnBuffer[index];
	}
	public void deleteButton(int index) {
		btnBuffer[index]=null;
		isBtnBuf[index]=false;
	}


	public int addImage(BufferedImg img) {
		for(int i=0;i<size;i++) {
			if(!isImgBuf[i]) {
				imgBuffer[i]=img;
				isImgBuf[i]=true;
				img.setId(i);
				return i;
			}
		}
		return -1;
	}
	public BufferedImg getImgById(int index) {
		return imgBuffer[index];
	}
	public void deleteImage(int index) {
		imgBuffer[index]=null;
		isImgBuf[index]=false;
	}
	public void paint(Graphics g) {
		if(!firstPainted) {
			firstPainted=true;
			thr=new Thread(this);
			thr.start();
		}
		bf= new BufferedImage( this.getWidth(), this.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d=(Graphics2D) bf.getGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i=0;i<size;i++) {
			if(isImgBuf[i]) {
				g2d.drawImage(imgBuffer[i].getImg(),imgBuffer[i].getX(),imgBuffer[i].getY(),imgBuffer[i].getWidth(),imgBuffer[i].getHeight(),this);
			}
		}
		for(int i=0;i<size;i++) {
			if(isBtnBuf[i]) {
				g2d.drawImage(btnBuffer[i].getBoxData().getImg(),btnBuffer[i].getBoxData().getX(),btnBuffer[i].getBoxData().getY(),btnBuffer[i].getBoxData().getWidth(),btnBuffer[i].getBoxData().getHeight(),this);
				g2d.setFont(btnBuffer[i].getFont());
				g2d.setColor(btnBuffer[i].getTextColor());
				g2d.drawString(btnBuffer[i].getText(),(int) (btnBuffer[i].getBoxData().getX()+0.76*btnBuffer[i].getFont().getSize()/2),(int) (btnBuffer[i].getBoxData().getY()+btnBuffer[i].getBoxData().getHeight()/2+0.76*btnBuffer[i].getFont().getSize()/2));

			}	
		}
		
		g.drawImage(bf,0,0,null);
		
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(40);
				paint(this.getGraphics());	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		int tX=arg0.getX();
		int tY=arg0.getY();
		for(int i=0;i<size;i++) {
			if(isBtnBuf[i]) {
				BufferedImg tmp=btnBuffer[i].getBoxData();
				if(tmp.getX()<tX&&tX<tmp.getX()+tmp.getWidth()&&tmp.getY()<tY&&tY<tmp.getY()+tmp.getHeight()) {
					btnBuffer[i].action();
				}
			}
		}
		System.out.println(tX+" "+tY);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {


	}
}

class BufferedButton implements Runnable{
	BufferedImg boxData;
	String text;
	Font font;
	int Id=-1;
	Color textColor;
	Thread thr;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id=id;
	}
	public BufferedImg getBoxData() {
		return boxData;
	}
	public void setBoxData(BufferedImg boxData) {
		this.boxData = boxData;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	public BufferedButton(BufferedImg btnImg,String content,Font font,Color textColor) {
		boxData=btnImg;
		setText(content);
		setFont(font);
		setTextColor(textColor);	
	}
	public void action() {
		thr=new Thread(this);
		thr.start();
	}
	@Override
	
	public void run() {
		
	}
}

class BufferedImg{
	private int x,y,width,height;
	private Image img;
	private int Id=-1;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id=id;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}


	public BufferedImg(String imgSrc,int locX,int locY,int sizeWidth,int sizeHeight) {
		setImg(new ImageIcon(imgSrc).getImage());
		setX(locX);
		setY(locY);
		setWidth(sizeWidth);
		setHeight(sizeHeight);
	}
}


