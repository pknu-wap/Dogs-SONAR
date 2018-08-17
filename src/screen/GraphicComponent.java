package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class GraphicComponent{
    private float alpha=1f;
    private String text="";
    private Font font=new Font("맑은 고딕",Font.PLAIN,20);
    private Color textColor=Color.WHITE;
    private int id=-1,x=0,y=0,width=10,height=10;
    private BufferedImage img;
    private boolean isClickable=false;
    private boolean isAnimated=false;
    private boolean isEffect=false;
    private boolean willDestroy=false;
    private boolean isVisible=true;
    private boolean tooltipable=false;
    private String description="";
    private Tooltip tooltip=null;
    public void drawString(Graphics2D g2d) {
        g2d.setFont(getFont());
        g2d.setColor(getTextColor());
        g2d.drawString(this.getText(),(int) (this.getX()+this.getWidth()/2.0-this.getStringSize(g2d,this.getText())/2.0),(int) (this.getY()+this.getHeight()/2+0.76*this.getFont().getSize()/2));
    }
    public void drawImage(Graphics2D g2d,GraphicViewer canvas) {
        if(getImg()!=null) g2d.drawImage(getImg(),getX(),getY(),getWidth(),getHeight(),canvas);
    }
    public boolean isTooltipable() {
        return tooltipable;
    }
    public void setTooltip(Tooltip tooltip) {
        this.tooltip=tooltip;
        setTooltipable(true);
    }
    public Tooltip getTooltip() {
        return tooltip;
    }
    public void setTooltipable(boolean tooltipable) {
        this.tooltipable = tooltipable;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isVisible() {
        return isVisible;
    }
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    public boolean isDestroy() {
    	return willDestroy;
    }
    public void dispose() {
    	willDestroy=true;
    }
    public boolean isEffect() {
        return isEffect;
    }
    public void setEffect(boolean isEffect) {
        this.isEffect = isEffect;
    }
    public boolean isAnimated() {
        return isAnimated;
    }
    public void setAnimated(boolean isAnimated) {
        this.isAnimated = isAnimated;
    }
    public boolean isClickable() {
        return isClickable;
    }
    public void setClickable(boolean isClickable) {
        this.isClickable = isClickable;
    }
    public int getStringSize(Graphics2D g,String text) {
        return g.getFontMetrics(g.getFont()).stringWidth(text);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
        String src;
        if(imgSrc==null) {
            src="sprites\\Debug\\transparent.png";
        }else {
            src=imgSrc;
        }
        this.img=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D bGr = getImg().createGraphics();
        try {
            bGr.drawImage(ImageIO.read(new File(src)).getScaledInstance(getWidth(), getHeight(),Image.SCALE_SMOOTH), 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bGr.dispose();
    }
    public void setImg(BufferedImage target) {
        this.img=target;
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
    public float getAlpha() {
        return alpha;
    }
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
    public void setAlpha(float alpha) {
        if(alpha>1) alpha=1f;
        else if(alpha<0) alpha=0f;
        this.alpha = alpha;
    }
}
class FontCalculator extends FontMetrics{
    protected FontCalculator(Font font) {
        super(font);
    }
    
}
