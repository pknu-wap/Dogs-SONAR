package screen;

import java.awt.image.BufferedImage;

public class Effect extends GraphicComponent implements Animated{
    Animation frames;
    int index=0;
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
        if(index==frames.length)dispose();
    }
}

