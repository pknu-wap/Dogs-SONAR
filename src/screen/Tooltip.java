package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Tooltip extends GraphicComponent{
    int offsetX,offsetY;
    Color bgColor,foreColor;
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
        setText(text);
        setVisible(true);
        setClickable(false);
        setTooltipable(false);
        setTextColor(foreColor);
        setFont(new Font("맑은 고딕",Font.PLAIN,size));
        setOffsetX(offsetX);
        setOffsetY(offsetY);
        int count=1;
        for (String line : getText().split("\n"))count++;
        setHeight(10+getFont().getSize()*count);
        
    }
    @Override
    public void drawString(Graphics2D g2d) {
        g2d.setColor(foreColor);
        g2d.setFont(getFont());
        int x=getX()+getOffsetX()+5,y=getY()+getOffsetY()+5;
        //g2d.drawString(g2d,getText(), getX()+getOffsetX()+5, getY()+getOffsetY()+5);
        for (String line : getText().split("\n"))
            g2d.drawString(line, x, y += g2d.getFontMetrics().getHeight());
        
    }
    @Override
    public void drawImage(Graphics2D g2d,GraphicViewer canvas) {
        g2d.setColor(bgColor);
        g2d.fillRect(getX()+getOffsetX(),getY()+getOffsetY(),this.getStringSize(g2d),getHeight());
    }
}
