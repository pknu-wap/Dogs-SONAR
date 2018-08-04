package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.Random;

import screen.*;

public class TitlePane extends GraphicViewer{
    FixedImage img=new FixedImage("sprites\\Title\\title.jpg",0,0,1280,720);
    StartBtn start=new StartBtn("sprites\\Buttons\\main.png",800,100,200,50);
    UpgradeBtn upgrade=new UpgradeBtn("sprites\\Buttons\\main.png",800,300,200,50);
    ExitBtn exit=new ExitBtn("sprites\\Buttons\\main.png",800,500,200,50);
    Window w;
    TitleDogAnimator dog=new TitleDogAnimator(100,100);
    FixedImage moneyIcon=new FixedImage("sprites\\Money\\icon.png",1000,100,50,50);
    FixedImage moneyValue=new FixedImage(null,1060,100,100,50);
    public TitlePane(int width, int height, int rate,Window w) {
        super(width,height,rate);
        this.w=w;
        addComponent(img,"background");
        addComponent(start,"start");
        addComponent(upgrade,"upgrade");
        addComponent(exit,"exit");
        addComponent(dog,"dog");
        addComponent(moneyIcon,"moneyIcon");
        addComponent(moneyValue,"moneyValue");
        start.setText("Game Start");
        moneyValue.setText(w.sv.getItem("money").getValueInt()+"");
    }
    class TitleDogAnimator extends Animator {
        Random rand;
        boolean isPlaying=true;
        public TitleDogAnimator(int width, int height) {
            super(width, height);
            rand=new Random();
            this.addAnimation(new Animation("sprites\\Dog\\idle",500,500), "idle");
            this.addAnimation(new Animation("sprites\\Dog\\bark",500,500), "bark");
            this.setNext("bark", "idle");
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    int changeInterval=1000;
                    while(isPlaying) {
                        System.out.println(changeInterval+"");
                        TitleDogAnimator.this.setNowAnim("bark");
                        changeInterval=rand.nextInt(10000)+1000;
                        try {
                            Thread.sleep(changeInterval);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        
    }
    class StartBtn extends Button{
        public StartBtn(String imgSrc, int locX, int locY, int sizeWidth, int sizeHeight) {
            super(imgSrc, locX, locY, sizeWidth, sizeHeight);
            // TODO Auto-generated constructor stub
        }
        @Override
        public void act(MouseEvent e) {
            w.remove(TitlePane.this);
            w.add(new GamePane(1280,720,100,w));
            TitlePane.this.dog.isPlaying=false;
        }
        
    }
    class ExitBtn extends Button{
        public ExitBtn(String imgSrc, int locX, int locY, int sizeWidth, int sizeHeight) {
            super(imgSrc,locX,locY,sizeWidth,sizeHeight);
            this.setText("Exit");
        }
        @Override
        public void act(MouseEvent e) {
            w.dispose();
            System.exit(0);
        }
    }
    class UpgradeBtn extends Button{
        public UpgradeBtn(String imgSrc, int locX, int locY, int sizeWidth, int sizeHeight) {
            super(imgSrc, locX, locY, sizeWidth, sizeHeight);
            this.setText("Upgrades");
            // TODO Auto-generated constructor stub
        }
        @Override
        public void act(MouseEvent e) {
            w.remove(TitlePane.this);
            w.add(new UpgradePane(1280,720,50,w));
            TitlePane.this.dog.isPlaying=false;
            
        }
        
    }

}