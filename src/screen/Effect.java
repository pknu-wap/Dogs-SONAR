package screen;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Effect extends GraphicComponent implements Animated{
    Animation frames;
    int index=0;
    public boolean isEnd() {
        if(frames.length==index)return true;
        else return false;
    }
    public Effect(String src,int locX,int locY,int width,int height) {
        setX(locX);
        setY(locY);
        setWidth(width);
        setHeight(height);
        frames=new Animation(src,width,height);
        setAnimated(true);
        setEffect(true);
    }
    public Effect(Animation effect,int locX,int locY) {
        this.frames=effect;
        setWidth(frames.getWidth());
        setHeight(frames.getHeight());
        setX(locX);
        setY(locY);
        setAnimated(true);
        setEffect(true);
    }
    public int getLength() {
        return frames.length;
    }
    public int getIndex() {
        return index;
    }
    public BufferedImage getImg() {
        BufferedImage t=frames.getFrame(index);
        return t;
    }
    @Override
    public void nextFrame() {
        index+=1;
    }
}

