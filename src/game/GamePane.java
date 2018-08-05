package game;

import screen.*;
import soundCapture.*;

import java.awt.event.MouseEvent;

import Character.*;
import savingModule.SaveManager;
public class GamePane extends GraphicViewer {
    Window w;
    SaveManager sv;
    LevelMeter lm;
    VoiceHandler vh;
    Dog dog;
    FixedImage timer=new FixedImage(null,1000,50,200,50);
    Button chargeBtn=new Button("sprites\\Buttons\\main.png",200,600,100,50) {
    	@Override
    	public void act(MouseEvent e) {
    		if(dog.attackMode==1) {
    			dog.command(1);
    			this.setText("start charge");
    		}else if(dog.attackMode==0) {
    			dog.command(0);
    			this.setText("release charge");
    		}
    	}
    };
    int addMoney=0;
    public GamePane(int width, int height, int rate,Window w) {
        super(width,height,rate);
        this.chargeBtn.setText("start charge");
        this.w=w;
        this.sv=w.sv;
        addComponent(new FixedImage("sprites\\title\\title.jpg",0,0,1280,720),"background");
        dog=new Dog(this);
        addComponent(dog.dogAnim,"dog");
        addComponent(timer,"timer");
        addComponent(chargeBtn,"chargeBtn");
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
    
    class VoiceHandler implements SoundHandler{
        Dog dog;
        public VoiceHandler(Dog dog) {
            this.dog=dog;
        }
        @Override
        public synchronized void action(double now, double peak) {
            dog.attack(now);
            
        }
        
    }

}
