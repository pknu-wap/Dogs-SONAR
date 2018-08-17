package screen;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Tooltip extends GraphicComponent{
    int offsetX,offsetY;
    Color bgColor,foreColor;
    boolean initiated=true;
    ArrayList<String> texts=new ArrayList<String>();
    public int getOffsetX() {
        return offsetX;
    }
    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }
    public int getOffsetY() {
        return offsetY;
    }
    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
    public Tooltip(String text,int offsetX,int offsetY,Color bgColor,Color foreColor,int size) {
        this.bgColor=bgColor;
        this.foreColor=foreColor;
        setVisible(true);
        setClickable(false);
        setTooltipable(false);
        setTextColor(foreColor);
        setFont(new Font("맑은 고딕",Font.PLAIN,size));
        setOffsetX(offsetX);
        setOffsetY(offsetY);
        setAlpha(0.8f);
        for(String line : text.split("\n")) texts.add(line);
    }
    public void setSize(Graphics2D g2d) {
        int count=1;
        int tWidth=0;
        for (String line : texts) {
            count++;
            Canvas c=new Canvas();
            c.setFont(getFont());
            int t=this.getStringSize(g2d,line);
            if(tWidth<t) tWidth=t;
        }
        setWidth(tWidth);
        setHeight(10+getFont().getSize()*count);
    }
    @Override
    public void drawString(Graphics2D g2d) {
        g2d.setColor(foreColor);
        g2d.setFont(getFont());
        int x=getX()+getOffsetX()+5,y=getY()+getOffsetY()+5;
        for(String line:texts) {
            g2d.drawString(line, x, y += g2d.getFontMetrics().getHeight());
        }
    }
    @Override
    public void drawImage(Graphics2D g2d,GraphicViewer canvas) {
        if(initiated) {
            setSize(g2d);
            initiated=false;
        }
        g2d.setColor(bgColor);
        g2d.fillRect(getX()+getOffsetX(),getY()+getOffsetY(),getWidth(),getHeight());
    }
}
