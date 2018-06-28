package screen;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animator extends GraphicComponent{
    String[] AnimName;
    Animation[] Anim;
    int[] next;
    int animInd=0,rear=0,frameInd=0;
    public Animator(int size,int width,int height) {
        setWidth(width);
        setHeight(height);
        next=new int[size];
        AnimName=new String[size];
        Anim=new Animation[size];
    }
    public void addAnimation(Animation animation,String name) {
        if(rear==Anim.length) {
            System.out.println("size overflowed. animation not added");
            return;
        }
        Anim[rear]=animation;
        AnimName[rear]=name;
        next[rear]=rear;
        rear+=1;
    }
    public void setNowAnim(String name) {
        animInd=getIndexByName(name);
        this.frameInd=0;
    }
    int getIndexByName(String name) {
        for(int i=0;i<rear;i++) {
            if(name.equals(AnimName[i])) return i;
        }
        return -1;
    }
    public void setNext(String target,String next) {
        this.next[getIndexByName(target)]=getIndexByName(next);
    }
    public synchronized BufferedImage getImg() {
        BufferedImage t = Anim[animInd].getFrame(frameInd);
        frameInd+=1;
        if(frameInd==Anim[animInd].getLength()) {
            animInd=next[animInd];
            frameInd=0;
        }
        ;
        return t;
    }
}
