package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class GraphicViewer extends Canvas implements Runnable{
    Graphics2D g2d;
    int size;
    BufferedImage[] buffer;
    boolean[] isBufferEmpty;
    Thread thr;
    public GraphicViewer(int bufferSize) {
        this.size=bufferSize;
        this.setSize(1000,1000);
        this.setBackground(Color.blue);
        buffer=new BufferedImage[size];
        isBufferEmpty=new boolean[size];
        for(int i=0;i<size;i++) {
            isBufferEmpty[i]=false;
        }
    }
    public int addImage(BufferedImage img) {
        for(int i=0;i<size;i++) {
            if(!isBufferEmpty[i]) {
                buffer[i]=img;
                isBufferEmpty[i]=true;
                return i;
            }
        }
        return -1;
    }
    public void changeImage(int index,BufferedImage img) {
        buffer[index]=img;
    }
    public void deleteImage(int index) {
        buffer[index]=null;
        isBufferEmpty[index]=false;
    }
    
    public void paint(Graphics g) {
        g2d=(Graphics2D) g;
        for(int i=0;i<size;i++) {
            if(isBufferEmpty[i]) {
                g2d.drawImage(buffer[i].getImg(),buffer[i].getX(),buffer[i].getY(),buffer[i].getWidth(),buffer[i].getHeight(),this);
            }
        }
        
    }
    public static void main(String args[]) {
        GraphicViewer l=new GraphicViewer(100);
        ExampleJFrame k=new ExampleJFrame();
        k.add(l);
        k.setVisible(true);
    }
    @Override
    public void run() {
        
        
    }
}
class ExampleJFrame extends JFrame{
    public ExampleJFrame() {
        this.setSize(1000,1000);
    }
   
}
class BufferedImage{
    private int x,y,width,height;
    private Image img;
    
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

    public BufferedImage(Image content,int locX,int locY,int sizeWidth,int sizeHeight) {
        setImg(content);
        setX(locX);
        setY(locY);
        setWidth(sizeWidth);
        setHeight(sizeHeight);
    }
    
    
    
}