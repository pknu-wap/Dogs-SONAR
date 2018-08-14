package screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Map;

public class GraphicViewer extends Canvas implements Runnable,MouseListener,MouseMotionListener{
    BufferedImage bf;
    int frameRate=50;
    LinkedHashMap<String,GraphicComponent> buffer;
    LinkedHashMap<String,GraphicComponent> queue;
    boolean firstPainted=false;
    Thread thr;
    public GraphicViewer(int width,int height,int rate) {
        frameRate=rate;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setSize(width,height);
        this.setBackground(Color.white);
        buffer=new LinkedHashMap<String,GraphicComponent>();
        queue=new LinkedHashMap<String,GraphicComponent>();
        new Thread(new GCControl()).start();
    }
    public GraphicViewer(int rate) {
        frameRate=rate;
        this.addMouseListener(this);
        this.setSize(100,100);
        this.setBackground(Color.white);
        buffer=new LinkedHashMap<String,GraphicComponent>();
    }

    public void addComponent(GraphicComponent btn,String name) {
        queue.put(name, btn);
        if(btn.isTooltipable()&&btn.getTooltip()!=null)
        queue.put(name+"_tooltip", btn.getTooltip());
        
        
    }
    public GraphicComponent getComponentByName(String name) {
        GraphicComponent ret=buffer.get(name);
        if(ret==null) {
            return queue.get(name);
        }else {
            return ret;
        }
    }
    public void deleteComponent(String name) {
        buffer.remove(name);
    }
    public synchronized void paint(Graphics g) {
        if(!firstPainted) {
            firstPainted=true;
            thr=new Thread(this);
            thr.start();
        }
        bf= new BufferedImage( this.getWidth(), this.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d=(Graphics2D) bf.getGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        buffer.putAll(queue);
        queue.clear();
        Iterator<String> keys = buffer.keySet().iterator(); 
        while( keys.hasNext() ){
            String key = keys.next();
            GraphicComponent value =  buffer.get(key);
            if(!value.isVisible())continue;
            BufferedImage k=value.getImg();
            if(value.isAnimated()) {
                ((Animated)value).nextFrame();   
            }
            if(value.isDestroy()) {
                keys.remove();
            }
            if(k!=null) {
                g2d.setComposite(AlphaComposite.SrcOver.derive(value.getAlpha()));
                value.drawImage(g2d,this);
                value.drawString(g2d);
                
            }
            if(key.contains("_tooltip")) {
                value.setVisible(false);
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
                if(this.getGraphics()==null)break;
                paint(this.getGraphics());	
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void remove(Window w) {
        w.removeAll();
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
        System.out.println("clicked : "+tX+" / "+tY);
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

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        int tX=e.getX();
        int tY=e.getY();
        for (Entry<String, GraphicComponent> entry : buffer.entrySet()) {
            String key   = entry.getKey();
            GraphicComponent value =  entry.getValue();
            if((value.getX()<tX&&tX<value.getX()+value.getWidth()&&value.getY()<tY&&tY<value.getY()+value.getHeight()&&value.getImg().getRGB(tX-value.getX(),tY-value.getY())!=0)&&value.getAlpha()>0) {
                if(value.isTooltipable()) {
                    hovered(e,value);
                }
                
                break;
            }
        }
        
    }
    public void hovered(MouseEvent e,GraphicComponent t){
        int tX=e.getX(),tY=e.getY();
        if(t.getTooltip()!=null) {
            Tooltip tooltip=t.getTooltip();
            tooltip.setVisible(true);
            tooltip.setX(e.getX());
            tooltip.setY(e.getY());
        }
    }
}









