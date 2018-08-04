package game;

import screen.*;
import soundCapture.*;
import Character.*;
import savingModule.SaveManager;
public class GamePane extends GraphicViewer {
    Window w;
    SaveManager sv;
    LevelMeter lm;
    VoiceHandler vh;
    Dog dog;
    FixedImage timer=new FixedImage(null,1000,50,200,50);
    int addMoney=0;
    public GamePane(int width, int height, int rate,Window w) {
        super(width,height,rate);
        this.w=w;
        this.sv=w.sv;
        addComponent(new FixedImage("sprites\\title\\title.jpg",0,0,1280,720),"background");
        dog=new Dog();
        addComponent(dog.dogAnim,"dog");
        addComponent(timer,"timer");
        vh=new VoiceHandler(dog);
        lm=new LevelMeter(vh,0.05,3);
        Thread k=new Thread(new Runnable() {
            int i=0;
            @Override
            public void run() {
                while(i<=60000) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    i+=100;
                    GamePane.this.timer.setText((60-(i/1000))+"");
                }
                GamePane.this.result(true);
            }
        });
        k.start();
    }
    public void result(boolean cleared) {
        //TODO make result screen pane
        
    }
    class Dog{
        Animator dogAnim;
        public Dog() {
            dogAnim=new Animator(100,100);
            dogAnim.setX(200);
            dogAnim.setY(360);
            dogAnim.addAnimation(new Animation("sprites\\Dog\\idle",100,100), "idle");
            dogAnim.addAnimation(new Animation("sprites\\Dog\\bark",100,100), "bark");
            dogAnim.setNowAnim("idle");
            dogAnim.setNext("bark","idle");
        }
    }
    class VoiceHandler implements SoundHandler{
        Dog dog;
        public VoiceHandler(Dog dog) {
            this.dog=dog;
        }
        @Override
        public synchronized void action(double now, double peak) {
            dog.dogAnim.setNowAnim("bark");
            addComponent(new Effect(new Animation("sprites\\effect\\baseBark",100,100),230,360), "baseBark");
        }
        
    }

}
