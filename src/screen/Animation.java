package screen;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation{
    BufferedImage[] frames;
    int width=0,height=0,length=0;
    public BufferedImage[] getFrames() {
        return frames;
    }
    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
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
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public String getSrc() {
        return src;
    }
    public void setSrc(String src) {
        this.src = src;
    }
    public int setWidth() {
        return width;
    }
    public int setHeight() {
        return height;
    }
    String src;
    public Animation(String src,int width,int height) {
        length=new File(src).list().length;
        frames=new BufferedImage[length];
        this.width=width;
        this.height=height;
        this.src=src;
        for(int i=0;i<length;i++) {
            setFrame(i,src);
        }
    }
    public BufferedImage getFrame(int ind) {
        if(ind>=length||ind<0)return null;
        else return frames[ind];
    }
    public void setFrame(int ind, String src) {
        synchronized(this) {
            for(int i=0;i<length;i++) {
                BufferedImage tmp=new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D bGr = tmp.createGraphics();
                try {
                    bGr.drawImage(ImageIO.read(new File(src+"\\Frame"+i+".png")).getScaledInstance(width, height,Image.SCALE_SMOOTH), 0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                frames[i]=tmp;
                bGr.dispose();
            }
        }
    }
    
}