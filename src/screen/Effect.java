package screen;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
class LoadedEffect{
    BufferedImage[] frames;
    int width,height,length;
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    String src;
    public LoadedEffect(String src,int width,int height) {
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
        return frames[ind];
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
class Effect {
    LoadedEffect frames;
    int x,y,id;
    public int getLength() {
        return frames.length;
    }
    public int getX() {
        return x;
    }
    public BufferedImage getFrame(int ind) {
        return frames.getFrame(ind);
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
    public Effect(String src,int locX,int locY,int width,int height) {
        setX(locX);
        setY(locY);
        frames=new LoadedEffect(src,width,height);
    }
    public Effect(LoadedEffect effect,int locX,int locY) {
        this.frames=effect;
        setX(locX);
        setY(locY);
    }
}