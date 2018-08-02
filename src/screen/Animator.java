package screen;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
interface Animated {
    public void nextFrame();
}
public class Animator extends GraphicComponent implements Animated{
    TreeMap<String,Animation> anim;
    TreeMap<String,String> next;
    String animInd,rear;
    int frameInd=0;
    public Animator(int width,int height) {
        setWidth(width);
        setHeight(height);
        anim=new TreeMap<String,Animation>();
        next=new TreeMap<String,String>();
        setAnimated(true);
    }
    public void addAnimation(Animation animation,String name) {
        anim.put(name,animation);
        next.put(name,name);
        animInd=name;
    }
    void syncSize() {
        this.setWidth(anim.get(animInd).getWidth());
        this.setHeight(anim.get(animInd).getHeight());
    }
    public void setNowAnim(String name) {
        animInd=name;
        this.frameInd=0;
        syncSize();
    }
    public void setNext(String target,String next) {
        this.next.remove(target);
        this.next.put(target,next);
    }
    public BufferedImage getImg() {
        BufferedImage t = anim.get(animInd).getFrame(frameInd);
        return t;
    }
    public void nextFrame() {
        frameInd+=1;
        if(frameInd==anim.get(animInd).getLength()) {
            animInd=next.get(animInd);
            frameInd=0;
            syncSize();
        }
    }
}
