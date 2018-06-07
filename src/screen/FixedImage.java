package screen;

import java.awt.Color;
import java.awt.Font;

public class FixedImage extends GraphicComponent{
    public FixedImage(String imgSrc) {
        setImg(imgSrc);
        setWidth(getImg().getWidth(null));
        setHeight(getImg().getHeight(null));
    }
    public FixedImage(String imgSrc,int locX,int locY,int sizeWidth,int sizeHeight,String content,Font font,Color textColor){
        setX(locX);
        setY(locY);
        setWidth(sizeWidth);
        setHeight(sizeHeight);
        setText(content);
        setFont(font);
        setTextColor(textColor);
        setImg(imgSrc);
    }
    public FixedImage(String imgSrc,int locX,int locY,int sizeWidth,int sizeHeight){
        setX(locX);
        setY(locY);
        setWidth(sizeWidth);
        setHeight(sizeHeight);
        setImg(imgSrc);
    }
    public FixedImage(String imgSrc,int locX,int locY){
        setX(locX);
        setY(locY);
        setWidth(getImg().getWidth(null));
        setHeight(getImg().getHeight(null));
        setImg(imgSrc);
    }
}
