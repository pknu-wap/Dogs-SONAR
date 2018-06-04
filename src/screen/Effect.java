package screen;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class Effect {
    BufferedImage[] frames;
    int x,y,width,height,length,id;
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getX() {
        return x;
    }
    public BufferedImage getFrame(int ind) {
        return frames[ind];
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
    public Effect(String src,int locX,int locY,int width,int height) {
        setX(locX);
        setY(locY);
        setWidth(width);
        setHeight(height);
        length=new File(src).list().length;
        frames=new BufferedImage[length];
        synchronized(this) {
            for(int i=0;i<length;i++) {
                setFrame(i,src+"\\Frame"+i+".png");
            }
        }
    }

    void setFrame(int ind,String src) {
        frames[ind]=new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D bGr = frames[ind].createGraphics();
        try {
            bGr.drawImage(ImageIO.read(new File(src)).getScaledInstance(width, height,Image.SCALE_SMOOTH), 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bGr.dispose();
    }
}