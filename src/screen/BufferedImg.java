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

class BufferedImg implements Actable{
    private int x,y,width,height;
    private BufferedImage img;
    private int Id=-1;
    private float alpha=1f;
    private String text="";
    private Font font=new Font("Consolas",Font.PLAIN,20);
    private Color textColor=Color.WHITE;
    private MouseEvent tmp;
    private boolean isRun=false;
    Thread thr;
    public float getAlpha() {
        return alpha;
    }
    public void setAlpha(float alpha) {
        if(alpha>1) {
            alpha=1f;
        }else if(alpha<0) {
            alpha=0f;
        }
        this.alpha = alpha;
    }
    public BufferedImg(String imgSrc) {
        setX(0);
        setY(0);
        setWidth(getImg().getWidth(null));
        setHeight(getImg().getHeight(null));
        setImg(imgSrc);
    }
    public BufferedImg(String imgSrc,int locX,int locY,int sizeWidth,int sizeHeight,String content,Font font,Color textColor){
        setX(locX);
        setY(locY);
        setWidth(sizeWidth);
        setHeight(sizeHeight);
        setText(content);
        setFont(font);
        setTextColor(textColor);
        setImg(imgSrc);
    }
    public BufferedImg(String imgSrc,int locX,int locY,int sizeWidth,int sizeHeight){
        setX(locX);
        setY(locY);
        setWidth(sizeWidth);
        setHeight(sizeHeight);
        setImg(imgSrc);
    }
    public BufferedImg(String imgSrc,int locX,int locY){
        setX(locX);
        setY(locY);
        setWidth(getImg().getWidth(null));
        setHeight(getImg().getHeight(null));
        setImg(imgSrc);
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Font getFont() {
        return font;
    }
    public void setFont(Font font) {
        this.font = font;
    }
    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id=id;
    }
    public int getX() {
        return x;
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

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(String imgSrc){
        this.img=new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D bGr = img.createGraphics();
        try {
            bGr.drawImage(ImageIO.read(new File(imgSrc)).getScaledInstance(width, height,Image.SCALE_SMOOTH), 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bGr.dispose();
    }



    public void action(MouseEvent e){
        if(!isRun) {
            tmp=e;
            thr=new Thread(this);
            thr.start();
        }
    }
    @Override

    public void run() {
        this.isRun=true;
        act(tmp);
        this.isRun=false;
    }
    public void act(MouseEvent e) {

    }
} 