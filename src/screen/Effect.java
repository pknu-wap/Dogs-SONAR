package screen;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Effect extends GraphicComponent {
    Animation frames;
    int index=0;
    public Effect(String src,int locX,int locY,int width,int height) {
        setX(locX);
        setY(locY);
        setWidth(width);
        setHeight(height);
        frames=new Animation(src,width,height);
    }
    public Effect(Animation effect,int locX,int locY) {
        this.frames=effect;
        setWidth(frames.getWidth());
        setHeight(frames.getHeight());
        setX(locX);
        setY(locY);
    }
    public int getLength() {
        return frames.length;
    }
    
    public BufferedImage getImg() {
        BufferedImage t=frames.getFrame(index);
        index+=1;
        return t;
    }
}

