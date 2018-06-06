package screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicViewer extends Canvas implements Runnable,MouseListener{
    int size;
    BufferedImage bf;
    GraphicComponent[] buffer;
    boolean[] check;
    boolean firstPainted=false;
    Thread thr;
    public GraphicViewer(int bufferSize,int width,int height) {
        this.addMouseListener(this);
        this.size=bufferSize;
        this.setSize(width,height);
        this.setBackground(Color.white);
        buffer=new GraphicComponent[size];
        check=new boolean[size];
        for(int i=0;i<size;i++) {
            check[i]=false;
        }
        new Thread(new GCControl()).start();
    }
    public GraphicViewer(int bufferSize) {
        this.addMouseListener(this);
        this.size=bufferSize;
        this.setSize(100,100);
        this.setBackground(Color.white);
        buffer=new GraphicComponent[size];
        check=new boolean[size];
        for(int i=0;i<size;i++) {
            check[i]=false;
        }
    }
    
    public int addComponent(GraphicComponent btn) {
        for(int i=0;i<size;i++) {
            if(!check[i]) {
                buffer[i]=btn;
                check[i]=true;
                btn.setId(i);
                return i;
            }
        }
        return -1;
    }
    public GraphicComponent getComponentById(int index) {
        return buffer[index];
    }
    public void deleteComponent(int index) {
        buffer[index]=null;
        check[index]=false;
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
            if(check[i]) {
                BufferedImage k=buffer[i].getImg();
                if(k!=null)
                g2d.setComposite(AlphaComposite.SrcOver.derive(buffer[i].getAlpha()));
                g2d.drawImage(k,buffer[i].getX(),buffer[i].getY(),buffer[i].getWidth(),buffer[i].getHeight(),this);
                g2d.setFont(buffer[i].getFont());
                g2d.setColor(buffer[i].getTextColor());
                g2d.drawString(buffer[i].getText(),(int) (buffer[i].getX()+0.76*buffer[i].getFont().getSize()/2),(int) (buffer[i].getY()+buffer[i].getHeight()/2+0.76*buffer[i].getFont().getSize()/2));
            }
        }
        g2d.dispose();
        g.drawImage(bf,0,0,null);
        g.dispose();
        
    }
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(33);
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
        for(int i=size-1;i>=0;i--) {
            if(check[i]&&buffer[i].isClickable()) {
                Button tmp=(Button)buffer[i];
                if((tmp.getX()<tX&&tX<tmp.getX()+tmp.getWidth()&&tmp.getY()<tY&&tY<tmp.getY()+tmp.getHeight()&&tmp.getImg().getRGB(tX-tmp.getX(),tY-tmp.getY())!=0)&&tmp.getAlpha()>0) {
                    System.out.println("coordinate : "+tX+" "+tY);
                    System.out.println("clicked ID : "+i);
                    System.out.printf("color(argb) : %x\n",tmp.getImg().getRGB(tX-tmp.getX(),tY-tmp.getY()));
                    tmp.action(arg0);
                    break;
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {}
}
class GCControl implements Runnable{

    @Override
    public void run() {
        while(true) {
            try {
                System.gc();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}








