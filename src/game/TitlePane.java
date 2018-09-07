package game;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.Random;
import screen.*;

public class TitlePane extends GraphicViewer{
    FixedImage img=new FixedImage("sprites\\Title\\title.jpg",0,0,1280,720);
    StartBtn start=new StartBtn("sprites\\Buttons\\main.png",680,150,250,60);
    UpgradeBtn upgrade=new UpgradeBtn("sprites\\Buttons\\main.png",680,300,250,60);
    ExitBtn exit=new ExitBtn("sprites\\Buttons\\main.png",680,450,250,60);
    Window w;
    TitleDogAnimator dog=new TitleDogAnimator(100,100);
    FixedImage moneyIcon=new FixedImage("sprites\\Money\\icon.png",1000,70,70,70);
    FixedImage moneyValue=new FixedImage(null,1060,75,100,50);
    public TitlePane(int width, int height, int rate,Window w) {
        super(width,height);
        this.w=w;
        start.setTooltip(new Tooltip("게임을 \n시작합니다.",5,5,Color.BLACK,Color.WHITE,15));
        upgrade.setTooltip(new Tooltip("업그레이드 페이지로\n이동합니다.",5,5,Color.BLACK,Color.WHITE,15));
        exit.setTooltip(new Tooltip("게임을 \n나갑니다.",5,5,Color.BLACK,Color.WHITE,15));
        addComponent(img,"background");
        addComponent(start,"start");
        addComponent(upgrade,"upgrade");
        addComponent(exit,"exit");
        addComponent(dog,"dog");
        addComponent(moneyIcon,"moneyIcon");
        addComponent(moneyValue,"moneyValue");
        start.setText("게임 시작");
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
                        try {Thread.sleep(changeInterval);} catch (InterruptedException e) {}
                    }
                }
            }).start();
        }
    }
    class StartBtn extends Button{
        public StartBtn(String imgSrc, int locX, int locY, int sizeWidth, int sizeHeight) {
            super(imgSrc, locX, locY, sizeWidth, sizeHeight);
        }
        @Override
        public void act(MouseEvent e) {
            TitlePane.this.dog.isPlaying=false;
            remove(w);
            w.add(new GamePane(1280,720,17,w));
        }
    }
    class ExitBtn extends Button{
        public ExitBtn(String imgSrc, int locX, int locY, int sizeWidth, int sizeHeight) {
            super(imgSrc,locX,locY,sizeWidth,sizeHeight);
            this.setText("나가기");
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
            this.setText("업그레이드");
        }
        @Override
        public void act(MouseEvent e) {
            TitlePane.this.dog.isPlaying=false;
            remove(w);
            w.add(new UpgradePane(1280,720,17,w));
        }
    }
}
