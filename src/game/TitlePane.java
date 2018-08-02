package game;

import java.awt.event.MouseEvent;
import java.util.Random;

import screen.*;

public class TitlePane extends GraphicViewer{
    FixedImage img=new FixedImage("sprites\\title\\title.jpg",0,0,1280,720);
    StartBtn start=new StartBtn("sprites\\Buttons\\main.png",800,100,200,50);
    UpgradeBtn upgrade=new UpgradeBtn("sprites\\Buttons\\main.png",800,300,200,50);
    ExitBtn exit=new ExitBtn("sprites\\Buttons\\main.png",800,500,200,50);
    Window w;
    TitleDogAnimator dog=new TitleDogAnimator(100,100);
    public TitlePane(Window w) {
        super(1280,720,200);
        this.w=w;
        start.setPane(this);
        upgrade.setPane(this);
        exit.setPane(this);
        addComponent(img,"background");
        addComponent(start,"start");
        addComponent(upgrade,"upgrade");
        addComponent(exit,"exit");
        addComponent(dog,"dog");
        
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
        TitlePane pane;
        public void setPane(TitlePane pane) {
            this.pane=pane;
            this.setText("Game Start");
        }
        public StartBtn(String imgSrc, int locX, int locY, int sizeWidth, int sizeHeight) {
            super(imgSrc, locX, locY, sizeWidth, sizeHeight);
            // TODO Auto-generated constructor stub
        }
        @Override
        public void act(MouseEvent e) {
            pane.w.remove(pane);
            pane.w.add(pane.w.game);
            pane.dog.isPlaying=false;
        }
        
    }
    class ExitBtn extends Button{
        TitlePane pane;
        public void setPane(TitlePane pane) {
            this.pane=pane;
        }
        public ExitBtn(String imgSrc, int locX, int locY, int sizeWidth, int sizeHeight) {
            super(imgSrc,locX,locY,sizeWidth,sizeHeight);
            this.setText("Exit");
        }
        @Override
        public void act(MouseEvent e) {
            pane.w.dispose();
        }
    }
    class UpgradeBtn extends Button{
        TitlePane pane;
        public void setPane(TitlePane pane) {
            this.pane=pane;
        }
        public UpgradeBtn(String imgSrc, int locX, int locY, int sizeWidth, int sizeHeight) {
            super(imgSrc, locX, locY, sizeWidth, sizeHeight);
            this.setText("Upgrades");
            // TODO Auto-generated constructor stub
        }
        @Override
        public void act(MouseEvent e) {
            pane.w.remove(pane);
            pane.w.add(pane.w.game);
            pane.dog.isPlaying=false;
        }
        
    }

}
