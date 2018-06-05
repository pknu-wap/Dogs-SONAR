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
    BufferedImg[] imgBuffer;
    Effect[] effects;
    int[] effectCheck;
    boolean[] imgCheck;
    boolean firstPainted=false;
    Thread thr;
    public GraphicViewer(int bufferSize,int width,int height) {
        this.addMouseListener(this);
        this.size=bufferSize;
        this.setSize(width,height);
        this.setBackground(Color.white);
        imgBuffer=new BufferedImg[size];
        effects=new Effect[size];
        imgCheck=new boolean[size];
        effectCheck=new int[size];
        for(int i=0;i<size;i++) {
            imgCheck[i]=false;
            effectCheck[i]=-1;
        }
        new Thread(new GCControl()).start();
    }
    public GraphicViewer(int bufferSize) {
        this.addMouseListener(this);
        this.size=bufferSize;
        this.setSize(100,100);
        this.setBackground(Color.white);
        imgBuffer=new BufferedImg[size];
        effects=new Effect[size];
        imgCheck=new boolean[size];
        effectCheck=new int[size];
        for(int i=0;i<size;i++) {
            imgCheck[i]=false;
            effectCheck[i]=-1;
        }
    }
    public void addEffect(Effect effect) {
        for(int i=0;i<size;i++) {
            if(effectCheck[i]!=-1) {
                continue;
            }else {
                effects[i]=effect;
                effectCheck[i]=effect.getLength()-1;
                return;
            }
        }
    }
    public int addButton(BufferedImg btn) {
        for(int i=0;i<size;i++) {
            if(!imgCheck[i]) {
                imgBuffer[i]=btn;
                imgCheck[i]=true;
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
        imgCheck[index]=false;
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
            if(imgCheck[i]) {
                g2d.setComposite(AlphaComposite.SrcOver.derive(imgBuffer[i].getAlpha()));
                g2d.drawImage(imgBuffer[i].getImg(),imgBuffer[i].getX(),imgBuffer[i].getY(),imgBuffer[i].getWidth(),imgBuffer[i].getHeight(),this);
                g2d.setFont(imgBuffer[i].getFont());
                g2d.setColor(imgBuffer[i].getTextColor());
                g2d.drawString(imgBuffer[i].getText(),(int) (imgBuffer[i].getX()+0.76*imgBuffer[i].getFont().getSize()/2),(int) (imgBuffer[i].getY()+imgBuffer[i].getHeight()/2+0.76*imgBuffer[i].getFont().getSize()/2));
            }
        }
        for(int i=0;i<size;i++) {
            if(effectCheck[i]>-1) {
                g2d.drawImage(effects[i].getFrame(effects[i].getLength()-effectCheck[i]-1), effects[i].getX(),effects[i].getY() , effects[i].frames.getWidth(), effects[i].frames.getHeight(),this);
                effectCheck[i]-=1;
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
            if(imgCheck[i]) {
                if((imgBuffer[i].getX()<tX&&tX<imgBuffer[i].getX()+imgBuffer[i].getWidth()&&imgBuffer[i].getY()<tY&&tY<imgBuffer[i].getY()+imgBuffer[i].getHeight()&&imgBuffer[i].getImg().getRGB(tX-imgBuffer[i].getX(),tY-imgBuffer[i].getY())!=0)&&imgBuffer[i].getAlpha()>0) {
                    System.out.println("coordinate : "+tX+" "+tY);
                    System.out.println("clicked ID : "+i);
                    System.out.printf("color(argb) : %x\n",imgBuffer[i].getImg().getRGB(tX-imgBuffer[i].getX(),tY-imgBuffer[i].getY()));
                    imgBuffer[i].action(arg0);
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








