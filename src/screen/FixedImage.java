package screen;

import java.awt.Color;
import java.awt.Font;

public class FixedImage extends GraphicComponent{
    public FixedImage(String imgSrc) {
        setImg(imgSrc);
        setWidth(getImg().getWidth(null));
        setHeight(getImg().getHeight(null));
    }
    public FixedImage(String imgSrc,int locX,int locY,int sizeWidth,int sizeHeight,String content){
        this(imgSrc,locX,locY,sizeWidth,sizeHeight);
        setText(content);
        setImg(imgSrc);
    }
    public FixedImage(String imgSrc,int locX,int locY,int sizeWidth,int sizeHeight){
        this(imgSrc,locX,locY);
        setWidth(sizeWidth);
        setHeight(sizeHeight);
        setImg(imgSrc);
    }
    public FixedImage(String imgSrc,int locX,int locY){
        this(imgSrc);
        setX(locX);
        setY(locY);   
    }
}
