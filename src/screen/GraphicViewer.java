package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Random;

public class GraphicViewer extends Canvas implements Runnable{
    Graphics2D g2d;
    int size;
    BufferedImage[] imgBuffer;
    BufferedButton[] btnBuffer;
    boolean[] isImgBuf;
    boolean[] isBtnBuf;
    Thread thr;
    BufferedButton tmp;
    public GraphicViewer(int bufferSize) {
        tmp=new BufferedButton(new BufferedImage(new ImageIcon("buttonSprite.png").getImage(), 100, 100, 200, 50),"ExampleButton",new Font("Consolas",Font.BOLD,20));
        this.size=bufferSize;
        this.setSize(1000,1000);
        this.setBackground(Color.white);
        imgBuffer=new BufferedImage[size];
        btnBuffer=new BufferedButton[size];
        isImgBuf=new boolean[size];
        isBtnBuf=new boolean[size];
        for(int i=0;i<size;i++) {
            isImgBuf[i]=false;
            isBtnBuf[i]=false;
        }
    }
    
    public int addButton(BufferedButton btn) {
        for(int i=0;i<size;i++) {
            if(!isBtnBuf[i]) {
                btnBuffer[i]=btn;
                isBtnBuf[i]=true;
                btn.setId(i);
                return i;
            }
        }
        return -1;
    }
    public BufferedButton getButtonById(int index) {
        return btnBuffer[index];
    }
    public void deleteButton(int index) {
        btnBuffer[index]=null;
        isBtnBuf[index]=false;
    }
    
    
    public int addImage(BufferedImage img) {
        for(int i=0;i<size;i++) {
            if(!isImgBuf[i]) {
                imgBuffer[i]=img;
                isImgBuf[i]=true;
                img.setId(i);
                return i;
            }
        }
        return -1;
    }
    public BufferedImage getImgById(int index) {
        return imgBuffer[index];
    }
    public void deleteImage(int index) {
        imgBuffer[index]=null;
        isImgBuf[index]=false;
    }
    
    public void paint(Graphics g) {
        g2d=(Graphics2D) g;
        for(int i=0;i<size;i++) {
            if(isImgBuf[i]) {
                g2d.drawImage(imgBuffer[i].getImg(),imgBuffer[i].getX(),imgBuffer[i].getY(),imgBuffer[i].getWidth(),imgBuffer[i].getHeight(),this);
            }
            if(isBtnBuf[i]) {
                g2d.drawImage(btnBuffer[i].getBoxData().getImg(),btnBuffer[i].getBoxData().getX(),btnBuffer[i].getBoxData().getY(),btnBuffer[i].getBoxData().getWidth(),btnBuffer[i].getBoxData().getHeight(),this);
                g2d.setFont(btnBuffer[i].getFont());
                Dimension d=btnBuffer[i].getTextPos();
                g2d.drawString(btnBuffer[i].getText(),(int)d.getWidth(),(int)d.getHeight());
                
            }
        }
    }
    public static void main(String args[]) {
        GraphicViewer l=new GraphicViewer(100);
        ExampleJFrame k=new ExampleJFrame();
        k.add(l);
        l.thr=new Thread(l);
        l.thr.start();
        l.addButton(l.tmp);
        k.setVisible(true);
    }
    @Override
    public void run() {
        Random rand=new Random();
        while(true) {
            try {
                Thread.sleep(33);
                synchronized(this) {
                    this.getButtonById(tmp.getId()).getBoxData().setX(rand.nextInt(1000));
                    this.getButtonById(tmp.getId()).getBoxData().setY(rand.nextInt(1000));
                    this.getButtonById(tmp.getId()).getBoxData().setWidth(rand.nextInt(1000));
                    this.getButtonById(tmp.getId()).getBoxData().setHeight(rand.nextInt(1000));
                    this.repaint();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        
    }
}
class ExampleJFrame extends JFrame{
    public ExampleJFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000,1000);
    }
   
}

class BufferedButton{
    private BufferedImage boxData;
    private String text;
    private Font font;
    private int Id;
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id=id;
    }
    public Dimension getTextPos() {
        Dimension ret=new Dimension(boxData.getX()+(boxData.getWidth()-font.getSize()*text.length())/2,boxData.getY()+(boxData.getHeight()-font.getSize())/2);
        return ret;
    }
    public BufferedImage getBoxData() {
        return boxData;
    }
    public void setBoxData(BufferedImage boxData) {
        this.boxData = boxData;
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
    public BufferedButton(BufferedImage btnImg,String content,Font font) {
        boxData=btnImg;
        text=content;
        this.font=font;
    }
}

class BufferedImage{
    private int x,y,width,height;
    private Image img;
    private int Id;
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

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public BufferedImage(Image content,int locX,int locY,int sizeWidth,int sizeHeight) {
        setImg(content);
        setX(locX);
        setY(locY);
        setWidth(sizeWidth);
        setHeight(sizeHeight);
    }
}