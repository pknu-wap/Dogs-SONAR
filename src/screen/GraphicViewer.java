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
	boolean[] isBtnBuf;
	boolean firstPainted=false;
	Thread thr;
	public GraphicViewer(int bufferSize,int width,int height) {
		this.addMouseListener(this);
		this.size=bufferSize;
		this.setSize(width,height);
		this.setBackground(Color.white);
		imgBuffer=new BufferedImg[size];
		isBtnBuf=new boolean[size];
		for(int i=0;i<size;i++) {
			isBtnBuf[i]=false;
		}
	}

	public int addButton(BufferedImg btn) {
		for(int i=0;i<size;i++) {
			if(!isBtnBuf[i]) {
				imgBuffer[i]=btn;
				isBtnBuf[i]=true;
				btn.setId(i);
				return i;
			}
		}
		return -1;
	}
	public BufferedImg getButtonById(int index) {
		return imgBuffer[index];
	}
	public void deleteButton(int index) {
		imgBuffer[index]=null;
		isBtnBuf[index]=false;
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
			if(isBtnBuf[i]) {
				g2d.drawImage(imgBuffer[i].getImg(),imgBuffer[i].getX(),imgBuffer[i].getY(),imgBuffer[i].getWidth(),imgBuffer[i].getHeight(),this);
				if(imgBuffer[i].getFont()!=null)
				g2d.setFont(imgBuffer[i].getFont());
				g2d.setColor(imgBuffer[i].getTextColor());
				g2d.drawString(imgBuffer[i].getText(),(int) (imgBuffer[i].getX()+0.76*imgBuffer[i].getFont().getSize()/2),(int) (imgBuffer[i].getY()+imgBuffer[i].getHeight()/2+0.76*imgBuffer[i].getFont().getSize()/2));

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
				if(imgBuffer[i].getX()<tX&&tX<imgBuffer[i].getX()+imgBuffer[i].getWidth()&&imgBuffer[i].getY()<tY&&tY<imgBuffer[i].getY()+imgBuffer[i].getHeight()) {
					imgBuffer[i].action();
				}
			}
		}
		System.out.println(tX+" "+tY);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {


	}
}

class BufferedImg implements Runnable{
	private int x,y,width,height;
	private Image img;
	private int Id=-1;
	String text="";
    Font font=new Font("Consolas",Font.PLAIN,20);
    Color textColor=Color.WHITE;
    Thread thr;
    public BufferedImg(String imgSrc,int locX,int locY,int sizeWidth,int sizeHeight,String content,Font font,Color textColor) {
        setImg(imgSrc);
        setX(locX);
        setY(locY);
        setWidth(sizeWidth);
        setHeight(sizeHeight);
        setText(content);
        setFont(font);
        setTextColor(textColor);
    }
    public BufferedImg(String imgSrc,int locX,int locY,int sizeWidth,int sizeHeight) {
        setImg(imgSrc);
        setX(locX);
        setY(locY);
        setWidth(sizeWidth);
        setHeight(sizeHeight);
    }
    public BufferedImg(String imgSrc,int locX,int locY) {
        setImg(imgSrc);
        setX(locX);
        setY(locY);
        setWidth(getImg().getWidth(null));
        setHeight(getImg().getHeight(null));
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

	public void setImg(String imgSrc) {
		this.img=new ImageIcon(imgSrc).getImage();
	}


	
	public void action() {
        thr=new Thread(this);
        thr.start();
    }
    @Override
    
    public void run() {
        
    }
}


