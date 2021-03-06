package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

interface Actable extends Runnable{
    public void act(MouseEvent e);
}
public class Button extends GraphicComponent implements Actable{
    private boolean isRun=false;
    Thread thr;
    private MouseEvent tmp;
    public Button(String imgSrc) {
        setImg(imgSrc);
        setWidth(getImg().getWidth(null));
        setHeight(getImg().getHeight(null));
        setClickable(true);
    }
    public Button(String imgSrc,int locX,int locY,int sizeWidth,int sizeHeight,String content){
        setX(locX);
        setY(locY);
        setWidth(sizeWidth);
        setHeight(sizeHeight);
        setText(content);
        setImg(imgSrc);
        setClickable(true);
    }
    public Button(String imgSrc,int locX,int locY,int sizeWidth,int sizeHeight){
        setX(locX);
        setY(locY);
        setWidth(sizeWidth);
        setHeight(sizeHeight);
        setImg(imgSrc);
        setClickable(true);
    }
    public Button(String imgSrc,int locX,int locY){
        setX(locX);
        setY(locY);
        setWidth(getImg().getWidth(null));
        setHeight(getImg().getHeight(null));
        setImg(imgSrc);
        setClickable(true);
    }
    public void action(MouseEvent e){
        if(!isRun) {
            isRun=true;
            tmp=e;
            thr=new Thread(this);
            thr.start();
            try {thr.join(100);}
            catch (InterruptedException e1) {}
            isRun=false;
        }
    }
    @Override
    public void run() {
        act(tmp);
    }
    public void act(MouseEvent e) {}
}