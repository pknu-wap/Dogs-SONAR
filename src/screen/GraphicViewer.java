package screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class GraphicViewer extends Canvas implements Runnable,MouseListener{
    BufferedImage bf;
    int frameRate=50;
    TreeMap<String,GraphicComponent> buffer;
    boolean firstPainted=false;
    Thread thr;
    public GraphicViewer(int width,int height,int rate) {
        frameRate=rate;
        this.addMouseListener(this);
        this.setSize(width,height);
        this.setBackground(Color.white);
        buffer=new TreeMap<String,GraphicComponent>();
        new Thread(new GCControl()).start();
    }
    public GraphicViewer(int rate) {
        frameRate=rate;
        this.addMouseListener(this);
        this.setSize(100,100);
        this.setBackground(Color.white);
        buffer=new TreeMap<String,GraphicComponent>();
    }

    public void addComponent(GraphicComponent btn,String name) {
        buffer.put(name, btn);
    }
    public GraphicComponent getComponentByName(String name) {
        return buffer.get(name);
    }
    public void deleteComponent(String name) {
        buffer.remove(name);
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
        for (Entry<String, GraphicComponent> entry : buffer.entrySet()) {
            String key   = entry.getKey();
            GraphicComponent value =  entry.getValue();
            if(value.isEffect()&&((Effect)value).getLength()<=((Effect)value).getIndex()) {
                deleteComponent(key);
                continue;
            }
            BufferedImage k=value.getImg();
            if(value.isAnimated()) {
                ((Animated)value).nextFrame();   
            }
            if(k!=null) {
                g2d.setComposite(AlphaComposite.SrcOver.derive(value.getAlpha()));
                g2d.drawImage(k,value.getX(),value.getY(),value.getWidth(),value.getHeight(),this);
                g2d.setFont(value.getFont());
                g2d.setColor(value.getTextColor());
                g2d.drawString(value.getText(),(int) (value.getX()+0.76*value.getFont().getSize()/2),(int) (value.getY()+value.getHeight()/2+0.76*value.getFont().getSize()/2));
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
                Thread.sleep(frameRate);
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
        for (Entry<String, GraphicComponent> entry : buffer.entrySet()) {
            String key   = entry.getKey();
            GraphicComponent value =  entry.getValue();
            if(value.isClickable()) {
                Button tmp=(Button)value;
                if((tmp.getX()<tX&&tX<tmp.getX()+tmp.getWidth()&&tmp.getY()<tY&&tY<tmp.getY()+tmp.getHeight()&&tmp.getImg().getRGB(tX-tmp.getX(),tY-tmp.getY())!=0)&&tmp.getAlpha()>0) {
                    System.out.println("coordinate : "+tX+" "+tY);
                    System.out.println("clicked Name : "+key);
                    System.out.printf("color(argb) : %x\n",tmp.getImg().getRGB(tX-tmp.getX(),tY-tmp.getY()));
                    tmp.action(arg0);
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {}

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
}









