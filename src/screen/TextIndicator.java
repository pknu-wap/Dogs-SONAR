package screen;

import java.awt.Color;
import java.awt.Font;


public class TextIndicator extends GraphicComponent{
    int time;
    public static int counter=0;
    public TextIndicator(String src,int size,Color color,int locX,int locY,int time) {
        System.out.println(src + locX+ " / "+locY);
        this.setImg((String)null);
        this.setX(locX);
        this.setY(locY);
        this.time=time;
        this.setTextColor(color);
        this.setFont(new Font("consolas",Font.PLAIN,size));
        this.setText(src);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<TextIndicator.this.time;i++) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    TextIndicator.this.setY(TextIndicator.this.getY()-2);
                    //TextIndicator.this.setAlpha(TextIndicator.this.getAlpha()*0.9f);
                }
                TextIndicator.this.dispose();
            }
        }).start();
    }
}
